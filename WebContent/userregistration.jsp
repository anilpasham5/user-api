<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Registration</title>
</head>
<body>
<h3 style="color:green" align="center">User Registration</h3>
<div align="center">
<form action="./userreg" method="post" onsubmit="return validate()">
<table>
<tr><td>Enter Your First Name:</td><td><input name="userFirstName"  id="userFirstName"  onchange="checkUserFirstName(this.value)">
<span id="fntxt" style="color:red" ></span>
</td></tr>
<tr><td>Enter Your Last Name:</td><td><input name="userLastName" id="userLastName" onchange="checkUserLastName(this.value)">
<span id="sntxt" style="color:red"></span>
</td></tr>
<tr><td>Enter Your EmailId:</td><td><input name="userEmailId" id="userEmailId" onchange="checkEmailId(this.value)">
<span id="txtHint" style="color:red"></span>
</td>
</tr>
<tr><td>Enter Password:</td><td><input id="userPassword" name="userPassword" type="password" onchange="checkPassword(this.value)">
<span id="pwdtxt" style="color:red"></span>
</td></tr>
<tr><td>Reenter Password:</td><td><input name="userPassword2" type="password"  id="reUserPassword" onchange="recheckPassword(this.value)">
<span id="repwdtxt" style="color:red"></span>
</td></tr>
<tr><td>Enter Your Mobile Number:</td>
<td><span>+91</span><input name="userContactNumber" id="userContactNumber" onchange="checkMobileNumber(this.value)">
<span id="mobiletxt" style="color:red"></span>
</td></tr>
</table>
<div align="center"><input type="submit" value="Register">
</div>
</form>
</div>
<script type="text/javascript">
function checkEmailId(userEmailId) {
	  var ruserEmailId=new RegExp("^([A-z0-9.]{4,45})@([A-z]{4,25}).com$");
	  if(!ruserEmailId.test(userEmailId)) {
		  document.getElementById("txtHint").innerHTML = "EmailId must be in XXXXX@YYYYY.com format ";
	  }
	  else{
	  var xhttp;
	  if (userEmailId.length == 0) { 
	    document.getElementById("txtHint").innerHTML = "";
	    return;
	  }
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	      document.getElementById("txtHint").innerHTML = xhttp.responseText;
	    }
	  };
	  xhttp.open("POST", "checkemail?q="+userEmailId, true);
	  xhttp.send();
	}}
function checkMobileNumber(userMobileNumber){
	var ruserMobileNumber=new RegExp("^([789])([0-9]{9})$");
	if(!ruserMobileNumber.test(userMobileNumber)){
		document.getElementById("mobiletxt").innerHTML = "Please Enter Valid Mobile Number";
		return;
	}
	else document.getElementById("mobiletxt").innerHTML = "";
}
function checkName(userName){
	var rUserName=new RegExp("^[A-z ]{4,25}$");
	return rUserName.test(userName);
}
function checkUserFirstName(userFirstName){
	if(!checkName(userFirstName)) document.getElementById("fntxt").innerHTML = "Name Allows only Alphabets and spaces Min 4 and Max 25";
	else document.getElementById("fntxt").innerHTML = "";
}
function checkUserLastName(userLastName){
	if(!checkName(userLastName)) document.getElementById("sntxt").innerHTML = "Name Allows only Alphabets and spaces Min 4 and Max 25";
	else document.getElementById("sntxt").innerHTML = "";
}
function checkPassword(userPassword){
	var ruserPassword=new RegExp("^[A-z0-9!@#$*._]{4,25}$");
	if(ruserPassword.test(userPassword))
		document.getElementById("pwdtxt").innerHTML = "";
	else document.getElementById("pwdtxt").innerHTML = "Password Allows Alphabets and !@#$*. characters only min 2 max 25";
}
function recheckPassword(reuserPassword){
	var userPassword=document.getElementById("userPassword").value;
    if(reuserPassword==userPassword) document.getElementById("repwdtxt").innerHTML = "";
    else  document.getElementById("repwdtxt").innerHTML = "Passwords are not matching";
}
function validate(){
    checkUserFirstName(document.getElementById("userFirstName").value);
    checkUserLastName(document.getElementById("userLastName").value);
    checkEmailId(document.getElementById("userEmailId").value);
    checkPassword(document.getElementById("userPassword").value);
    recheckPassword(document.getElementById("reUserPassword").value);
    checkMobileNumber(document.getElementById("userContactNumber").value);
	var fntxt=document.getElementById("fntxt").innerHTML;
	var sntxt=document.getElementById("sntxt").innerHTML;
	var pwdtxt=document.getElementById("pwdtxt").innerHTML;
	var repwdtxt=document.getElementById("repwdtxt").innerHTML;
	var mobiletxt=document.getElementById("mobiletxt").innerHTML;
	var txtHint=document.getElementById("txtHint").innerHTML;
	if(fntxt==""&&sntxt==""&&pwdtxt==""&&repwdtxt==""&&mobiletxt==""&&txtHint=="") return true;
	else{
		return false;
	}
}
</script>
</body>
</html>