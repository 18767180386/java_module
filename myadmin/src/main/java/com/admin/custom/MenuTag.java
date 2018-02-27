package com.admin.custom;

import com.admin.constant.GlobalConstant;
import com.admin.entity.MenuEntity;
import com.admin.entity.PermissionsList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.ArrayList;
import java.util.List;

public class MenuTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        try {
            //  User user=(User)this.pageContext.getRequest().getAttribute(GlobalConstant.USER_LOGIN);
            HttpSession session = pageContext.getSession();
            ServletRequest request = pageContext.getRequest();
            StringBuilder menu=new StringBuilder();
            List<PermissionsList>  list=(List<PermissionsList>)session.getAttribute(GlobalConstant.USER_PERMISSION);
            if(list!=null && list.size()>0)
            {
                List<MenuEntity> menuEntityList=MenuEntity.getMenuList();
                for (MenuEntity m:menuEntityList)
                {
                    menu.append(getMenu(list,m));
                }
            }
            JspWriter out = this.pageContext.getOut();
            out.println(menu.toString());
            out.flush();
        } catch(Exception e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;

    }

    //return EVAL_PAGE; // 表示处理完标签后继续执行以下的JSP网页
    // return SKIP_PAGE; //表示不处理接下来的JSP网页\


    /**
     *
     * 获取菜单
     * @param list
     * @param menu
     * @return
     */
    private  String  getMenu(List<PermissionsList>  list,MenuEntity menu)
    {
        int pid=-1;
         for(int i=0;i<list.size();i++)
         {
             if(list.get(i).getTitle().equals(menu.getMunuName()))
             {
                 pid=list.get(i).getResourceId();
                 break;
             }
         }
        StringBuilder menustr=new StringBuilder();
         List<PermissionsList> pList=new ArrayList<PermissionsList>();
         if(pid==-1)
         {
             menustr.append("");
         }else{
             for (PermissionsList p:list)
             {
                 if(p.getParentId()==pid)
                 {
                     pList.add(p);
                 }
             }

             if(pList.size()>0)
             {
                 menustr.append("<dl id=\""+menu.getMenuStyle()+"\">");
                 menustr.append("<dt><i class=\"Hui-iconfont\">"+menu.getMenutip()+";</i> "+menu.getMunuName()+"<i class=\"Hui-iconfont menu_dropdown-arrow\">&#xe6d5;</i></dt>");
                 menustr.append("<dd><ul>");
                 for (int i=0;i<pList.size();i++) {
                     menustr.append("<li><a data-href=\""+pList.get(i).getUrl()+"\" data-title=\""+pList.get(i).getTitle()+"\" href=\"javascript:void(0)\">"+pList.get(i).getTitle()+"</a></li>");
                 }
                 menustr.append(" </ul></dd></dl>");
             }
         }
      return  menustr.toString();
    }

    @Override

    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    @Override
    public void release() {
        super.release();
        //this.user = null;

    }
}