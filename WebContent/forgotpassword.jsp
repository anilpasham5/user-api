<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Forgot Password</title>
</head>
<body>
<%
String message="";
message=request.getParameter("message");
if(message==null) message="";
%>
<div align="left"><h4 style="color:red"><%=message %></h4></div>
<div align="center">
<form action="./forgotpassword" method="post" onsubmit="return validate()">
<table>
<tr><td>Enter EmailId:</td><td><input name="userEmailId" id="userEmailId" onchange="checkEmailId(this.value)">
<span id="txtHint" style="color:red"></span>
</td></tr>
<tr><td>Enter Mobile Number:</td><td><input name="userContactNumber"  id="userContactNumber" onchange="checkMobileNumber(this.value)">
<span id="mobiletxt" style="color:red"></span>
</td></tr>
</table>
<div>
<input type="submit" value="RESET PASSWORD">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="./login.jsp" >Login</a>
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
	    document.getElementById("txtHint").innerHTML = "EmailId must be in XXXXX@YYYYY.com format";
	    return;
	  }
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	      document.getElementById("txtHint").innerHTML = xhttp.responseText;
	    }
	  };
	  xhttp.open("POST", "checkEmailId?q="+userEmailId, true);
	  xhttp.send();
	}
	  }
function checkMobileNumber(userMobileNumber){
	var userEmailId=document.getElementById("userEmailId").value;
	checkEmailId(userEmailId);
	var txtHint=document.getElementById("txtHint").innerHTML;
	if(txtHint!="") return;
	var ruserMobileNumber=new RegExp("^([789])([0-9]{9})$");
	if(!ruserMobileNumber.test(userMobileNumber)){
		document.getElementById("mobiletxt").innerHTML = "Please Enter Valid Mobile Number";
		return;
	}
	else{
		  var xhttp;
		  if (userMobileNumber.length == 0) { 
		    document.getElementById("mobiletxt").innerHTML = "Enter Valid Mobile Number";
		    return;
		  }
		  xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		      document.getElementById("mobiletxt").innerHTML = xhttp.responseText;
		    }
		  };
		  xhttp.open("POST", "verifycneid?userEmailId="+userEmailId+"&userContactNumber="+userMobileNumber, true);
		  xhttp.send();
	}
}
function validate(){
         var userEmailId=document.getElementById("userEmailId").value;
         var userContactNumber=document.getElementById("userContactNumber").value;
	     checkEmailId(userEmailId);
         checkMobileNumber(userContactNumber);
         var txtHint=document.getElementById("txtHint").innerHTML;
         var mobiletxt=document.getElementById("mobiletxt").innerHTML;
         if(txtHint==""&&mobiletxt==""){
        	return true;
         }
         else return false;
 }
</script>
</body>
</html>