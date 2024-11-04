package com.admin.model;

import java.util.List;

public class AdminService {

	private AdminJDBCDAO_interface dao;

	public AdminService() {
		dao = new AdminJDBCDAO();
	}

	public AdminVO addAdmin(String adminname,
			String adminaccstatus,String adminacc,String adminpwd) {

		AdminVO adminVO = new AdminVO();

		adminVO.setAdminname(adminname);
		adminVO.setAdminaccstatus(adminaccstatus);
		adminVO.setAdminacc(adminacc);
		adminVO.setAdminpwd(adminpwd);
		dao.insert(adminVO);

		return adminVO;
	}

	public AdminVO updateAdmin(Integer adminid,String adminname,
			String adminaccstatus,String adminacc,String adminpwd) {

		AdminVO adminVO = new AdminVO();

		adminVO.setAdminid(adminid);
		adminVO.setAdminname(adminname);
		adminVO.setAdminaccstatus(adminaccstatus);
		adminVO.setAdminacc(adminacc);
		adminVO.setAdminpwd(adminpwd);
		dao.update(adminVO);

		return adminVO;
	}

	public void deleteAdmin(Integer adminid) {
		dao.delete(adminid);
	}

	public AdminVO getOneAdmin(Integer adminid) {
		return dao.findByPrimaryKey(adminid);
	}

	public List<AdminVO> getAll() {
		return dao.getAll();
	}
}
	
