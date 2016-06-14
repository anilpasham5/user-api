<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String userEmailId=null;
try{
HttpSession session=request.getSession(false);
if(session!=null) userEmailId=session.getAttribute("userEmailId").toString();
else response.sendRedirect("login.jsp?message=You must login first");
}catch(Exception e){
	response.sendRedirect("login.jsp?message=You must login first");return;
}
String message="";
message=request.getParameter("message");
if(message==null) message="";
%>
<div><h4 align="left" style="color:green">Hi <%=userEmailId %></h4>
<div align="right">
<form action="./logout"><button type="submit">Logout</button></form>
</div></div><br>
<div align="left"><h4 align="left" style="color:orange"><%=message %></h4></div><br>
<div align="center" >
<a href="./viewprofile" shape="poly">View Profile</a><br>
<a href="./uploadimage.jsp">Click here to upload image</a>
<br><br>
<form action="./changepassword.jsp"><button type="submit">Change Password</button></form>
<form action="./viewimage.jsp"><button type="submit">View Profile Picture</button></form>
</div>
</body>
</html>