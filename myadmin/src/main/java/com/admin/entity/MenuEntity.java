package com.admin.entity;

import java.util.ArrayList;
import java.util.List;

public class MenuEntity {
    private String munuName;
    private String menuStyle;
    private String menutip;
    public String getMunuName() {
        return munuName;
    }

    public void setMunuName(String munuName) {
        this.munuName = munuName;
    }

    public String getMenuStyle() {
        return menuStyle;
    }

    public void setMenuStyle(String menuStyle) {
        this.menuStyle = menuStyle;
    }

    public String getMenutip() {
        return menutip;
    }

    public void setMenutip(String menutip) {
        this.menutip = menutip;
    }

    private static String[]  menuArr={"menu-article,&#xe616,咨询管理","menu-picture,&#xe613,图片管理","menu-product,&#xe620,产品管理","menu-comments,&#xe622,评论管理","menu-member,&#xe60d,会员管理","menu-admin,&#xe62d,管理员管理","menu-tongji,&#xe61a,系统统计","menu-system,&#xe62e,系统管理"};

    public  static List<MenuEntity>  getMenuList()
    {
        List<MenuEntity> list=new ArrayList<MenuEntity>();
        for (int i=0;i<menuArr.length;i++)
        {
            String[] arr=menuArr[i].split(",");
            MenuEntity menuEntity=new MenuEntity();
            menuEntity.setMenuStyle(arr[0]);
            menuEntity.setMenutip(arr[1]);
            menuEntity.setMunuName(arr[2]);
            list.add(menuEntity);
        }
        return  list;
    }


}
