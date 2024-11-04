package com.admin.model;

import java.util.*;

public interface AdminJDBCDAO_interface {
	public void insert(AdminVO adminVO);
    public void update(AdminVO adminVO);
    public void delete(Integer adminid);
    public AdminVO findByPrimaryKey(Integer adminid);
    public List<AdminVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
