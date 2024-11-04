<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.admin.model.*"%>

<%AdminVO adminVO = (AdminVO) request.getAttribute("adminVO");%>
<!-- 查看adminVO是否空值 -->
<%-- --<%= adminVO==null %>--${adminVO.adminid} --%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome,opera=1"/>
    <title>管理員資料新增 - addAdmin.jsp</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/back-end/admin/addAdmin.css" %>">
    <style>
    </style>
</head>

<body>
<table id="table-1">
    <tr>
        <td>
            <div class="content">
                <h1 >
    <img src="<%= request.getContextPath() + "/back-end/images/pflogow.png" %>" width="ˊ70" height="70" border="0" style="vertical-align: bottom;" />
    	管理員資料新增
    <img src="<%= request.getContextPath() + "/back-end/images/pflogow.png" %>" width="70" height="70" border="0" style="vertical-align: bottom;" />
				</h1>

                <h2 >- addAdmin.jsp</h2>
                <h4><a href="select_page.jsp">回首頁</a></h4>
            </div>
        </td>
    </tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="admin.do" name="form1">
	<table id="table-2">
	<h2 id="pt">資料新增:</h2>
	<table id="table-3">
	<tr>
		<td id="text">管理員姓名:<font color="red"><b>*</b></font></td>
		<td><input type="TEXT" name="adminname" value="<%= (adminVO==null)? "管理員" : adminVO.getAdminname()%>" size="30"/></td>
	</tr>
	
	<tr>
    <td id="text">管理員帳號狀態:</td>
    <td>
        <label>
            <input type="radio" name="adminaccstatus" value="0" 
                <c:if test="${adminVO.adminaccstatus == 0}">checked</c:if>
            /> 0
        </label>
        <label>
            <input type="radio" name="adminaccstatus" value="1" 
                <c:if test="${adminVO.adminaccstatus == 1 ||adminVO.adminaccstatus == null}">checked</c:if>
            /> 1
        </label>
    </td>
</tr>
	
	<tr>
		<td id="text">管理員帳號:<font color="red"><b>*</b></font></td>
		<td><input type="TEXT" name="adminacc" value="<%= (adminVO==null)? "PF1" : adminVO.getAdminacc()%>" size="12"/></td>
	</tr>
	<tr>
		<td id="text">管理員密碼:<font color="red"><b>*</b></font></td>
		<td><input type="TEXT" name="adminpwd"   value="<%= (adminVO==null)? "xxxxxxxx" : adminVO.getAdminpwd()%>" size="12"/></td>
	</tr>
	</table>

</table>
<br>
<input id= "sub"type="hidden" name="action" value="insert">
<input id= "sub" type="submit" value="送出新增"></FORM>

</body>

</html>