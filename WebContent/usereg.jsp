<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UserMenu</title>
</head>
<body>
<form action="./users" method="post" >
Enter Name:<input name="name"><br>
Enter email:<input name="email"><br>
Enter mobile:<input name="mobile"><br>
<input type="submit" value="REG">
</form>
<br>
<form action="./getuser" method="post">
Enter User Id:<input name="id"><input type="submit" value="GET HIM">
</form><br>
<form action="./deleteuser" method="post">
Enter User Id:<input name="id"><input type="submit" value="DELETE">
</form><br>
<form action="./getallusers" >
<button type="submit">Get All Users</button>
</form><br>
</body>
</html>