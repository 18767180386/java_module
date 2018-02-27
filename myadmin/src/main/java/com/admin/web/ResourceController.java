package com.admin.web;


import com.admin.entity.*;
import com.admin.service.ResourceService;
import com.admin.util.Utils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/resource")
public class ResourceController  extends ApiController{
    @Autowired
    private ResourceService  resourceService;

    //@RequestMapping("permissionadd")
    @RequestMapping(value = "/permissionadd", method = RequestMethod.GET)
    public ModelAndView  permissionAdd(String id,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin-permission-add");
        Resource resource=null;
        if(id!=null)
        {
          //  System.out.println("--------"+id);
             resource=resourceService.getResourceById(Integer.valueOf(id));
           // System.out.println("--------"+resource.getTitle());
            mav.addObject("resource",resource);
        }else
        {
            mav.addObject("resource",new Resource());
        }
        List<Resource>  list=resourceService.getParentResourceList(0);
        mav.addObject("resourcelist", list);
        str="";
        TreeHtml((resource!=null?resource.getParentId():0));
        mav.addObject("selecthtml",str);
        System.out.println("--------"+list.size());
        return mav;
          /*
                <c:forEach items="${resourcelist}" var="node">
                      <option value="${node.id}" <c:if test="${resource.parentId== node.id}"> selected="selected"  </c:if>  >${node.title}</option>
                  </c:forEach>
                  */
    }

    @RequestMapping("permissionaddaction")
    public void  permissionAddAction(Resource  resource, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(JSONObject.toJSONString(resource));
        if(resource!=null) {
            if(resource.getId()>0) {
                resourceService.updateResource(resource);
            }else {
                resourceService.addResource(resource);
            }
        }
        writeJson(response,"1");
    }

    @RequestMapping("adminpermission")
    public ModelAndView  adminPermission(HttpServletRequest request, String title, Integer pageNum, Integer pageSize) {
        pageNum=pageNum==null?1:pageNum;
        int offset = pageNum;// 默认便宜0
        int limit = pageSize==null ? 10 : pageSize;// 默认展示50条
        offset = (offset - 1) * limit;
        String keyword = title==null? "" : title;
        List<User> uList=new ArrayList<User>();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin-permission");
        List<Resource> list = resourceService.getResourceList(keyword,offset,limit);
        int count=resourceService.getResourceCount(keyword);
        int totalPageNum = (count +  limit  - 1) / limit;
        mav.addObject("count",count);
        mav.addObject("totalpagenum",totalPageNum);
        mav.addObject("curpage",pageNum);
        mav.addObject("resourcelist", list);
        return mav;
    }

    @RequestMapping(value = "/delresource", method = RequestMethod.POST)
    public  void delResourceById(String id, HttpServletRequest request, HttpServletResponse response)
    {
        if(id==null)
        {
            id="0";
        }
        resourceService.delResource(Integer.valueOf(id));
        writeJson(response,"1");
    }



    @RequestMapping(value = "/delAllResource", method = RequestMethod.POST)
    public void delAllUser(String ids, HttpServletRequest request, HttpServletResponse response){
        if(ids!=null) {
            resourceService.batchDelete(ids.split(","));
        }
        writeJson(response,"1");
    }

    @RequestMapping("adminrole")
    public ModelAndView  adminRole(HttpServletRequest request, String title, Integer pageNum, Integer pageSize) {
        pageNum=pageNum==null?1:pageNum;
        int offset = pageNum;// 默认便宜0
        int limit = pageSize==null ? 10 : pageSize;// 默认展示50条
        offset = (offset - 1) * limit;
        String keyword = title==null? "" : title;
        List<User> uList=new ArrayList<User>();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin-role");
      //  List<Role> list = resourceService.getRoleList(keyword,offset,limit);
        List<Role> list = resourceService.getRoleListNew(keyword,offset,limit);
        int count=resourceService.getRoleCountNew(keyword);
        int totalPageNum = (count +  limit  - 1) / limit;
        mav.addObject("count",count);
        mav.addObject("totalpagenum",totalPageNum);
        mav.addObject("curpage",pageNum);
        mav.addObject("rolelist", list);
        return mav;
    }



    @RequestMapping(value = "/roleadd", method = RequestMethod.GET)
    public ModelAndView  roleAdd(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin-role-add");
        List<Resource>  list=resourceService.getParentResourceList(0);
        List<TreeNode> tList=new ArrayList<TreeNode>();
        for (int i=0;i<list.size();i++)
        {
            TreeNode treeNode=new TreeNode();
            treeNode.setId(list.get(i).getId());
            treeNode.setName(list.get(i).getTitle());
            treeNode.setpId(list.get(i).getParentId());
            tList.add(treeNode);
        }
       // System.out.println(JSONObject.toJSONString(tList));
        mav.addObject("role",new Role());
        mav.addObject("resourcelist", JSONObject.toJSONString(tList));
        return mav;
    }




    @RequestMapping("roleaddaction")
    public  void  roleAddAction(Role role, HttpServletRequest request, HttpServletResponse response)
    {
        if(role.getId()>0) {
            resourceService.updateRole(role);
           // addResource(role.getRids(),role.getId());
        }else{
            resourceService.addRole(role);
        }
        writeJson(response,"1");
    }


    @RequestMapping(value = "/roleedit", method = RequestMethod.GET)
    public ModelAndView  roleEdit(String id,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin-role-add");
        Role role=null;
        List<RoleResource> roleResourceList=null;
        if(id!=null)
        {
            role=resourceService.getRole(Integer.valueOf(id));
            mav.addObject("role",role);
            roleResourceList=resourceService.getRoleResourceInfo(Integer.valueOf(id));
        }
        List<Resource>  list=resourceService.getParentResourceList(0);
        List<TreeNode> tList=new ArrayList<TreeNode>();
        for (int i=0;i<list.size();i++)
        {
            TreeNode treeNode=new TreeNode();
            treeNode.setId(list.get(i).getId());
            treeNode.setName(list.get(i).getTitle());
            treeNode.setpId(list.get(i).getParentId());
            if(roleResourceList!=null) {
                for (int k = 0; k < roleResourceList.size(); k++) {
                      if(list.get(i).getId()==roleResourceList.get(k).getResourceId())
                      {
                          treeNode.setChecked(true);
                          break;
                      }
                }
            }
            tList.add(treeNode);
        }
       // System.out.println(JSONObject.toJSONString(tList));
        mav.addObject("resourcelist", JSONObject.toJSONString(tList));
        return mav;
    }


    @RequestMapping(value = "/delrole", method = RequestMethod.POST)
    public  void delRole(String id, HttpServletRequest request, HttpServletResponse response)
    {
        if(id==null)
        {
            id="0";
        }
        resourceService.delRoleById(Integer.valueOf(id));
        writeJson(response,"1");
    }


    @RequestMapping(value = "/delAllrole", method = RequestMethod.POST)
    public void delAllRole(String ids, HttpServletRequest request, HttpServletResponse response){
        if(ids!=null) {
            resourceService.batchDeleteRole(ids.split(","));
        }
        writeJson(response,"1");
    }


    @RequestMapping("roleeditaction")
    public  void  roleEditAction(Role role, HttpServletRequest request, HttpServletResponse response)
    {
        /*
        resourceService.addRole(role);
        if(role.getId()>0)
        {
            String ids=role.getRids();
            if(!Utils.isEmpty(ids))
            {
                ids=ids.substring(0,ids.lastIndexOf(","));
                String[] arr=ids.split(",");
                List<RoleResource> rList=new ArrayList<RoleResource>();
                for (int i=0;i<arr.length;i++)
                {
                    RoleResource roleResource=new RoleResource();
                    roleResource.setRoleId(role.getId());
                    roleResource.setResourceId(Integer.valueOf(arr[i]));
                    rList.add(roleResource);
                }
                if(rList.size()>0)
                {
                    resourceService.addRoleResourseByList(rList);
                }
            }

        }
        */
        writeJson(response,"1");
    }


    public boolean HasChild(int pid) {
        boolean flag = false;
        List<Resource> list=resourceService.getResourceInfo(pid);
        if(list!=null && list.size()>0)
        {
           flag=true;
        }
        return flag;
    }


    String str="";
    public void BindChildByParent(int curId,int pid, String prefix) {
        if (this.HasChild(pid)) {
            // 得到当前父节点下的所有孩子
            List<Resource> list = this.findChildByPid(pid);
            // 循环打印当前父节点下的孩子
            for (int i = 0; i < list.size(); i++) {
                //System.out.println("|----"+prefix+list.get(i).getTitle());
                String select="";
                if(curId==list.get(i).getId())
                {
                    select="selected=\"selected\"";
                }
                str=str+"<option value='"+list.get(i).getId()+"' "+select+" >|------"+prefix+list.get(i).getTitle()+"</option>";
                if (this.HasChild(list.get(i).getId())) {
                    this.BindChildByParent(curId,list.get(i).getId(),"--");
                }
            }
        }
    }

    public List<Resource> findAllParents(int pid) {
        List<Resource> treeList=resourceService.getResourceInfo(pid);
        return treeList;
    }


    public List<Resource> findChildByPid(int pid) {
        List<Resource> treeList=resourceService.getResourceInfo(pid);
        return treeList;
    }

    public void TreeHtml(int curId) {
        List<Resource>  treeList1 = this.findAllParents(0);
        if (treeList1 != null) {
            for (int i = 0; i < treeList1.size(); i++) {
                Resource tree = treeList1.get(i);
                // 打印父节点
                String select="";
                if(curId==tree.getId())
                {
                    select="selected=\"selected\"";
                }
                str=str+"<option value='"+tree.getId()+"' "+select+" >  |--" + tree.getTitle()+"</option>";
                //System.out.println("|--" + tree.getTitle());
                // 绑定孩子
                this.BindChildByParent(curId,tree.getId(), "");
            }
        } else {
           // System.out.println("没有数据！");
        }

    }


    @RequestMapping("userpermission")
    public ModelAndView   userPermission(HttpServletRequest request, String title, Integer pageNum, Integer pageSize) {
        pageNum=pageNum==null?1:pageNum;
        int offset = pageNum;// 默认便宜0
        int limit = pageSize==null ? 10 : pageSize;// 默认展示50条
        offset = (offset - 1) * limit;
        String keyword = title==null? "" : title;
        List<User> uList=new ArrayList<User>();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user-permission");
        List<Resource> list = resourceService.getResourceList(keyword,offset,limit);
        int count=resourceService.getResourceCount(keyword);
        List<TreeNode> treeNodeList=new ArrayList<TreeNode>();
        if(list!=null && list.size()>0)
        {
            for (int i=0;i<list.size();i++)
            {
                TreeNode treeNode=new TreeNode();
                treeNode.setpId(list.get(i).getParentId());
                treeNode.setName(list.get(i).getTitle());
                treeNode.setId(list.get(i).getId());
                treeNode.setDeal(list.get(i).getUrl());
                treeNode.setOpen(list.get(i).getParentId()==0?true:false);
                treeNodeList.add(treeNode);
            }
        }
        int totalPageNum = (count +  limit  - 1) / limit;
        mav.addObject("count",count);
        mav.addObject("totalpagenum",totalPageNum);
        mav.addObject("curpage",pageNum);
        mav.addObject("resourcelist", JSONObject.toJSONString(treeNodeList));
        return mav;
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> parseTree(List<Map<String, Object>> list) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        Map<String, Object> tmpMap = new HashMap<String, Object>();
        for (int i = 0, l = list.size(); i < l; i++) {
            tmpMap.put(String.valueOf(list.get(i).get("id")), list.get(i));
        }
        for (int i = 0, l = list.size(); i < l; i++) {
            Map<String, Object> map = list.get(i);
            //tmpMap存储的均为id为key的键值对，如果以pid为key可以取出对象，则表明该元素是父级元素
            if(tmpMap.get(map.get("pid")) != null && (map.get("id") != map.get("pid"))){
                //给当前这个父级map对象中添加key为children的ArrayList
                if ((tmpMap.get(map.get("pid")) != null) && ((Map<String, Object>) tmpMap.get(map.get("pid"))).get("children") == null) {
                    ((Map<String,Object>) tmpMap.get(map.get("pid"))).put("children", new ArrayList<Map<String, Object>>());
                }
                Map<String, Object> tmap = (Map<String, Object>) tmpMap.get(map.get("pid"));
                ArrayList<Map<String, Object>> tArrayList = (ArrayList<Map<String, Object>>) tmap.get("children");
                tArrayList.add(list.get(i));
                //没有父节点
            } else {
                resultList.add(list.get(i));
            }
        }

        for (int i = 0, l = list.size(); i < l; i++) {
            System.out.println("原始数据是否变化：" + list.get(i));
        }

        Iterator<Map.Entry<String, Object>> iterator = tmpMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            System.out.println("遍历之后：" + entry.getKey() + ":" + entry.getValue());
        }
        return resultList;
    }


}
