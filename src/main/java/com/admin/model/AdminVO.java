package com.admin.model;


public class AdminVO implements java.io.Serializable{
	private Integer adminid;
	private String adminname;
	private String adminaccstatus;
	private String adminacc;
	private String adminpwd;
	

	public Integer getAdminid() {
		return adminid;
	}
	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}
	
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	
	public String getAdminaccstatus() {
		return adminaccstatus;
	}
	public void setAdminaccstatus(String adminaccstatus) {
		this.adminaccstatus = adminaccstatus;
	}
	
	public String getAdminacc() {
		return adminacc;
	}
	public void setAdminacc(String adminacc) {
		this.adminacc = adminacc;
	}
	
	public String getAdminpwd() {
		return adminpwd;
	}
	public void setAdminpwd(String adminpwd) {
		this.adminpwd = adminpwd;
	}
	
}
