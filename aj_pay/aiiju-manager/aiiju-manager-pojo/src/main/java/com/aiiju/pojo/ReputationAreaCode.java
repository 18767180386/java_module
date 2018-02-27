package com.aiiju.pojo;

import java.io.Serializable;

/** 
 * @ClassName ReputationAreaCode
 * @Description
 * @author zong
 * @CreateDate 2017年5月25日 下午3:03:20
 */
public class ReputationAreaCode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String area_code;
	
	private String area_name;
	
	private String area_parentid;
	
	private String area_type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getArea_parentid() {
		return area_parentid;
	}

	public void setArea_parentid(String area_parentid) {
		this.area_parentid = area_parentid;
	}

	public String getArea_type() {
		return area_type;
	}

	public void setArea_type(String area_type) {
		this.area_type = area_type;
	}


	
	
}
