<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.admin.model.*"%>

<%
// 取得 adminVO 物件
AdminVO adminVO = (AdminVO) request.getAttribute("adminVO");
%>
--<%=adminVO == null%>--${adminVO.adminid}--
<!-- line 100 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome,opera=1" />
<title>管理員資料修改 - update_admin_input.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>管理員資料修改 - update_admin_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="<%= request.getContextPath() + "/back-end/images/back1.gif" %>" 
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="admin.do" name="form1">
		<table>
			<tr>
				<td>管理員編號:<font color=red><b>*</b></font></td>
				<td><%=adminVO.getAdminid()%></td>
			</tr>
			<tr>
				<td>管理員姓名:</td>
				<td><input type="TEXT" name="adminname"
					value="<%=adminVO.getAdminname()%>" size="30" /></td>
			</tr>
			
		<tr>
    <td>管理員帳號狀態:</td>
    <td>
        <label>
            <input type="radio" name="adminaccstatus" value="0" 
                <c:if test="${adminVO.adminaccstatus == 0}">checked</c:if>
            /> 0
        </label>
        <label>
            <input type="radio" name="adminaccstatus" value="1" 
                <c:if test="${adminVO.adminaccstatus == 1}">checked</c:if>
            /> 1
        </label>
    </td>
</tr>
			
			<tr>
				<td>管理員帳號:</td>
				<td><input type="TEXT" name="adminacc" value="<%=adminVO.getAdminacc()%>"
					size="12" /></td>
			</tr>
			
			<tr>
				<td>管理員密碼:</td>
				<td><input type="TEXT" name="adminpwd" value="<%=adminVO.getAdminpwd()%>"
					size="12" /></td>
			</tr>
			
<!-- 					目前沒有使用adminSvc，以下可移除 -->
<%--  			<jsp:useBean id="adminSvc" scope="page"  --%>
<%-- 				class="com.admin.model.AdminService" />  --%>
			
		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="adminid" value="<%=adminVO.getAdminid()%>"> <input
			type="submit" value="送出修改">
	</FORM>
</body>

</html>