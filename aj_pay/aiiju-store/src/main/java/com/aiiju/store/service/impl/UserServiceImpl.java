package com.aiiju.store.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.JpushClientUtil;
import com.aiiju.common.util.JsonUtils;
import com.aiiju.common.util.NoteUtil;
import com.aiiju.common.util.RandomUtil;
import com.aiiju.dao.JedisClient;
import com.aiiju.mapper.DiscountMapper;
import com.aiiju.mapper.GoodsTypeMapper;
import com.aiiju.mapper.JPushMapper;
import com.aiiju.mapper.MessageMapper;
import com.aiiju.mapper.ShopMapper;
import com.aiiju.mapper.UserLoginRecordMapper;
import com.aiiju.mapper.UserMapper;
import com.aiiju.pojo.AppAuth;
import com.aiiju.pojo.DiscountShopLink;
import com.aiiju.pojo.GoodsType;
import com.aiiju.pojo.JPush;
import com.aiiju.pojo.Message;
import com.aiiju.pojo.Shop;
import com.aiiju.pojo.User;
import com.aiiju.pojo.UserLoginRecord;
import com.aiiju.pojo.WxSub;
import com.aiiju.store.constant.DiscountTypeSwitch;
import com.aiiju.store.service.AppAuthService;
import com.aiiju.store.service.UserService;
import com.aiiju.store.service.WxSubService;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: UserServiceImpl
 * @Description: 用户ServiceImpl
 * @author 小飞
 * @date 2016年11月8日 下午4:46:10
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Autowired
    private ShopMapper shopMapper;


    @Autowired
    private DiscountMapper discountMapper;

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private JPushMapper jpushMapper;
    
    @Autowired
    private UserLoginRecordMapper userLoginRecordMapper;
    
    @Autowired
	private AppAuthService appAuthService;
    @Autowired
	private WxSubService wxSubService;
    

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;

    @Value("${REDIS_USER_TOKEN_KEY}")
    private String REDIS_USER_TOKEN_KEY;

    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;
    
    @Value("${HEART_BEAT_EXPIRE}")
    private int HEART_BEAT_EXPIRE;

    @Value("${HEART_BEAT}")
    private String HEART_BEAT;
    
    
    @Value("${STORE_ID}")
    private String STORE_ID;

    @Value("${OPERATOR_ID}")
    private String OPERATOR_ID;

    @Value("${VERIFICATION_CODE}")
    private String VERIFICATION_CODE;

    @Value("${VERIFICATION_CODE_EXPIRE}")
    private Integer VERIFICATION_CODE_EXPIRE;

    @Value("${INVITE_STORE_ID}")
    private String INVITE_STORE_ID;

    @Value("${INVITE_STORE_ID_EXPIRE}")
    private Integer INVITE_STORE_ID_EXPIRE;

    @Value("${INVITE_URL}")
    private String INVITE_URL;

    @Value("${SALT}")
    private String SALT;

    @Override
    public Result checkUser(String content, Integer type) {
        // 对数据进行校验：1、2、3分别代表username、phone、email
        // 用户名校验
        User user = null;
        if (1 == type) {
            user = this.userMapper.checkByUserName(content);
            // 电话校验
        } else if (2 == type) {
            user = this.userMapper.checkByPhone(content);
            // email校验
        } else {
            user = this.userMapper.checkByEmail(content);
        }
        if (user != null) {
            return Result.build(401, "用户名已存在", true);
        }
        return Result.success(false);
    }

    @Override
    public Result save(User user) {
    	
    	  User temp = this.userMapper.checkByPhone(user.getPhone());
    	  
    	  if (temp != null) {
           return Result.build(401, "该手机号已注册过了", false);
        }
    	  
    	
        // 昵称不为空进行判断
//        if (!StringUtils.isBlank(user.getUserName())) {
//            User temp = this.userMapper.checkByNickName(user.getNickName());
//            if (temp != null) {
//                return Result.build(401, "昵称已被使用,请更换新昵称", false);
//            }
//        } else {
//            user.setNickName(user.getUserName());
//        }
        // 绑定店铺
        Shop shop = new Shop();
        shop.setShopName(user.getUserName());
        shop.setStoreId(String.valueOf(this.jedisClient.incr(STORE_ID)));
        shop.setParentStoreId(shop.getStoreId());
        shop.setShopType("0"); // 设置为总店
        
        shop.setIsReputationShop("0");
      //  shop.setReputationShopId(0);
        shop.setModifyRelationGoods("0");
        shop.setDeleteRelationGoods("0");
        shop.setManageOwnGoods("0");
        shop.setIsDiscount(DiscountTypeSwitch.DiscountTypeSwitchOpen);
        shop.setAlipay(Byte.valueOf("2"));
        shop.setWx(Byte.valueOf("2"));
        this.shopMapper.add(shop);
        // 商品管理默认分类
        GoodsType gt = new GoodsType();
        gt.setName("默认分类");
        gt.setStoreId(shop.getStoreId());
        this.goodsTypeMapper.add(gt);
        // 优惠折扣,默认8种
        List<DiscountShopLink> links = new ArrayList<>(8);
        DiscountShopLink link = null;
        for (int i = 1; i < 9; i++) {
            link = new DiscountShopLink();
            link.setStoreId(shop.getStoreId());
            link.setDiscountId(i);
            links.add(link);
        }
        this.discountMapper.insertBatch(links);
        // 用户
//        if (StringUtils.isBlank(user.getName())) {
//            user.setName(user.getNickName());
//        }
        if (StringUtils.isBlank(user.getPhone())) {
            user.setPhone(user.getUserName());
        }
        String old = user.getPassword();
        user.setPassword(DigestUtils.md5DigestAsHex(old.getBytes()));
        user.setCreateDate(new Date());
        user.setRole(Byte.parseByte("1"));// 店长
        user.setIsDelete("0");
        user.setShopId(shop.getId());
        user.setStoreId(shop.getStoreId());
        user.setOldStoreId(shop.getStoreId());
        user.setOperatorId(shop.getStoreId() + "001");
        this.userMapper.add(user);
        this.jedisClient.set(OPERATOR_ID + ":" + shop.getStoreId(), user.getOperatorId());
        return Result.success(shop);
    }
    @Override
    public Result createAdmin(User user) {
    	
    	if(user.getPhone()==null||"".equals(user.getPhone())){
    		  return Result.build(401, "手机号不能为空", false);
    	}
    	
    	  User temp = this.userMapper.checkByPhone(user.getPhone());
    	  
    	  if (temp != null) {
           return Result.build(401, "该手机号已注册过了", false);
        }
    	  
    	if (StringUtils.isBlank(user.getUserName())) {
                user.setUserName(user.getPhone());
        }
        // 绑定店铺
        Shop shop = new Shop();
        shop.setShopName(user.getUserName());
        shop.setStoreId(String.valueOf(this.jedisClient.incr(STORE_ID)));
        shop.setParentStoreId(shop.getStoreId());
        shop.setShopType("0"); // 设置为总店
        
        shop.setIsReputationShop("0");
        shop.setCreateDate(new Date());
      //  shop.setReputationShopId(0);
        shop.setModifyRelationGoods("1");
        shop.setDeleteRelationGoods("1");
        shop.setManageOwnGoods("1");
        shop.setIsDiscount(DiscountTypeSwitch.DiscountTypeSwitchOpen);
        shop.setAlipay(Byte.valueOf("2"));
        shop.setWx(Byte.valueOf("2"));
        this.shopMapper.add(shop);
        // 商品管理默认分类
        GoodsType gt = new GoodsType();
        gt.setName("默认分类");
        gt.setCreateDate(new Date());
        gt.setStoreId(shop.getStoreId());
        this.goodsTypeMapper.add(gt);
        // 优惠折扣,默认8种
        List<DiscountShopLink> links = new ArrayList<>(8);
        DiscountShopLink link = null;
        for (int i = 1; i < 9; i++) {
            link = new DiscountShopLink();
            link.setStoreId(shop.getStoreId());
            link.setDiscountId(i);
            links.add(link);
        }
        this.discountMapper.insertBatch(links);
        // 用户
//        if (StringUtils.isBlank(user.getName())) {
//            user.setName(user.getNickName());
//        }
    
        String old = user.getPassword();
        user.setIsActivate("0");
        user.setIsDelete("0");
        user.setParentStoreId(shop.getStoreId());
        user.setPassword(DigestUtils.md5DigestAsHex(old.getBytes()));
        user.setCreateDate(new Date());
        user.setRole(Byte.parseByte("0"));// 总店长
        user.setShopId(shop.getId());
        user.setStoreId(shop.getStoreId());
        user.setOldStoreId(shop.getStoreId());
        user.setOperatorId(shop.getStoreId() + "001");
        this.userMapper.add(user);
        this.jedisClient.set(OPERATOR_ID + ":" + shop.getStoreId(), user.getOperatorId());
        return Result.success(shop);
    }
    
    // 邀请
    @Override
    public Result saveSeller(User user) {
        if (StringUtils.isBlank(user.getStoreId())) {
            String storeId = this.jedisClient.get(INVITE_STORE_ID + ":" + user.getUserName());
            if (StringUtils.isBlank(storeId)) {
                return Result.build(400, "邀请链接已超时");
            }
            user.setStoreId(storeId);
        }
        User dbUser = this.userMapper.checkByUserNameWithoutShop(user.getUserName());
        if (dbUser != null) {
            if (user.getStoreId().equals(dbUser.getStoreId())) {
                return Result.build(401, "此账号已与该店铺关联,不能重复邀请");
            }
            if ("1".equals(String.valueOf(dbUser.getRole()))) {
                List<User> userList = this.userMapper.getAllByStoreId(dbUser.getStoreId());
                if (userList != null && userList.size() > 1) {
                    return Result.build(401, "您的店铺中存在其他员工,请解除关联后重试");
                }
            } else {
                return Result.build(401, "此账号已属于某家店铺,需先解除和原店铺的关联");
            }
            if (!DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).equals(dbUser.getPassword())) {
                return Result.build(401, "用户名或密码错误");
            }
//            /**
//             * 店铺发生变动通知 ，20170401注解掉，功能待测
//             */
//            JPush jpush = this.jpushMapper.getByOperatorId(dbUser.getOperatorId());
//            if (jpush != null) {
//                String token = this.jedisClient.get(REDIS_USER_TOKEN_KEY + ":" + jpush.getEquipmentCode());
//                if (!StringUtils.isBlank(token)) {
//                    this.jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
//                    this.jedisClient.del(REDIS_USER_TOKEN_KEY + ":" + jpush.getEquipmentCode());
//                }
//                JPushUtil.sendMsg(Arrays.asList(jpush.getEquipmentCode()), "您的店铺发生变动,请重新登录",
//                        JsonUtils.objectToJson(Result.success(JPushUtil.UNBIND)));
//            }
            
            
          //  user.setRole(Byte.parseByte("2"));// 店员
            user.setOperatorId(this.jedisClient.incr(OPERATOR_ID + ":" + user.getStoreId()) + "");
            user.setId(dbUser.getId());
            this.userMapper.update(user);
        } else {
            String old = user.getPassword();
            user.setPassword(DigestUtils.md5DigestAsHex(old.getBytes()));
//            if (StringUtils.isBlank(user.getNickName())) {
//                user.setNickName(user.getUserName());
//            }
//            if (StringUtils.isBlank(user.getName())) {
//                user.setName(user.getUserName());
//            }
            if (StringUtils.isBlank(user.getPhone())) {
                user.setPhone(user.getUserName());
            }
        //    user.setRole(Byte.parseByte("2"));// 店员
            user.setOperatorId(this.jedisClient.incr(OPERATOR_ID + ":" + user.getStoreId()) + "");
            this.userMapper.add(user);
        }
        // 保存消息
        String shopName = this.shopMapper.getShopNameByStoreId(user.getStoreId());
        Message message = new Message();
        message.setSubject("收银员邀请成功");
        message.setContent("【" + user.getUserName() + "】已成为【" + shopName + "】的收银员");
        message.setStoreId(user.getStoreId());
        message.setOperatorId(user.getStoreId() + "001");
        message.setMsgType(Byte.valueOf("9"));
        this.messageMapper.add(message);
        // 推送消息给店长
//        JPush jpush = this.jpushMapper.getByOperatorId(user.getStoreId() + "001");
//        if (jpush != null) {
//            JPushUtil.sendMsg(Arrays.asList(jpush.getEquipmentCode()), message.getContent(),
//                    JsonUtils.objectToJson(Result.success(JPushUtil.INVITE)));
//        }
        return Result.success(shopName);
    }

    @Override
    public Result login(String userName, String password, String equipmentCode,String phoneType,String OS,String phoneId) {
        User user = this.userMapper.checkByPhone(userName);
        // 如果没有此用户名
        if (user == null) {
            return Result.build(401, "用户名未注册");
        }
        // 比对密码
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            return Result.build(401, "用户名或密码错误");
        }
        // 生成token
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        System.out.println("用户登录时获取的token="+user.getToken());
        // 把用户信息写入redis 和 设置session的过期时间
        this.jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, equipmentCode);
        this.jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        // token信息
        this.jedisClient.set(REDIS_USER_TOKEN_KEY + ":" + equipmentCode, token);
        this.jedisClient.expire(REDIS_USER_TOKEN_KEY + ":" + equipmentCode, SSO_SESSION_EXPIRE);
        
        
        this.jedisClient.set(HEART_BEAT + ":" + user.getId(), user.getId()+"");
        this.jedisClient.expire(HEART_BEAT + ":" + user.getId(), HEART_BEAT_EXPIRE);
        
        
        String isActivate = user.getIsActivate();
        
        if("".equals(isActivate)||isActivate==null||"0".equals(isActivate)){
        	// 用户第一登录，修改激活状态，未激活->已激活
        	System.out.println("用户第一登录，修改激活状态，未激活->已激活");
        	userMapper.updateIsActivate(user);	
        }
        // 保存登录相关信息
        UserLoginRecord userLoginRecord = new UserLoginRecord ();
        userLoginRecord.setUserId(user.getId());
        userLoginRecord.setCurrentStatus("1");
        userLoginRecord.setLoginTime(new Date());
        userLoginRecord.setPhone(user.getPhone());
        userLoginRecord.setStoreId(user.getStoreId());
        userLoginRecord.setEquipmentCode(equipmentCode);
        userLoginRecord.setPhoneType(phoneType);
        userLoginRecord.setPhoneId(phoneId);
        userLoginRecord.setOS(OS);
        saveOrUpdateUserLoginRecord(userLoginRecord);
        
        // 判断自己店铺是否有支付授权
        
       String StoreIdDB =  user.getStoreId();
       String parentStoreIdDB =  user.getParentStoreId();
       Shop shop = shopMapper.getByStoreId(StoreIdDB);
       byte aplipay = shop.getAlipay();
       byte weixin = shop.getWx();
       
       if(aplipay==2){
    	   // 查看 授权表中是否有此店铺编号或者主店铺编号，如果 更新aplipay=1
    	  AppAuth appAuth1  = appAuthService.getAppAuthByStoreId(StoreIdDB);
    	  if(appAuth1!=null){
    		  shop.setAlipay((byte)1);
    		  shop.setModifyDate(new Date());
    		  shopMapper.updatePayAuth(shop);
    		  user.setShop(shop);
    		  System.out.println("该登录用户有支付宝支付能力");
    	  }else{
    		  AppAuth appAuth2  = appAuthService.getAppAuthByStoreId(parentStoreIdDB);
    		  if(appAuth2!=null){
    			  // 主店铺有授权信息
    			  appAuth2.setStoreId(StoreIdDB);
    			  appAuth2.setCreateDate(new Date());
    			  appAuthService.saveAppAuth(appAuth2);
    			  shop.setAlipay((byte)1);
        		  shop.setModifyDate(new Date());
        		  shopMapper.updatePayAuth(shop);
        		  user.setShop(shop);
        		  System.out.println("该登录用户将使用主店的支付宝支付账号");
    		  }else{
    			  System.out.println("该登录用户无支付宝支付能力");
    		  }
    	  }   
       }else{
    	   System.out.println("该登录用户有支付宝支付能力");
       }
       
       if(weixin==2){
    	   // 查看 授权表中是否有此店铺编号或者主店铺编号，如果 更新wx=1
    	  
    	  WxSub wxSub1  =  wxSubService.getWxSubByStoreId(StoreIdDB);
    	  
    	  if(wxSub1!=null){
    		  shop.setWx((byte)1);
    		  shop.setModifyDate(new Date());
    		  shopMapper.updatePayAuth(shop);
    		  user.setShop(shop);
    		  System.out.println("该登录用户已微信签约");
    	  }else{
    		  WxSub wxSub2  =  wxSubService.getWxSubByStoreId(parentStoreIdDB);
    		
    		  if(wxSub2!=null){
    			  // 主店铺有授权信息
    			  wxSub2.setStoreId(StoreIdDB);
    			  wxSub2.setCreateDate(new Date());
    			  wxSubService.saveWxSub(wxSub2);
    			  shop.setWx((byte)1);
        		  shop.setModifyDate(new Date());
        		  shopMapper.updatePayAuth(shop);
        		  user.setShop(shop);
        		  System.out.println("该登录用户将使用主店的微信签约功能");
    		  }else{
    			  System.out.println("该登录用户无微信签约");
    		  }
    	  }   
    	  
       }else{
    	   System.out.println("该登录用户已微信签约");
       }
        user.setPassword(null);
        return Result.success(user);
    }
    @Override
    public Result heartBeat(User user) {
    	this.jedisClient.set(HEART_BEAT + ":" + user.getId(), user.getId()+"");
        this.jedisClient.expire(HEART_BEAT + ":" + user.getId(), HEART_BEAT_EXPIRE);
    
        return Result.success();
    }
    @Override
    public Result logout(String token, String equipmentCode,Long id) {
    	
        this.jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
        this.jedisClient.del(REDIS_USER_TOKEN_KEY + ":" + equipmentCode);
        this.jedisClient.del(HEART_BEAT + ":" + id);
        
       UserLoginRecord userLoginRecord = new UserLoginRecord ();
        
        userLoginRecord.setCurrentStatus("0");
        if(id!=null){
        	 userLoginRecord.setUserId(id);
        }else{
        	System.out.println("退出時，傳入的id為null");
        }
       
        saveOrUpdateUserLoginRecord(userLoginRecord);
        
       JPush jpush =  jpushMapper.getJpushByEquipmentCode(equipmentCode);
       
       
       if(jpush!=null){
            jpush.setStatus("0");
           jpushMapper.updateByEquipmentCode(jpush);
       }

        
        
        
        return Result.success();
    }

    @Override
    public Result updatePWD(String userName, String newPassword) {
        User user = this.userMapper.checkByPhone(userName);
        // 如果没有此用户名
        if (user == null) {
            return Result.build(401, "用户不存在", false);
        }
        String newPWD = DigestUtils.md5DigestAsHex(newPassword.getBytes());
        user.setPassword(newPWD);
        this.userMapper.updatePWD(user);
        // 清除缓存

        List<JPush> jpushList = this.jpushMapper.getByOperatorId(user.getOperatorId());
        
        for (JPush jPush2 : jpushList) {
            String token = this.jedisClient.get(REDIS_USER_TOKEN_KEY + ":" + jPush2.getEquipmentCode());
            if (!StringUtils.isBlank(token)) {
                this.jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
                this.jedisClient.del(REDIS_USER_TOKEN_KEY + ":" + jPush2.getEquipmentCode());
            }
        }
        

        return Result.success(true);
    }

    @Override
    public Result updatePWD(String userName, String oldPassword, String newPassword) {
        User user = this.userMapper.checkByUserNameWithoutShop(userName);
        // 如果没有此用户名
        if (user == null) {
            return Result.build(401, "用户名不存在", false);
        }
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(oldPassword.getBytes()))) {
            return Result.build(401, "原始密码不正确", false);
        }

        String newPWD = DigestUtils.md5DigestAsHex(newPassword.getBytes());
        user.setPassword(newPWD);
        this.userMapper.updatePWD(user);
        // 清除缓存
        List<JPush> jpushList = this.jpushMapper.getByOperatorId(user.getOperatorId());
        
        for (JPush jPush2 : jpushList) {
            String token = this.jedisClient.get(REDIS_USER_TOKEN_KEY + ":" + jPush2.getEquipmentCode());
            if (!StringUtils.isBlank(token)) {
                this.jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
                this.jedisClient.del(REDIS_USER_TOKEN_KEY + ":" + jPush2.getEquipmentCode());
            }
        }
        
        return Result.success(true);
    }

    @Override
    public Result checkToken(String token) {
        // 根据token从redis中查询用户信息
        String value = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
        // 判断是否为空
        if (StringUtils.isBlank(value)) {
            return Result.build(600, "用户未登录或session已过期或用户角色发生变化");
        }
        // 更新过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        jedisClient.expire(REDIS_USER_TOKEN_KEY + ":" + value, SSO_SESSION_EXPIRE);
        // 返回用户信息
        return Result.success();
    }

    @Override
    @SuppressWarnings("unchecked")
    public String sendVerificationCode(String phone) {
        // 设置验证码
        String code = RandomUtil.generateVerificationCode();
        this.jedisClient.set(VERIFICATION_CODE + ":" + phone, code);
        this.jedisClient.expire(VERIFICATION_CODE + ":" + phone, VERIFICATION_CODE_EXPIRE);
        // 调用短信API
        String codeResult = NoteUtil.sendNote(phone, "您的验证码为：" + code + "，请在5分钟内完成验证。为保障账号安全，请勿转发验证码给他人。");
        Map<String, Object> rt = JsonUtils.jsonToPojo(codeResult, Map.class);
        return rt.get("code").toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public String sendInviteURL(String shopName,String fromPhone ,String toPhone, String storeId,byte role) {
//        this.jedisClient.set(INVITE_STORE_ID + ":" + toPhone, storeId);
//        this.jedisClient.expire(INVITE_STORE_ID + ":" + toPhone, INVITE_STORE_ID_EXPIRE);  // 2017年6月15号 把短信链接的失效功能去掉
        // 调用短信API
    	//【爱聚收银记账】您收到了来自@ 的通知，请点击此链接加入店铺:@

    	if(role==0){
    		System.out.println("警告信息，您竟然要创建管理员账号！前台传值错误，role不可为0");
    	}
    	String message = "您收到了来自"+fromPhone+"的通知，请点击此链接加入店铺：" + NoteUtil.getShortUrl(INVITE_URL+"?shopName="+shopName+"&fromPhone="+fromPhone+"&role="+role
            	+"&toPhone="+toPhone+"&storeId="+storeId);
    	System.out.println("短信发送的消息："+message);
        String codeResult = NoteUtil.sendNote(toPhone,message);
        Map<String, Object> rt = JsonUtils.jsonToPojo(codeResult, Map.class);
        
        String code = rt.get("code").toString();
        System.out.println("短信接口返回："+codeResult);
        return codeResult;
    }

    @Override
    public Result checkCode(String phone, String code) {
        String redisCode = this.jedisClient.get(VERIFICATION_CODE + ":" + phone);
        if (redisCode == null) {
            return Result.build(401, "验证码已失效，请重试");
        }

        if (redisCode.equals(code)) {
            this.jedisClient.del(VERIFICATION_CODE + ":" + phone);
            return Result.success(true);
        }
        return Result.build(401, "验证码输入错误");
    }

    @Override
    public Result updateByOperatorId(String operatorId) {
        User user = this.userMapper.getUserByOperatorId(operatorId);
        if (user == null) {
            return Result.build(400, "该店员不存在");
        }
        if (!StringUtils.isBlank(user.getOldStoreId())) {
            String oldStoreId = user.getOldStoreId();
            user.setStoreId(oldStoreId);
            user.setOperatorId(oldStoreId + "001");
        } else {
            user.setStoreId(null);
            user.setOperatorId(null);
        }
        user.setRole(Byte.valueOf("1"));
        this.userMapper.updateOnShop(user);
        // 推送消息给店员

        
        List<JPush> jpushList = this.jpushMapper.getByOperatorId(user.getOperatorId());
        
        for (JPush jPush2 : jpushList) {
            // 清除缓存
            String token = this.jedisClient.get(REDIS_USER_TOKEN_KEY + ":" + jPush2.getEquipmentCode());
            if (!StringUtils.isBlank(token)) {
                this.jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
                this.jedisClient.del(REDIS_USER_TOKEN_KEY + ":" + jPush2.getEquipmentCode());
            }

            
          	if("androidT1".equals(jPush2.getPhoneType())){

         		JpushClientUtil.sendMsgT1(jPush2.getEquipmentCode(),"您的店铺发生变动,请重新登录",JsonUtils.objectToJson(Result.build(200, "您的店铺发生变动,请重新登录", JpushClientUtil.NOLOGIN)),jPush2.getEnvType());
         	}else{
         		JpushClientUtil.sendMsg(jPush2.getEquipmentCode(),"您的店铺发生变动,请重新登录",JsonUtils.objectToJson(Result.build(200, "您的店铺发生变动,请重新登录", JpushClientUtil.NOLOGIN)),jPush2.getEnvType(),jPush2.getFromApp());
         		
         	}
            
            
            
        }
        
        
        
        
        
        return Result.success(true);
    }

    @Override
    public Result update(User user) {
        // 修改昵称时 需进行去重判断
//        if (!StringUtils.isBlank(user.getNickName())) {
//            List<User> userList = this.userMapper.getAllByStoreId(user.getStoreId());
//            for (User dbUser : userList) {
//                if (dbUser.getNickName().trim().equals(user.getNickName().trim())) {
//                    return Result.build(400, "该昵称已被占用");
//                }
//            }
//        }
        this.userMapper.update(user);
        return Result.success(true);
    }

    @Override
    public Result getById(Long id) {
        User user = this.userMapper.getById(id);
        
        Shop shop = shopMapper.getByStoreId(user.getStoreId());
        
        return Result.build(200, "", user, shop.getImageUrl());
    }

    @Override
    public Result getByStoreId(String storeId) {
        List<User> list = this.userMapper.getAllByStoreId(storeId);
        return Result.success(list);
    }

	@Override
	public Result saveOrUpdateUserLoginRecord(UserLoginRecord userLoginRecord) {
		
		if(userLoginRecord!=null&&userLoginRecord.getCurrentStatus().equals("1")){
			
			
			userLoginRecordMapper.updateStatus(userLoginRecord);//先确保 ，使其此用户下线
			userLoginRecordMapper.add(userLoginRecord);
		}else{
			UserLoginRecord userLogin  = userLoginRecordMapper.getByPhoneStoreId( userLoginRecord);
			
			if(userLogin!=null &&userLogin.getLoginTime()!=null ){
				
				
				Date date = new Date();
				
				long onlineTime = date.getTime() - userLogin.getLoginTime().getTime();
				userLoginRecord.setOnlineTime((int)onlineTime);
				userLoginRecord.setLogouTime(date);
				userLoginRecordMapper.update(userLoginRecord);
			}else{
				System.out.println("无效的中间数据，不进行其他操作了");
			}
			
		}

		return Result.success();
	}
	
	public static String formatSecondToTime(long second){
		
		long xiaoshi = second/3600;
		long fenzhong = (second%3600)/60;
		long miao = (second%(3600*60))%60;	
		StringBuffer sb = new StringBuffer();

		if(xiaoshi!=0){
			sb.append(xiaoshi+"小时");
		}
		if(fenzhong!=0){
			sb.append(fenzhong+"分钟");
		}
		if(miao!=0){
			sb.append(miao+"秒");
		}
		return sb.toString();
	}


	@Override
	public List<UserLoginRecord> getOnlineUserList() {
		
       List<UserLoginRecord> onlinkList = this.userLoginRecordMapper.getOnlineUser();
		
		return onlinkList;
	}

	@Override
	public Result updateStatusBatch(List<UserLoginRecord> userLoginRecord) {
		userLoginRecordMapper.updateStatusBatch(userLoginRecord);
		return Result.success();
	}

	
	@Override
	public Result addClerk(User user,String shopName,String fromPhone) {
		
	   byte	role = user.getRole();
	  
	   if(role<1){
		   return Result.build(401, "您无权添加此角色", true);
	   }
		if(role==1){
			Integer isExist =    userMapper.queryIsExistRoleIs1(user);
			
			if(isExist>0){
				
				  return Result.build(401, "已存在店长角色，只能添加店员", true);
			}
		}
		
	
		
		User userDB = userMapper.checkByPhone(user.getPhone());

		
		if(userDB==null){

			  String codeResult = sendInviteURL(shopName, fromPhone, user.getPhone(), user.getStoreId(), user.getRole());
	            
	     	  JSONObject json = 	JSONObject.fromObject(codeResult);
			  Integer code =  (Integer) json.get("code");
			  String data =  (String) json.get("data");    
			 //  System.out.println("短信工具包返回："+data);
			   if(code==0){
				 //  return Result.build(200, "获取成功", true);
			   }else{
				   return Result.build(500, "发送短信通知失败,"+data, true);
				   
			   }	
			   
			//   this.jedisClient.set(OPERATOR_ID + ":" + shop.getStoreId(), user.getOperatorId());

			  String operatorid = this.jedisClient.incr(OPERATOR_ID + ":" + user.getStoreId()) + "";
			  
			  if(operatorid!=null&&operatorid.length()>8){
				  user.setOperatorId(this.jedisClient.incr(OPERATOR_ID + ":" + user.getStoreId()) + "");
			  }else{
				    user.setOperatorId(user.getStoreId() + "002");
			        this.jedisClient.set(OPERATOR_ID + ":" + user.getStoreId(), user.getOperatorId());
			  }
			user.setOperatorId(this.jedisClient.incr(OPERATOR_ID + ":" + user.getStoreId()) + "");
			if(user.getUserName()==null||"".equals(user.getUserName())){
				user.setUserName(user.getPhone());
			}
			user.setCreateDate(new Date());
			user.setIsActivate("0");
			user.setIsDelete("0");
			userMapper.addClerk(user);
		
			
			return Result.success(user);
		}else{
			  return Result.build(401, "该用户已存在", true);
		}

	}

	@Override
	public Result clerkList(String storeId) {
		
		
	// 管理员 ，  店长 ,店员 ，    查询 
		
		List<User> clerkList = new ArrayList<User>();

//		if("0".equals(role)){
//
//			//超级管理员    查询 Shop 和  ReputationShop 的  parent_store_id  为 storeId的数据
//			clerkList = userMapper.getClerkListByParentStoreId(storeId);
//		}
//	    if("1".equals(role)){
			
	    	clerkList = userMapper.getByStoreId(storeId);
	   
			//店长    查询 Shop 和  ReputationShop 的  store_id  为 storeId的数据
//		}
		
		return Result.success(clerkList);

	}


	@Override
	public Result deleteClerk(User user) {

		User userDB = userMapper.getById(user.getId());
		List<JPush> list = jpushMapper.getListByOperatorId(userDB.getOperatorId());
		
		String msg = "您因被移出该店铺，被迫下线";
		
		for (JPush jPush : list) {
			
    	     String phoneType = jPush.getPhoneType();
         	
         	if("androidT1".equals(phoneType)){

         		JpushClientUtil.sendMsgT1(jPush.getEquipmentCode(),msg,JsonUtils.objectToJson(Result.build(200, msg, JpushClientUtil.NOLOGIN)),jPush.getEnvType());
         	}else{
         		JpushClientUtil.sendMsg(jPush.getEquipmentCode(),msg,JsonUtils.objectToJson(Result.build(200, msg, JpushClientUtil.NOLOGIN)),jPush.getEnvType(),jPush.getFromApp());
         		
         	}
			
			 
			
		}
		userMapper.deleteClerk(user);
		
		return Result.success();
	}


	@Override
	public Result updateClerk(User user) {
		
		//查询该店铺下是否已有店长，如果，提示不能修改为店长
		if(user.getRole()==1){
			Integer hava = userMapper.selectHave( user);
			if(hava==null||(hava!=null&&hava<1)){
				userMapper.updateClerk(user);
				return Result.success();	
			}else{
				return Result.build(401, "本店铺已存在店长");
			}
		}else{
			
			//传入角色为0时，查询改员工原角色后比较是否变更角色
		User userDB =	userMapper.getById(user.getId());
		
		if(user.getRole()!=userDB.getRole()){
			// 角色发生改变，推送消息
			
			List<JPush> list = jpushMapper.getListByOperatorId(userDB.getOperatorId());
			
			for (JPush jPush : list) {
				// 推送代码
				
			     String phoneType = jPush.getPhoneType();
		         	
		         	if("androidT1".equals(phoneType)){
		         		JpushClientUtil.sendMsgT1(jPush.getEquipmentCode(),"检测到您的角色发生变动，请重新登录",JsonUtils.objectToJson(Result.build(200, "检测到您的角色发生变动，请重新登录", JpushClientUtil.NOLOGIN)),jPush.getEnvType());
		         		//JpushClientUtil.sendMsgT1(jPush.getEquipmentCode(),"检测到您的角色发生变动，请重新登录",JsonUtils.objectToJson(Result.success(JpushClientUtil.UNBIND)),jPush.getEnvType());
		         	}else{
		         		JpushClientUtil.sendMsg(jPush.getEquipmentCode(),"检测到您的角色发生变动，请重新登录",JsonUtils.objectToJson(Result.build(200, "检测到您的角色发生变动，请重新登录", JpushClientUtil.NOLOGIN)),jPush.getEnvType(),jPush.getFromApp());
		         		
		         	}
				
				
			}
			System.out.println("角色发生改变，推送消息");
			
			
		}
			userMapper.updateClerk(user);
			return Result.success(user);
		}

	}

	
	@Override
	public Result updateClerkPassword(User user) {
		
		String newPWD = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
	    user.setPassword(newPWD);
		userMapper.updateClerkPassword(user);
		
		//this.userMapper.getUserByPhoneAndStoreId(user);
		
		return Result.success();
	}


	@Override
	public Result clerkListIsActivate(String role,String storeId,String queryStoreId) {

		List<User> clerkList = new ArrayList<User>();
		
		if("".equals(queryStoreId)||queryStoreId==null){
			
			System.out.println("查询所有店铺的店员，但要根据当前用户的角色，如果是管理员，查询除加盟店下的所有店铺的店员，"
					+ "如果是店长，则只能查看自己店铺下的所有店员");
			
			if("0".equals(role)){

				clerkList = userMapper.clerkListIsActivateByParentStoreId(storeId);
				
			}else{
				
				clerkList = userMapper.clerkListIsActivateByStoreId(storeId);
			}
			
			
		}else{
			// 查询具体店铺下的员工了
			
			clerkList = userMapper.clerkListIsActivateByStoreId(queryStoreId);
		}
		

		return Result.success(clerkList);

	}

	@Override
	public Result grantERP(String rId,String pStoreId,String storeIds) {
		
		// 如果没有此用户名
        if (rId == null) {
            return Result.build(401, "erpID 为空");
        }
        
        String[] stores = storeIds.split(",");
        HashSet<String> storeSet = new HashSet<String>();
        
		if (stores != null && stores.length > 0) {
			for (String storeId : stores) {
				storeSet.add(storeId);
			}
		}

		// 判断是否为空
		if (pStoreId == null || StringUtils.isBlank(pStoreId)) {
			return Result.build(401, "授权的总店铺不可以为空");
		}
		storeSet.add(pStoreId);

		// 判断这些店铺是不是同一家的店铺
		Set<String> list = new HashSet<String>();
		list.add(pStoreId);
		if(!StringUtils.isBlank(storeIds)){
			String[] ids= storeIds.split(",");
			if(ids != null && ids.length > 0){
				for (String id : ids) {
					list.add(id);
				}
			}
		}

		Map<String, Object> params = new HashMap<String, Object>(1);
		params.put("ids", list);
		Set<String> pStoreSet = this.shopMapper.getParentStoreIdSet(params);
		
		if (pStoreSet.isEmpty() || pStoreSet.size() != 1) {
			return Result.build(401, "选择授权的店铺不全是同一家的店铺");
		}

		// 保存这些店铺的授权状态
		boolean status = this.updateErpGrantStores(storeSet);
        
        // 在redis 中存储一个 值用于 获取token
        this.jedisClient.set("GETPSTORE" + ":"+ rId  , pStoreId);
        this.jedisClient.set("GETGRANTSTORES" + ":" + rId , storeIds);
        
        this.jedisClient.expire("GETPSTORE" + ":"+ rId  , 60);
        this.jedisClient.expire("GETGRANTSTORES" + ":" + rId , 60);
        
        return Result.success("授权成功");
	}

	/**
	 * 更新授权店铺的状态
	 * 
	 * @param storeSet
	 * @return 如果这些店铺是同一个总店下的分店，将保存状态返回成功。否则返回失败
	 */
	public boolean updateErpGrantStores(Set<String> storeIdSet) {
		 Map<String, Object> params = new HashMap<String, Object>(1);
		 params.put("ids", storeIdSet);
		// 判断这些店铺是不是同一家店铺，如果是返回总店铺的 id
		Set<String> pStoreSet = this.shopMapper.getParentStoreIdSet(params);
		
		// 查询所有店铺的总店铺，如果结果是有且只有一个说明是同一家店铺的
		if (pStoreSet.isEmpty() || pStoreSet.size() != 1) {
			return false;
		}
		String pstoreId = pStoreSet.iterator().next();
		// 取消总店铺下授权的所有的分店铺
		this.shopMapper.cancelErpGrant(pstoreId);
		// 重新授权 ，新的店铺的
		this.shopMapper.savaGrantStatus(params);

		return true;
	}

	@Override
	public Result getGrant(String rId) {
		
		// 需要从redis 查询结果
		String grantStores = this.jedisClient.get("GETGRANTSTORES" + ":" + rId);
		if(StringUtils.isBlank(grantStores) ){
			return Result.build(401, "没有授权信息，获取授权的店铺的数量为0");
		}else{
			// 授权后，立即失效 
			String pStoreId = this.jedisClient.get("GETPSTORE" + ":"+ rId);
			
			this.jedisClient.expire("GETPSTORE" + ":"+ rId  , 0);
	        this.jedisClient.expire("GETGRANTSTORES" + ":" + rId , 0);
	        
	       // 存入值 ：表示授权的结果
	       this.jedisClient.set("GRANTRESULT:" + rId, "success");
	       this.jedisClient.expire("GETPSTORE" + ":"+ rId  , 60);
			
			Map<String, List<Shop>> map = new HashMap<String, List<Shop>>();
			ArrayList<Shop> list = new ArrayList<Shop>();
			
			/**
			 * 用一个map 集合 里面放一个父店铺 的  和需要进行授权的用户的信息
			 */
		    Shop pshop = this.shopMapper.getByStoreId(pStoreId);
			Shop tempPshop = new Shop();
			tempPshop.setShopName(pshop.getShopName());
			tempPshop.setStoreId(pshop.getStoreId());
			
			list.add(tempPshop);
			map.put("pshop", list);
			
			// 存储店铺的id
			Set<String> storeIds = new HashSet<String>();
			
			ArrayList<Shop> sList = new ArrayList<Shop>();
			String[] grantStoreIds = grantStores.split(",");
		        for (String storeId : grantStoreIds) {
		        	Shop shop = this.shopMapper.getByStoreId(storeId);
		        	if(shop != null){
		        		tempPshop = new Shop();
						tempPshop.setShopName(shop.getShopName());
						tempPshop.setStoreId(shop.getStoreId());
						
		        		sList.add(tempPshop);
		        		
		        		storeIds.add(shop.getStoreId());
		        	}
				}
			map.put("grantShop", sList);
			
			// 把授权的信息存储到数据库中
			boolean status = this.updateErpGrantStores(storeIds);
			if(!status){
				return Result.build(401, "授权的店铺不全部是同一家的店铺，请仔细核对");
			}
			
			
			String jsonStr = JsonUtils.objectToJsonNONull(map);
			
			return Result.success(jsonStr);
		}
	}

	
	@Override
	public Result getGrantStatus(String rId) {
		// 授权成功后，grantResult 中的值是 success ，如果没有拿到授权是失败
		String grantResult = this.jedisClient.get("GRANTRESULT:" + rId);
		if (StringUtils.isBlank(grantResult)) {
			// 授权成功后，应该删除获取授权，也就是只能在授权中获取授权信息其他的情况下无法获取信息
			String grantStores = this.jedisClient.get("GETGRANTSTORES" + ":" + rId);
			if(!StringUtils.isBlank(grantStores)){
				this.jedisClient.expire("GETPSTORE" + ":"+ rId  , 0);
		        this.jedisClient.expire("GETGRANTSTORES" + ":" + rId , 0);
			}
							
			return Result.build(401, "没有获得授权信息");
		} else {
			// 目前只有成功这 一个结果（success），暂时不做判断了
			// 删除 redis 中的授权结果结果 （立即失效）
			this.jedisClient.expire("GRANTRESULT:" + rId, 0);
			return Result.success("授权成功");
		}
	}


	@Override
	public Result sendIsOnlineMsg() {
		
//		List<JPush> jpustList = jpushMapper.getAllEquipmentCode();
//		
//		for (JPush jPush : jpustList) {
//			JPushUtil.sendMsg(Arrays.asList(jPush.getEquipmentCode()),"检查是否在线的推送消息",JsonUtils.objectToJson(Result.success(JPushUtil.ISONLINE)));
//		}
		return Result.success("此接口已废弃，因为TMD，IOS不支持");
	}

	

	@Override
	public Result whoIsOnline() {
		
		List<String> list = new ArrayList<String>();
		
		Set<String> total =  jedisClient.keys("HEART_BEAT:*");
		
	    for (String userkey : total) {
	    	
	    	String userId = userkey.replace("HEART_BEAT:", "");
	    	User user = userMapper.getById(Long.parseLong(userId));
	    	
	    	list.add("用户账号："+user.getPhone()+"，商铺号："+user.getStoreId()+",主店铺号："+user.getParentStoreId());
		}
		  
		  
		  int totalCount =  total.size();
		  
		  
		return Result.build(200, "当前在线总人数："+totalCount, list);
	}

	@Override
	public User getUserByPhone(String phone) {
		User user = 	userMapper.checkByPhone(phone);
		return user;
	}
}
