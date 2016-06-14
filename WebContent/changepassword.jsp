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
else{
	response.sendRedirect("login.jsp?message=You must login first");return;
}
}catch(Exception e){
	response.sendRedirect("login.jsp?message=You must login first");return;
}
String message="";
message=request.getParameter("message");
if(message==null) message="";
%>
<div align="left"><h4 align="left" style="color:blue">Hi <%=userEmailId %></h4></div>
<div align="right">
<form action="./logout"><button type="submit">Logout</button></form>
</div>
<div align="center"><h4 align="left" style="color:red"><%=message%></h4></div>
<div align="center">
<form action="./changepassword" method="post" onsubmit="return validate()">
<table>
<tr><td>Enter Current Password:</td>
<td>
<input type="password" id="userCurrentPassword" name="userCurrentPassword" onchange="checkCurrentPassword(this.value)">
<span id="pwdtxt" style="color:red"></span>
</td></tr>
<tr>
 <td>Enter New Password:</td>
 <td>
<input type="password" id="userNewPassword" name="userNewPassword" onchange="checkNewPassword(this.value)">
<span id="newpwdtxt" style="color:red"></span>
</td></tr>
<tr><td>Reenter New Password:</td>
<td>
<input type="password" id="confirmPassword" onchange="checkConfirmPassword(this.value)">
<span id="ctxt" style="color:red"></span>
</td></tr>
</table>
<div align="center">
<input type="submit" value="CHANGE PASSWORD">
</div>
</form>
</div>
<script type="text/javascript">
function checkCurrentPassword(userCurrentPassword){
	var ruserCurrentPassword=new RegExp("^[A-z0-9!@#$_.]{4,16}$");
	if(ruserCurrentPassword.test(userCurrentPassword)) document.getElementById("pwdtxt").innerHTML="";
	else document.getElementById("pwdtxt").innerHTML="Password Allows Alphanumerics and !@#$_. only min 4 and max 16";
}
function checkNewPassword(userNewPassword){
	var rcheckNewPassword=new RegExp("^[A-z0-9!@#$-.]{4,16}$");
	if(rcheckNewPassword.test(userNewPassword)) document.getElementById("newpwdtxt").innerHTML="";
	else document.getElementById("newpwdtxt").innerHTML="Password Allows Alphanumerics only";
}
function checkConfirmPassword(confirmPassword){
	var userNewPassword=document.getElementById("userNewPassword").value;
	if(userNewPassword==confirmPassword) document.getElementById("ctxt").innerHTML="";
	else document.getElementById("ctxt").innerHTML="Passwords are not matched";
}
function validate(){
	checkCurrentPassword(document.getElementById("userCurrentPassword").value);
	checkNewPassword(document.getElementById("userNewPassword").value);
	checkConfirmPassword(document.getElementById("confirmPassword").value);
	var pwdtxt=document.getElementById("pwdtxt").innerHTML;
	var newpwdtxt=document.getElementById("newpwdtxt").innerHTML;
	var ctxt=document.getElementById("ctxt").innerHTML;
	if(pwdtxt==""&&newpwdtxt==""&&ctxt=="") return true;
	else return false;
}
</script>
</body>
</html>