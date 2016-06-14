<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update</title>
</head>
<%
String userEmailId=null;
try{
HttpSession session=request.getSession(false);
if(session!=null) userEmailId=session.getAttribute("userEmailId").toString();
else response.sendRedirect("login.jsp?message=You must login first");
}catch(Exception e){
	response.sendRedirect("login.jsp?message=You must login first");return;
}
%>
<body>
<h4 style="color:green">Hello <%=userEmailId %></h4>
<div align="right">
<form action="./logout"><button type="submit">Logout</button></form>
</div>
<jsp:useBean id="user" type="com.api.tss.users.pojo.UserPojo" scope="request"></jsp:useBean>
<div align="center">
<form action="./updateuser" method="post">
<table>
<tr><td>Your EmailId:</td><td><input name="userEmailId" value="${user.userEmailId}" readonly="readonly"></td></tr>
<tr><td>Enter Your First Name:</td><td><input name="userFirstName" value="${user.userFirstName}"></td></tr>
<tr><td>Enter Your Last Name:</td><td><input name="userLastName" value="${user.userLastName}"></td></tr>
<tr><td>Enter Contact Number:</td><td><input name="userContactNumber" value="${user.userContactNumber}"></td></tr>
</table>
<div align="center">
<input type="submit" value="UPDATE">
</div>
</form>
</div>
</body>
</html>