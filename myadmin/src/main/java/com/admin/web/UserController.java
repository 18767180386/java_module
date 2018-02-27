package com.admin.web;

import com.admin.annotation.AuthCheck;
import com.admin.constant.GlobalConstant;
import com.admin.entity.PermissionsList;
import com.admin.entity.Role;
import com.admin.entity.User;
import com.admin.entity.UserRole;
import com.admin.service.RedisService;
import com.admin.service.ResourceService;
import com.admin.service.UserService;
import com.admin.util.ClientUtils;
import com.admin.util.DateUtil;
import com.admin.util.FileCommonUtil;
import com.admin.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/7/16.
 */

@Controller
@RequestMapping("/user")
public class UserController  extends  ApiController{
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService  resourceService;

    @Autowired
    private RedisService redisService;

    /**
     *
     * 登录
     * @param request
     * @return
     */
    @RequestMapping("testpic")
    public ModelAndView testPic(HttpServletRequest request) {
        ModelAndView  mv = new ModelAndView();
        mv.setViewName("TestPic");
        return mv;
    }


    /**
     *
     * 登录
     * @param request
     * @return
     */
    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView  mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }


    /**
     *
     * 登录处理
     * @param user
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/loginaction", method = RequestMethod.POST)
    public ModelAndView  loginAction(User user,HttpServletRequest request)throws Exception{
        ModelAndView  mv = new ModelAndView();
        String userName=user.getUserName();
        String password=user.getUserPassword();
        String code=user.getCode().toLowerCase();
        String _code=((String)request.getSession().getAttribute("code")).toLowerCase();
        if(Utils.isEmpty(userName))
        {
            mv.setViewName("login");
            mv.addObject("result","用户名不能为空");
        }else if(Utils.isEmpty(password))
        {
            mv.setViewName("login");
            mv.addObject("result","密码不能为空");
        }else if(Utils.isEmpty(code))
        {
            mv.setViewName("login");
            mv.addObject("result","验证码不能为空");
        }else if(!code.equals(_code))
        {
            mv.setViewName("login");
            mv.addObject("result","验证码输入错误");
        }else
        {
            user.setLoginIp(ClientUtils.getIpAddr(request));
            user.setLoginTime(new Date());
            int flag= userService.login(user);
            if(flag>0) {
                User  userinfo=userService.getUserByName(user.getUserName());
                if(userinfo!=null) {
                 //   LOG.info("invoke----------/user/list"+user.getUserName());
                    //
                   redisService.put("test", "mjinksdnjhiii", 1, TimeUnit.HOURS);
                    request.getSession().setAttribute(GlobalConstant.USER_LOGIN, user);
                    List<PermissionsList> permissionsLists=userService.getPermissionsListByUserId((int) userinfo.getUserId());
                    request.getSession().setAttribute(GlobalConstant.USER_PERMISSION,permissionsLists);
                   // System.out.println(JSONObject.toJSONString(permissionsLists));
                    mv.setViewName("redirect:/user/index");
                }else{
                    mv.setViewName("login");
                   // mv = new ModelAndView("redirect:/user/login");
                    mv.addObject("result","用户名不存在");
                }
            }else{
                mv.setViewName("login");
                mv.addObject("result","用户名不存在或密码错误");
            }
        }
        LOG.info("log---------------------------------------1--"+ FileCommonUtil.endFileDir());
        return mv;
    }


    /**
     *
     * 主页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("index")
    public ModelAndView  index(HttpServletRequest request, HttpServletResponse response) {
        String json = redisService.get("test");
        LOG.info("-------------"+json);
        ModelAndView   mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }


    /**
     *
     * 欢迎页
     * @param request
     * @return
     */
    @RequestMapping("welcome")
    public String  welcome(HttpServletRequest request) {
        return "welcome";
    }


    @RequestMapping("testtag")
    public String  testTag(HttpServletRequest request) {
        return "TestTag";
    }

    @AuthCheck(type = "/user/adminlist", write = false)
    @RequestMapping(value = "/adminlist", method = RequestMethod.GET)
    public String  adminList(HttpServletRequest request, Model model,String name,Integer pageNum,Integer pageSize) {
        pageNum=pageNum==null?1:pageNum;
        int offset = pageNum;// 默认便宜0
        int limit = pageSize==null ? 10 : pageSize;// 默认展示50条
        offset = (offset - 1) * limit;
        String keyword = name==null? "" : name;
        List<User> uList=new ArrayList<User>();
        List<User> list = userService.getUserList(uList,keyword,offset,limit);
        if(list.size()>0)
        {
            for (int i=0;i<list.size();i++)
            {
                 Role role=userService.getUserRoleByUserId((int)list.get(i).getUserId());
                 if(role!=null)
                 {
                     list.get(i).setRoleName(role.getRoleName());
                 }

            }
        }
        int count=userService.getUserCount(keyword);
        int totalPageNum = (count +  limit  - 1) / limit;
        model.addAttribute("userlist", list);
        model.addAttribute("count",count);
        model.addAttribute("totalpagenum",totalPageNum);
        model.addAttribute("curpage",pageNum);
        return "admin-list";
    }




    @RequestMapping("adduserlist")
    public void   addUserList(HttpServletRequest request) {
        List<User> uList=new ArrayList<User>();
        for (int i=0;i<100;i++)
        {
            User user=new User();
            user.setCreateTime(DateUtil.dateFormatAll(new Date()));
            user.setScore(1);
            user.setUserName("admin"+i);
            user.setUserPassword("pas-"+i);
            user.setUserPhone("as");
            uList.add(user);
        }
        userService.addUserList(uList);

    }



    @RequestMapping(value = "/user_action", method = RequestMethod.POST)
    public String addUserAction(User user,HttpServletRequest request){
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        userService.addUser(user);
        //System.out.println("进入了add1方法"+user.getUserName()+"---"+user.getUserPhone());
        return "redirect:/user/list";
    }




    @RequestMapping("testlogin")
    public String  testLogin(HttpServletRequest request) {
        //LOG.info("invoke----------/user/list"+user.getUserName());
        //  boolean flag = userService.addUser(user);
        //model.addAttribute("userlist", list);
        return "testlogin";
    }



    @RequestMapping("adduser")
    public String addUser(HttpServletRequest request) {
        //LOG.info("invoke----------/user/list"+user.getUserName());
        //  boolean flag = userService.addUser(user);
        //model.addAttribute("userlist", list);
        return "adduser";
    }

    @RequestMapping("addusers")
    public ModelAndView add1(ModelAndView m, User t){
        System.out.println("进入了add1方法");
        m.setViewName("add");
        m.addObject(t);
        return m;

    }


    //@RequestParam("user_id")

    @RequestMapping(value = "/delUser", method = RequestMethod.POST)
    public void delUser(String user_id, HttpServletRequest request, HttpServletResponse response){
      //  LOG.info("invoke----------/user/list"+user_id);
        userService.delUser(user_id);
        writeJson(response,"1");
       // return "redirect:/user/adminlist";
    }

    @RequestMapping(value = "/delAllUser", method = RequestMethod.POST)
    public void delAllUser(String user_ids, HttpServletRequest request, HttpServletResponse response){
        //  LOG.info("invoke----------/user/list"+user_id);
        userService.falseDelete(user_ids.split(","));
       // System.out.print(user_ids);
        writeJson(response,"1");
        // return "redirect:/user/adminlist";
    }

    @AuthCheck(type = "/user/adminadd", write = false)
    @RequestMapping("adminadd")
    public ModelAndView  adminAdd(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin-add");
        List<Role>  rolelist=resourceService.getRoleList("",0,100);
        mav.addObject("rolelist",rolelist);
        mav.addObject("user",new User());
        return mav;
    }



    @RequestMapping("adminedit")
    public ModelAndView  adminEdit(String id,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin-add");
        User user=userService.getUserById(Integer.valueOf(id));
        mav.addObject("user",user);
        List<UserRole> userRoleList=userService.getUserRoleById(Integer.valueOf(id));
        List<Role>  rolelist=resourceService.getRoleList("",0,100);
        boolean isAll=false;
        if(userRoleList!=null && userRoleList.size()>0)
        {
           for (int i=0;i<rolelist.size();i++)
           {
               for (int k=0;k<userRoleList.size();k++)
               {
                   if(rolelist.get(i).getId()==userRoleList.get(k).getRoleId())
                   {
                       rolelist.get(i).setCheck(true);
                   }
               }
           }
           if(userRoleList.size()==rolelist.size())
           {
               isAll=true;
           }
        }
        mav.addObject("rolelist",rolelist);
        mav.addObject("isall",isAll);
        return mav;
    }


    private static final String uploadFilePath = "d:\\temp_upload_file\\";

    @RequestMapping(value = "/adminuseraddaction", method = RequestMethod.POST)
    public void   adminUserAddAction(User user,HttpServletRequest request,HttpServletResponse response,MultipartFile file){
        if(user.getUserId()>0) {
            user.setPic(uploadPic(user,file));
            userService.updateUserInfoByUser(user);
        }else{
            user.setPic(uploadPic(user,file));
            user.setScore(1);
            user.setCreateTime(DateUtil.dateFormatAll(new Date()));
            userService.addUser(user);
            //System.out.print("----------user"+user.getUserId());
        }
        writeJson(response,user.getUserId()+"");
    }


    private String uploadPic(User user,MultipartFile studentPhoto)
    {
        String picUrl = "";
        try {
            if(studentPhoto!=null)
            {
               MultipartFile uploadFile = studentPhoto;
                String filename = uploadFile.getOriginalFilename();
                InputStream is = uploadFile.getInputStream();
                // 如果服务器已经存在和上传文件同名的文件，则输出提示信息
                picUrl = filename;
                File folder = new File(uploadFilePath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                File tempFile = new File(uploadFilePath + filename);
                if (tempFile.exists()) {
                    boolean delResult = tempFile.delete();
                    System.out.println("删除已存在的文件：" + delResult);
                }
                // 开始保存文件到服务器
                if (!filename.equals("")) {
                    FileOutputStream fos = new FileOutputStream(uploadFilePath + filename);
                    byte[] buffer = new byte[8192]; // 每次读8K字节
                    int count = 0;
                    // 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中
                    while ((count = is.read(buffer)) > 0) {
                        fos.write(buffer, 0, count); // 向服务端文件写入字节流
                    }
                    fos.close(); // 关闭FileOutputStream对象
                    is.close(); // InputStream对象
                }
            }else{
                picUrl=user.getPic();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return picUrl;
    }


    @RequestMapping(value = "/updateuserstatus", method = RequestMethod.POST)
    public void  updateUserStatus(String user_id,String status,HttpServletRequest request, HttpServletResponse response){
        userService.updateUserStatus(Integer.valueOf(status),Integer.valueOf(user_id));
        writeJson(response,"1");
    }


    /**
     *
     * 退出登录
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/loginout", method = RequestMethod.GET)
    public ModelAndView  loginOut(HttpServletRequest request)throws Exception{
        // 清除session
        //Enumeration<String> em = request.getSession().getAttributeNames();
       // while (em.hasMoreElements()) {
         //   request.getSession().removeAttribute(em.nextElement().toString());
      //  }
        request.getSession().removeAttribute("code");
        request.getSession().removeAttribute(GlobalConstant.USER_LOGIN);
        request.getSession().invalidate();
        ModelAndView mv =new ModelAndView("redirect:/user/login");
        return mv;
    }

}

