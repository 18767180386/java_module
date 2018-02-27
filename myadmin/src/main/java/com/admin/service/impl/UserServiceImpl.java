package com.admin.service.impl;

import com.admin.dao.UserDao;
import com.admin.entity.PermissionsList;
import com.admin.entity.Role;
import com.admin.entity.User;
import com.admin.entity.UserRole;
import com.admin.service.UserService;
import com.admin.util.Utils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/16.
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserDao userDao;
	/*@Autowired
	private RedisCache cache;*/


    @Override
    public List<User> getUserLists(int offset, int limit) {
//		String cache_key=RedisCache.CAHCENAME+"|getUserList|"+offset+"|"+limit;
        //先去缓存中取
        List<User> result_cache = null;
//		List<User> result_cache=cache.getListCache(cache_key, User.class);
        if (result_cache == null) {
            //缓存中没有再去数据库取，并插入缓存（缓存时间为60秒）
            result_cache = userDao.queryAll(offset, limit);
//			cache.putListCacheWithExpireTime(cache_key, result_cache, RedisCache.CAHCETIME);
//			LOG.info("put cache with key:"+cache_key);
        } else {
//			LOG.info("get cache with key:"+cache_key);
        }
        return result_cache;
    }


    @Override
    public boolean addUser(User user) {
//		String cache_key=RedisCache.CAHCENAME+"|getUserList|"+offset+"|"+limit;
        //先去缓存中取
        userDao.addUser(user);
        if(user.getUserId()>0)
        {
            if(!Utils.isEmpty(user.getIds()))
            {
                String[] arr=user.getIds().split(",");
                List<UserRole> uList=new ArrayList<UserRole>();
                for (int i=0;i<arr.length;i++)
                {
                    UserRole userRole=new UserRole();
                    userRole.setUserId((int)user.getUserId());
                    userRole.setRoleId(Integer.valueOf(arr[i]));
                    uList.add(userRole);
                }

                addUserRoleList(uList);
            }
        }
        return true;
    }


    @Override
    public void delUser(String userid) {
//		String cache_key=RedisCache.CAHCENAME+"|getUserList|"+offset+"|"+limit;
        //先去缓存中取

        userDao.delUser(userid);
    }


    @Override
    public int  login(User user) throws Exception {
        int ret=userDao.login(user);
        if(ret==1)
        {
            /*
            User _user=new User();
            _user.setCreateTime(DateUtil.dateFormatAll(new Date()));
            _user.setScore(1);
            _user.setPic("ssdd");
            _user.setSex(1);
            _user.setStatus(1);
            _user.setUserEmail("112@ssdsd");
            _user.setUserPhone("as");
            _user.setUserName("admin--2");
            _user.setUserPassword("123456");
             userDao.addUser(_user);
             */
            ret=userDao.updateLoginInfo(user);
        }
        return  ret;
    }



    @Override
    public   void addUserList(List<User> item)
    {
        userDao.addUserList(item);
    }


    @Override
    public   List<User>  getUserList(List<User> user,String keyword,int offset,int limit)
    {
           // userDao.addUserList(user);
        return   userDao.getUserList(keyword,offset,limit);
    }


    @Override
    public  void  falseDelete(String[] checkedId)
    {
        userDao.falseDelete(checkedId);
    }


    @Override
    public int getUserCount(@Param("keyword") String keyword)
    {
        return userDao.getUserCount(keyword);
    }

    /**
     *
     * 批量添加用户角色
     * @param item
     */
    @Override
    public void addUserRoleList(List<UserRole> item)
    {
        userDao.addUserRoleList(item);
    }

    /**
     *
     *
     * @param userid
     * @return
     */
    @Override
    public  User  getUserById(@Param("userid") int userid)
    {
        return userDao.getUserById(userid);
    }

    @Override
    public List<UserRole>  getUserRoleById(@Param("userid") int userid)
    {
        return  userDao.getUserRoleById(userid);
    }


    /**
     *
     * 更改用户信息
     * @param user
     */
    @Override
    public   void updateUserInfoByUser(User user)
    {
        userDao.updateUserInfoByUser(user);
        delUserRoleByUserId((int)user.getUserId());
        if(user.getUserId()>0)
        {
            if(!Utils.isEmpty(user.getIds()))
            {
                String[] arr=user.getIds().split(",");
                List<UserRole> uList=new ArrayList<UserRole>();
                for (int i=0;i<arr.length;i++)
                {
                    UserRole userRole=new UserRole();
                    userRole.setUserId((int)user.getUserId());
                    userRole.setRoleId(Integer.valueOf(arr[i]));
                    uList.add(userRole);
                }
                addUserRoleList(uList);
            }
        }
    }

    /**
     *
     *
     * @param userid
     */
    @Override
    public  void delUserRoleByUserId(@Param("userid") int userid)
    {
        userDao.delUserRoleByUserId(userid);
    }



    /**
     *
     *
     * @param userName
     * @return
     */
    @Override
    public   User  getUserByName(@Param("userName") String  userName)
    {
        return  userDao.getUserByName(userName);
    }


    @Override
    public   void updateUserStatus(@Param("status") int status,@Param("userid") int userid)
    {
        userDao.updateUserStatus(status,userid);
    }

    /**
     *
     *
     * @param userid
     * @return
     */
    @Override
    public Role getUserRoleByUserId(@Param("userid") int userid)
    {
        return  userDao.getUserRoleByUserId(userid);
    }



    /**
     *
     *
     * @param userid
     * @return
     */
    @Override
    public   List<PermissionsList> getPermissionsListByUserId(@Param("userid") int userid)
    {
        return userDao.getPermissionsListByUserId(userid);
    }

}