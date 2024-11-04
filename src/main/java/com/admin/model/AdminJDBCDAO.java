package com.admin.model;

import java.util.*;
import java.sql.*;

public class AdminJDBCDAO implements AdminJDBCDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cia103g3?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "prnuto747";

	private static final String INSERT_STMT = 
		"INSERT INTO admin (adminname,adminaccstatus,adminacc,adminpwd) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT adminid,adminname,adminaccstatus,adminacc,adminpwd FROM admin order by adminid";
	private static final String GET_ONE_STMT = 
		"SELECT adminid,adminname,adminaccstatus,adminacc,adminpwd FROM admin where adminid = ?";
	private static final String DELETE = 
		"DELETE FROM admin where adminid = ?";
	private static final String UPDATE = 
		"UPDATE admin set adminname=?, adminaccstatus=?, adminacc=?, adminpwd=? where adminid = ?";

	//**************INSERT**************//
	@Override
	public void insert(AdminVO adminVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adminVO.getAdminname());
			pstmt.setString(2, adminVO.getAdminaccstatus());
			pstmt.setString(3,adminVO.getAdminacc());
			pstmt.setString(4, adminVO.getAdminpwd());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	//**************UPDATE**************//
	@Override
	public void update(AdminVO adminVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, adminVO.getAdminname());
			pstmt.setString(2, adminVO.getAdminaccstatus());
			pstmt.setString(3, adminVO.getAdminacc());
			pstmt.setString(4, adminVO.getAdminpwd());
			pstmt.setInt(5, adminVO.getAdminid());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	
	//**************DELETE**************//
	@Override
	public void delete(Integer adminid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, adminid);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	//**************GET_ONE**************//
	@Override
	public AdminVO findByPrimaryKey(Integer adminid) {

		AdminVO adminVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, adminid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// adminVo 也稱為 Domain objects
				adminVO = new AdminVO();
				adminVO.setAdminid(rs.getInt("adminid"));
				adminVO.setAdminname(rs.getString("adminname"));
				adminVO.setAdminaccstatus(rs.getString("adminaccstatus"));
				adminVO.setAdminacc(rs.getString("adminacc"));
				adminVO.setAdminpwd(rs.getString("adminpwd"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return adminVO;
	}

	//**************GET_ALL**************//
	@Override
	public List<AdminVO> getAll() {
		List<AdminVO> list = new ArrayList<AdminVO>();
		AdminVO adminVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				adminVO = new AdminVO();
				adminVO.setAdminid(rs.getInt("adminid"));
				adminVO.setAdminname(rs.getString("adminname"));
				adminVO.setAdminaccstatus(rs.getString("adminaccstatus"));
				adminVO.setAdminacc(rs.getString("adminacc"));
				adminVO.setAdminpwd(rs.getString("adminpwd"));
				list.add(adminVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {

		AdminJDBCDAO dao = new AdminJDBCDAO();

		//**************新增**************//
//		AdminVO AdminVO1 = new AdminVO();
//		AdminVO1.setAdminname("袁光勝");
//		AdminVO1.setAdminaccstatus("1");
//		AdminVO1.setAdminacc("PF1013");
//		AdminVO1.setAdminpwd("12345678");
//		dao.insert(AdminVO1);

		//**************修改**************//
//		AdminVO AdminVO2 = new AdminVO();
//		AdminVO2.setAdminid(1019);
//		AdminVO2.setAdminname("王昇至");
//		AdminVO2.setAdminaccstatus("1");
//		AdminVO2.setAdminacc("PF1013");
//		AdminVO2.setAdminpwd("12348333");
//		dao.update(AdminVO2);

		//**************刪除**************//
//		dao.delete(1019);
//		dao.delete(1020);

		//**************查詢ONE**************//
//		AdminVO AdminVO3 = dao.findByPrimaryKey(1001);
//		System.out.print(AdminVO3.getAdminid() + ",");
//		System.out.print(AdminVO3.getAdminname() + ",");
//		System.out.print(AdminVO3.getAdminaccstatus() + ",");
//		System.out.print(AdminVO3.getAdminacc() + ",");
//		System.out.print(AdminVO3.getAdminpwd() + ",");
//		System.out.println("---------------------");
		
		//**************查詢ALL**************//
//		List<AdminVO> list = list = dao.getAll();
//		for (AdminVO aAdmin : list) {
//			System.out.print(aAdmin.getAdminid() + ",");
//			System.out.print(aAdmin.getAdminname() + ",");
//			System.out.print(aAdmin.getAdminaccstatus() + ",");
//			System.out.print(aAdmin.getAdminacc() + ",");
//			System.out.print(aAdmin.getAdminpwd() + ",");
//			System.out.println("---------------------");
//			}
	}
}