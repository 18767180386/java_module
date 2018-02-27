package com.admin.service.impl;

import com.admin.dao.ResourceDao;
import com.admin.dao.UserDao;
import com.admin.entity.Resource;
import com.admin.entity.Role;
import com.admin.entity.RoleResource;
import com.admin.entity.User;
import com.admin.service.ResourceService;
import com.admin.util.Utils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ResourceServiceImpl  implements ResourceService{
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ResourceDao resourceDao;


    @Override
    public void addResource(Resource resource)
    {
        resourceDao.addResource(resource);
    }

    @Override
    public   void delResource(@Param("id") int id)
    {
        resourceDao.delResource(id);
    }


    @Override
    public   List<Resource> getResourceList(String keyword,int offset,int limit)
    {
        return  resourceDao.getResourceList(keyword,offset,limit);
    }

    @Override
    public    List<Resource>  getParentResourceList(@Param("id") int id)
    {
        return  resourceDao.getParentResourceList(id);
    }


    @Override
    public   void batchDelete(String[] checkedId)
    {
        resourceDao.batchDelete(checkedId);
    }


    @Override
    public   int getResourceCount(@Param("keyword") String keyword)
    {
        return resourceDao.getResourceCount(keyword);
    }


    @Override
    public   Resource  getResourceById(@Param("id") int id)
    {
        return  resourceDao.getResourceById(id);
    }


    @Override
    public void  updateResource(Resource resource)
    {
        resourceDao.updateResource(resource);
    }

    @Override
    public List<Resource>  getResourceInfo(int id)
    {
        return  resourceDao.getResourceInfo(id);
    }


    @Override
    public   int  addRole(Role role)
    {
        int ret=resourceDao.addRole(role);
        addResource(role.getRids(),role.getId());
        return  ret;
    }


    /**
     *
     * @param _ids
     * @param roleId
     */
    private  void   addResource(String _ids,int roleId)
    {
        if(roleId>0) {
            String ids = _ids;
            if (!Utils.isEmpty(ids)) {
                ids = ids.substring(0, ids.lastIndexOf(","));
                String[] arr = ids.split(",");
                List<RoleResource> rList = new ArrayList<RoleResource>();
                for (int i = 0; i < arr.length; i++) {
                    RoleResource roleResource = new RoleResource();
                    roleResource.setRoleId(roleId);
                    roleResource.setResourceId(Integer.valueOf(arr[i]));
                    rList.add(roleResource);
                }
                if (rList.size() > 0) {
                    addRoleResourseByList(rList);
                }
            }
        }
    }

    @Override
    public   void  addRoleResourseByList(List<RoleResource> item)
    {
         resourceDao.addRoleResourseByList(item);
    }


    /**
     *
     * 根据id获取记录
     * @param id
     * @return
     */
    @Override
   public    Role  getRole(@Param("id") int id)
   {
       return  resourceDao.getRole(id);
   }


    /**
     *
     * @param id
     * @return
     */
    @Override
    public   List<RoleResource>  getRoleResourceInfo(@Param("id") int id)
    {
        return resourceDao.getRoleResourceInfo(id);
    }


    @Override
    public   List<Role> getRoleList(@Param("keyword") String keyword,@Param("offset") int offset, @Param("limit") int limit)
    {
        return  resourceDao.getRoleList(keyword,offset,limit);
    }


    /**
     *
     *
     * @param keyword
     * @return
     */
    @Override
    public   int getRoleCount(@Param("keyword") String keyword)
    {
        return  resourceDao.getRoleCount(keyword);
    }


    /**
     *
     * 删除角色
     * @param id
     * @return
     */
    @Override
    public int  delRoleById(@Param("id") int id)
    {
        int ret=resourceDao.delRoleById(id);
        delRoleResourceByRoleId(id);
        return  ret;
    }


    /**
     *
     *
     * @param id
     * @return
     */
    @Override
     public int delRoleResourceByRoleId(@Param("id") int id)
    {
        return  resourceDao.delRoleResourceByRoleId(id);
    }



    /***
     *
     * 所选中的id,逻辑删除
     * @param checkedId
     */
     public void batchDeleteRole(String[] checkedId) {
         resourceDao.batchDeleteRole(checkedId);
         batchDeleteRoleResource(checkedId);
     }


    /**
     *
     *
     * @param checkedId
     */
   public void batchDeleteRoleResource(String[] checkedId) {
       resourceDao.batchDeleteRoleResource(checkedId);
   }



    /**
     *
     *
     * @param role
     */
    @Override
     public void  updateRole(Role role)
    {
         resourceDao.updateRole(role);
         delRoleResourceByRoleId(role.getId());
         addResource(role.getRids(),role.getId());
    }


    @Override
    public List<Role> getRoleListNew(@Param("keyword") String keyword,@Param("offset") int offset, @Param("limit") int limit)
    {
        return  resourceDao.getRoleListNew(keyword,offset,limit);
    }


    @Override
    public int getRoleCountNew(@Param("keyword") String keyword)
    {
        return  resourceDao.getRoleCountNew(keyword);
    }




}
