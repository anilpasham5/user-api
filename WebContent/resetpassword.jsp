<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reset Password</title>
</head>
<body>
<jsp:useBean id="userEmailId" type="java.lang.String" scope="request"></jsp:useBean>
<div align="center">
<form action="./resetpassword" method="post"  onsubmit="return validate()">
<table>
<tr><td>Your EmailId:</td><td><input name="userEmailId" value="${userEmailId}" readonly="readonly"></td></tr>
<tr><td>Enter Password:</td><td><input name="userPassword"	type="password"  id="userPassword"   onchange="checkPassword(this.value)">
<span id="pwdtxt"></span>
</td></tr>
<tr><td>Reenter Password:</td><td><input name="reUserPassword" id="reUserPassword" type="password" onchange="reCheckPassword(this.value)">
<span id="repwdtxt"></span>
</td></tr>
</table>
<div align="center"><input type="submit" value="RESET PASSWORD"></div>
</form>
</div>
<script type="text/javascript">
function checkPassword(userPassword){
	var rUserPassword=new RegExp("^[A-z0-9!@#$&*_.]{4,16}$");
	if(rUserPassword.test(userPassword)) document.getElementById("pwdtxt").innerHTML="";
	else document.getElementById("pwdtxt").innerHTML="Password Allows only Aplhanumerics and !@#$&_. only min 4,max 16 ";
}
function reCheckPassword(reUserPassword){
	var userPassword=document.getElementById("userPassword").value;
	if(userPassword==reUserPassword) document.getElementById("repwdtxt").innerHTML="";
	else document.getElementById("repwdtxt").innerHTML="Passwords are not matched";
}
function validate(){
	checkPassword(document.getElementById("userPassword").value);
	reCheckPassword(document.getElementById("reUserPassword").value);
	var pwdtxt=document.getElementById("pwdtxt").innerHTML;
	var repwdtxt=document.getElementById("repwdtxt").innerHTML;
	if(pwdtxt==""&&repwdtxt=="") return true;
	else return false;
}
</script>
</body>
</html>