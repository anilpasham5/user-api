<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
<%
String message="";
message=request.getParameter("message");
if(message==null) message="";
%>
<div align="left" style="color:red"><h4 align="left"><%=message %></h4></div>
<h3 style="color:blue" align="center">TSS Login</h3><br>
<div align="center">
<form action="./login" method="post" onsubmit="return validate()">
<table>
<tr><td>Enter EmailId or Mobile Number:</td><td><input name="userEmailId" id="userEmailId" onchange="checkEmailId(this.value)" >
<span id="eidtxt" style="color:red"></span>
</td></tr>
<tr><td>Enter Password:</td><td><input name="userPassword" type="password"  id="userPassword"  onchange="checkPassword(this.value)">
<span id="pwdtxt" style="color:red"></span>
</td></tr>
</table>
<div align="center">
<input type="submit" value="LOGIN"><br><br>
<a href="./forgotpassword.jsp" style="color:red">Forgot Password</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="./userregistration.jsp" style="color:green">New User Click here</a>
</div>
</form>
</div>
<script type="text/javascript">
function checkEmailId(userEmailId){
	var rUserEmailId=new RegExp("^([A-z0-9._]{4,35})@([A-z]{2,35}).com$");
	if(!rUserEmailId.test(userEmailId)) 
		document.getElementById("eidtxt").innerHTML="EmailId must be in ------@------.com format only";
	else document.getElementById("eidtxt").innerHTML="";
}
function checkPassword(userPassword){
	var rCheckPassword=new RegExp("^[A-z0-9!@#$%*._]{4,16}$");
	if(!rCheckPassword.test(userPassword)) document.getElementById("pwdtxt").innerHTML="Password Allows Alphanumerics and !@#$%_. only ";
	else document.getElementById("pwdtxt").innerHTML="";
}
function validate(){
	checkEmailId(document.getElementById("userEmailId").value);
	checkPassword(document.getElementById("userPassword").value);
	var eidtxt=document.getElementById("eidtxt").innerHTML;
	var pwdtxt=document.getElementById("pwdtxt").innerHTML;
	if(eidtxt==""&&pwdtxt=="") return true;
	else return false;
}
</script>
</body>
</html>