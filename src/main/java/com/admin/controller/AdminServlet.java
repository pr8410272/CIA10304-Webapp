package com.admin.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.admin.model.*;

public class AdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	    
	    //********************************getOne_For_Display********************************//
	    
	    if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

	        List<String> errorMsgs = new LinkedList<String>();
	        req.setAttribute("errorMsgs", errorMsgs); // 儲存錯誤訊息

	        /***** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
	        String str = req.getParameter("adminid");
	        if (str == null || str.trim().isEmpty()) {
	            errorMsgs.add("請輸入管理員編號");
	        }

	        Integer adminid = null;
	        try {
	            adminid = Integer.valueOf(str);
	        } catch (NumberFormatException e) {
	            errorMsgs.add("管理員編號格式不正確");
	        }

	        // 如果有錯誤，轉發回選擇頁面
	        if (!errorMsgs.isEmpty()) {
	            RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/select_page.jsp");
	            failureView.forward(req, res);
	            return; // 結束當前處理
	        }

	        /*************************** 2.開始查詢資料 *****************************************/
	        AdminService adminSvc = new AdminService();
	        AdminVO adminVO = adminSvc.getOneAdmin(adminid);
	        if (adminVO == null) {
	            errorMsgs.add("查無此管理員");
	        }

	        // 如果查詢結果有錯誤，轉發回選擇頁面
	        if (!errorMsgs.isEmpty()) {
	            RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/select_page.jsp");
	            failureView.forward(req, res);
	            return; // 結束當前處理
	        }

	        /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
	        req.setAttribute("adminVO", adminVO); // 資料庫取出的adminVO物件,存入req
	        String url = "/back-end/admin/listOneAdmin.jsp";
	        RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAdmin.jsp
	        successView.forward(req, res);
	    }

	    
	  //*****************************getOne_For_Update*****************************//
	    
	    if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

	        List<String> errorMsgs = new LinkedList<String>();
	        req.setAttribute("errorMsgs", errorMsgs); // 儲存錯誤訊息

	        /*************************** 1.接收請求參數 ****************************************/
	        String str = req.getParameter("adminid");
	        if (str == null || str.trim().isEmpty()) {
	            errorMsgs.add("請輸入管理員編號");
	        }

	        Integer adminid = null;
	        try {
	            adminid = Integer.valueOf(str);
	        } catch (NumberFormatException e) {
	            errorMsgs.add("管理員編號格式不正確");
	        }

	        // 如果有錯誤，轉發回管理員列表頁
	        if (!errorMsgs.isEmpty()) {
	            RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/listAllAdmin.jsp");
	            failureView.forward(req, res);
	            return; // 結束當前處理
	        }

	        /*************************** 2.開始查詢資料 ****************************************/
	        AdminService adminSvc = new AdminService();
	        AdminVO adminVO = adminSvc.getOneAdmin(adminid);
	        if (adminVO == null) {
	            errorMsgs.add("查無此管理員");
	        }

	        // 如果查詢結果有錯誤，轉發回管理員列表頁
	        if (!errorMsgs.isEmpty()) {
	            RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/listAllAdmin.jsp");
	            failureView.forward(req, res);
	            return; // 結束當前處理
	        }

	        /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
	        req.setAttribute("adminVO", adminVO); // 資料庫取出的adminVO物件,存入req
	        String url = "/back-end/admin/update_admin_input.jsp";
	        RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 update_admin_input.jsp
	        successView.forward(req, res);
	    }
	    
	    
		  //***********************************Update***********************************//
	    
		if ("update".equals(action)) { // 來自update_admin_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer adminid =Integer.valueOf(req.getParameter("adminid").trim());

			String adminname = req.getParameter("adminname");
			String adminnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (adminname == null || adminname.trim().isEmpty()) {
				errorMsgs.add("管理員姓名請勿空白");
			} else if (!adminname.trim().matches(adminnameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String adminaccstatus = req.getParameter("adminaccstatus").trim();
			if (adminaccstatus == "0") {
				errorMsgs.add("新增帳號狀態請選1");
			}

			String adminacc = req.getParameter("adminacc").trim();
			if (adminacc == null || adminacc.trim().isEmpty()) {
				errorMsgs.add("請填寫正確的帳號");
			}

			String adminpwd = req.getParameter("adminpwd").trim();
			if (adminpwd == null || adminpwd.trim().isEmpty()) {
				errorMsgs.add("請填寫8~12位的密碼");
			}
			
			
			
		  String adminacc1 = String.valueOf(req.getParameter("adminacc").trim());

			AdminVO adminVO = new AdminVO();
			adminVO.setAdminid(adminid);
			adminVO.setAdminname(adminname);
			adminVO.setAdminaccstatus(adminaccstatus);
			adminVO.setAdminacc(adminacc1);
			adminVO.setAdminpwd(adminpwd);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("adminVO", adminVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/update_admin_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			AdminService adminSvc = new AdminService();
			adminVO = adminSvc.updateAdmin(adminid,adminname, adminaccstatus, adminacc1, adminpwd);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("adminVO", adminVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/admin/listOneAdmin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		
		
		//*************************INSERT************************//
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String adminname = req.getParameter("adminname");
			String adminnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (adminname == null || adminname.trim().length() == 0) {
				errorMsgs.add("管理員姓名: 請勿空白");
			} else if (!adminname.trim().matches(adminnameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String adminaccstatus = req.getParameter("adminaccstatus").trim();
			if (adminaccstatus == null || adminaccstatus.trim().length() == 0) {
				errorMsgs.add("¾��ФŪť�");
			}
			
			String adminacc = req.getParameter("adminacc").trim();
			if (adminacc == null || adminacc.trim().length() == 0) {
				errorMsgs.add("¾��ФŪť�");
			}
			
			String adminpwd = req.getParameter("adminpwd").trim();
			if (adminpwd == null || adminpwd.trim().length() == 0) {
				errorMsgs.add("¾��ФŪť�");
			}

			String adminacc1 = String.valueOf(req.getParameter("adminacc").trim());

			AdminVO adminVO = new AdminVO();
			adminVO.setAdminname(adminname);
			adminVO.setAdminaccstatus(adminaccstatus);
			adminVO.setAdminacc(adminacc1);
			adminVO.setAdminpwd(adminpwd);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("adminVO", adminVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("back-end/admin/addAdmin.jsp");
				failureView.forward(req, res);
				return;
			}

			/**************************** 2.開始新增資料***************************************/
			AdminService adminSvc = new AdminService();
			adminVO = adminSvc.addAdmin(adminname, adminaccstatus, adminacc1, adminpwd);

			/**************************** 3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/back-end/admin/listAllAdmin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
		
		//***********************DELETE*************************//
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer adminid = Integer.valueOf(req.getParameter("adminid"));
				
				/***************************2.開始刪除資料***************************************/
				AdminService adminSvc = new AdminService();
				adminSvc.deleteAdmin(adminid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/admin/listAllAdmin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
		System.out.println("adminname: " + req.getParameter("adminname"));
	}
}