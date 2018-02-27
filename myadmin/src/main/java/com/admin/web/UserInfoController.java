package com.admin.web;

import com.admin.annotation.ValidateFiled;
import com.admin.annotation.ValidateGroup;
import com.admin.dto.JsonResult;
import com.admin.dto.Results;
import com.admin.entity.PicModel;
import com.admin.entity.RequestCode;
import com.admin.entity.Response;
import com.admin.entity.UserInfo;
import com.admin.httpnet.HttpPostUrl;
import com.admin.service.RedisService;
import com.admin.service.UserInfoService;
import com.admin.service.UserService;
import com.admin.util.CommonParams;
import com.admin.util.MdUtils;
import com.admin.util.Utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/userinfo")
public  class UserInfoController extends  ApiController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserInfoService  userInfoService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("test")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView  mv = new ModelAndView();
        mv.setViewName("test");
        return mv;
    }


    @RequestMapping(value = "/qt", method = RequestMethod.POST)
    public  void  remoteRequest(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo)
    {
        String name=userInfo.getUserName();
        String password=userInfo.getUserPassword();

        /*
        List<PicModel> picModelList=new ArrayList<PicModel>();
        for(int i=0;i<10;i++)
        {
            PicModel picModel=new PicModel();
            picModel.setPicUrl("http://aiiju.com/pc/images/comm/banner_02.png");
            picModel.setThumbnailUrl("http://aiiju.com/pc/images/comm/banner_02.png");
            picModelList.add(picModel);
        }

        Map<String,String> map=new HashMap<String, String>();
      //  map.put("userName",name);
      //  map.put("userPassword",password); //http://192.168.3.199:8080/myadmin/userinfo/userreg



        map.put("title","年后22112洞察束带2");
        map.put("content","剩的剩的所多未收到2");
        map.put("user_id","123456");
        map.put("type","3");
        map.put("author","asasasas");
        map.put("pic",JSON.toJSONString(picModelList));


      //  map.put("reply_content","阿萨斯21212");
      //  map.put("reply_uid","123456");
      //  map.put("reply_to_id","123456");
      //  map.put("comment_id","4");

        //map.put("offset","1");
      //  map.put("limit","20");
       // map.put("sort_type","1");
       // map.put("comment_id","3");
       // map.put("type","1");
      // // map.put("keyword","");

      //  String content=map.get("reply_content").toString();
      //  String reply_uid=map.get("reply_uid").toString();
      //  String reply_to_id=map.get("reply_to_id").toString();
       // String comment_id=map.get("comment_id").toString();

        String json=JSON.toJSONString(map);

        Map<String,String> parammap=new HashMap<String, String>();
        //  map.put("userName",name);/userinfo/userreg
        String args=Utils.urlEncoder(json);
        parammap.put("args",args);
        //  map.put("userPassword",password); //http://192.168.3.199:8080/myadmin
        parammap.put("sign", CommonParams.getHttpSign(json));
        System.out.println("sign3--------"+args+"------------"+CommonParams.getHttpSign(args));
        */

     //   parammap.put("offset","1");
       // parammap.put("limit","10");
       // parammap.put("type","0");


     //  LOG.info("-------"+json+"--------------"+JSON.toJSONString(parammap));
        /*
        String title = map.get("title").toString();
        String content = map.get("content").toString();
        String userId = map.get("user_id").toString();
        String author = map.get("author").toString();
        */

        //for(int i=0;i<100;i++) {
            Map<String, String> map = new HashMap<String, String>();

            map.put("offset","1");
            map.put("limit","20");
            map.put("sort_type","1");
            map.put("occ_id","701");
            //map.put("type","1");


           // map.put("comment_content", "阿萨斯21212s维维尔2四大皆空扩扩扩扩扩扩扩扩扩扩212卡拉斯科绿多多多多多多多多多多多多多多多军或212所得到的多多多多多多多多多多多多多多多多多多多多多多多多多多多21212");
          //  map.put("comment_uid", "2");
           // map.put("comment_num", "123456");
            //  map.put("comment_id","4");
           // map.put("occupation_id", "701");


           // map.put("reply_content", "阿萨斯21212s维维尔2四大皆空扩扩扩扩扩扩扩扩扩扩212卡拉斯科绿多多多多多多多多多多多多多多多军或212所得到的多多多多多多多多多多多多多多多多多多多多多多多多多多多21212");
            //map.put("reply_uid", "2");
            //map.put("reply_to_id", "2");
            //  map.put("comment_id","4");
           // map.put("comment_id", "103");

            // map.put("occ_id","701");
            // map.put("content","剩的剩的所多未收到2年后22112洞察束带2年后22112洞察束带2年后22112洞察束带2年后22112洞察束带2");
            //  map.put("user_id","2");
            //  map.put("type","6");
            // map.put("author","asasasas");
            //  map.put("pic",JSON.toJSONString(picModelList));
            Date now = new Date();
            long ys = DateUtils.truncate(now, Calendar.YEAR).getTime();
            long ds = DateUtils.truncate(now, Calendar.DAY_OF_MONTH).getTime();
            // long yd = DateUtils.truncate(date, Calendar.DAY_OF_MONTH).getTime();
            System.out.println("mnnnnnnnnnnn" + ys + "-------------" + ds + "---" + new Gson().toJson(new Date()));


        /*
        Map<String,String> map=new HashMap<String, String>();
        map.put("title","年后22112洞察束带2年后22112洞察束带2年后22112洞察束带2年后22112洞察束带2");
        map.put("content","剩的剩的所多未收到2年后22112洞察束带2年后22112洞察束带2年后22112洞察束带2年后22112洞察束带2");
        map.put("user_id","2");
        map.put("type","6");
        map.put("author","asasasas");
        map.put("pic",JSON.toJSONString(picModelList));
        */

            String json = JSON.toJSONString(map);

            Map<String, String> parammap = new HashMap<String, String>();
            //  map.put("userName",name);/userinfo/userreg
            String args = Utils.urlEncoder(json);
            parammap.put("args", args);
            //  map.put("userPassword",password); //http://192.168.3.199:8080/myadmin
            parammap.put("sign", CommonParams.getHttpSign(json));


            String action = "occupation/getCommentList";//"userinfo/userlogin";
            String ret = HttpPostUrl.sendPostNew("http://localhost:8080/myadmin/" + action, parammap);
            writeJson(response, ret);
       // }

        /*

        for (int i=1;i<100;i++) {
            int num=9;
             if(i%2==0)
             {
                 num=1;
             }else{
                 num=9;
             }
            List<PicModel> picModelList=new ArrayList<PicModel>();
            for(int m=0;m<num;m++)
            {
                PicModel picModel=new PicModel();
                if(m==0) {
                    picModel.setPicUrl("http://f.hiphotos.baidu.com/image/pic/item/503d269759ee3d6db032f61b48166d224e4ade6e.jpg");
                    picModel.setThumbnailUrl("http://f.hiphotos.baidu.com/image/pic/item/503d269759ee3d6db032f61b48166d224e4ade6e.jpg");
                }else if(m==1)
                {
                    picModel.setPicUrl("http://a.hiphotos.baidu.com/image/pic/item/500fd9f9d72a6059f550a1832334349b023bbae3.jpg");
                    picModel.setThumbnailUrl("http://a.hiphotos.baidu.com/image/pic/item/500fd9f9d72a6059f550a1832334349b023bbae3.jpg");
                }else if(m==2)
                {
                    picModel.setPicUrl("http://g.hiphotos.baidu.com/image/pic/item/c8ea15ce36d3d539f09733493187e950342ab095.jpg");
                    picModel.setThumbnailUrl("http://g.hiphotos.baidu.com/image/pic/item/c8ea15ce36d3d539f09733493187e950342ab095.jpg");
                }else if(m==3)
                {
                    picModel.setPicUrl("http://b.hiphotos.baidu.com/image/pic/item/58ee3d6d55fbb2fb4a944f8b444a20a44723dcef.jpg");
                    picModel.setThumbnailUrl("http://b.hiphotos.baidu.com/image/pic/item/58ee3d6d55fbb2fb4a944f8b444a20a44723dcef.jpg");
                }else if(m==4)
                {
                    picModel.setPicUrl("http://a.hiphotos.baidu.com/image/pic/item/d6ca7bcb0a46f21f46612acbfd246b600d33aed5.jpg");
                    picModel.setThumbnailUrl("http://a.hiphotos.baidu.com/image/pic/item/d6ca7bcb0a46f21f46612acbfd246b600d33aed5.jpg");
                }else{
                    picModel.setPicUrl("http://d.hiphotos.baidu.com/image/pic/item/a044ad345982b2b713b5ad7d3aadcbef76099b65.jpg");
                    picModel.setThumbnailUrl("http://d.hiphotos.baidu.com/image/pic/item/a044ad345982b2b713b5ad7d3aadcbef76099b65.jpg");
                }
                picModelList.add(picModel);
            }

            Map<String,String> map=new HashMap<String, String>();
            map.put("title","年后22112洞察束带2年后22112洞察束带2年后22112洞察束带2年后22112洞察束带2");
            map.put("content","剩的剩的所多未收到2年后22112洞察束带2年后22112洞察束带2年后22112洞察束带2年后22112洞察束带2");
            map.put("user_id","2");
            map.put("type","6");
            map.put("author","asasasas");
            map.put("pic",JSON.toJSONString(picModelList));

            String json=JSON.toJSONString(map);

            Map<String,String> parammap=new HashMap<String, String>();
            //  map.put("userName",name);/userinfo/userreg
            String args=Utils.urlEncoder(json);
            parammap.put("args",args);
            //  map.put("userPassword",password); //http://192.168.3.199:8080/myadmin
            parammap.put("sign", CommonParams.getHttpSign(json));




            String action = "occupation/addOccupation";//"userinfo/userlogin";
            String ret = HttpPostUrl.sendPostNew("http://localhost:8080/myadmin/" + action, parammap);
            writeJson(response, ret);
       }
       */

    }



    @RequestMapping(value = "/userreg", method = RequestMethod.POST)
    public  void  regUser(HttpServletRequest request,HttpServletResponse response,String userName,String userPassword)
    {
        String name=userName;
        String password=userPassword;
        if(Utils.isEmpty(name))
        {
            writeJson(response,(new JsonResult<String>("", RequestCode.RegNoName.getIndex()+"", RequestCode.RegNoName.getName())));
            return;
        }

        if(Utils.isEmpty(password))
        {
            writeJson(response,new JsonResult<String>("",
                    RequestCode.RegNoPass.getIndex()+"", RequestCode.RegNoPass.getName()));
            return;
        }

        UserInfo user=userInfoService.checkUserName(name);
        if(user!=null)
        {
            writeJson(response,new JsonResult<String>("",
                    RequestCode.UserNameExist.getIndex()+"", RequestCode.UserNameExist.getName()));
            return;
        }else {
            UserInfo userInfo=new UserInfo();
            userInfo.setUserName(name);
            userInfo.setUserPassword(password);
            userInfo.setStatus(1);
            long userId=userInfoService.insertUser(userInfo);
            if(userId>0)
            {
                writeJson(response,new JsonResult<String>("", RequestCode.RegSucc.getIndex()+"", RequestCode.RegSucc.getName()));
                return;
            }else{
                writeJson(response,new JsonResult<String>("",
                        RequestCode.RegFail.getIndex()+"", RequestCode.RegFail.getName()));
                return;
            }
        }
    }


    /*
    //下面方法 ，是需要验证参数的方法
    @ValidateGroup(fileds = {
            //index=0 表示下面方法的第一个参数，也就是person  nutNull=true 表示不能为空
            @ValidateFiled(index=2 , notNull=true ) ,
            //index=0 表示第一个参数  filedName表示该参数的一个属性 ，也就是person.id 最小值为3 也就是 person.id 最小值为3
          //  @ValidateFiled(index=0 , notNull=true , filedName="id" , minVal = 3) ,
            //表示第一个参数的name 也就是person.name属性最大长度为10，最小长度为3
           // @ValidateFiled(index=0 , notNull=true , filedName="name" , maxLen = 10 , minLen = 3 ) ,
            //index=1 表示第二个参数最大长度为5，最小长度为2
            @ValidateFiled(index=3 , notNull=true , maxLen = 20 , minLen = 6 ) ,
          //  @ValidateFiled(index=2 , notNull=true , maxVal = 100 , minVal = 18),
          //  @ValidateFiled(index=3 , notNull=false , regStr= "^\\w+@\\w+\\.com$" )
    })
    */


    @RequestMapping(value = "/userlogin", method = RequestMethod.POST)
    @ResponseBody
    public Results login(HttpServletRequest request, HttpServletResponse response, String userName, String userPassword)
    {
        String name=userName;
        String password=userPassword;
        return  userInfoService.loginTest(name,password);
    }

}
