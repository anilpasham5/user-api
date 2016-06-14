<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload Image</title>
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
%>
<body>
<h4 style="color:green">Hello <%=userEmailId %></h4>
<div align="right">
<form action="./logout"><button type="submit">Logout</button></form>
</div>
<div align="center">
<form action="./uploadimage" method="post" enctype="multipart/form-data" onsubmit="return validate()">
<table>
<tr><td>Upload Image:</td><td><input type="file" name="userImagePath" id="userImagePath"></td></tr>
</table>
<div align="center">
<input type="submit" value="Upload">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="reset" value="Clear Path">
</div>
</form>
</div>
<script type="text/javascript">
function validate(){
	var userImagePath=document.getElementById("userImagePath").value;
	if(userImagePath==null||userImagePath=="") {
		alert("Please Select Path");return false;
	}
	else return true;
}
</script>
</body>
</html>