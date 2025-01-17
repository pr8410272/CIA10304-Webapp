package com.admin.model;

import java.util.*;

public interface AdminJDBCDAO_interface {
	public void insert(AdminVO adminVO);
    public void update(AdminVO adminVO);
    public void delete(Integer adminid);
    public AdminVO findByPrimaryKey(Integer adminid);
    public List<AdminVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
