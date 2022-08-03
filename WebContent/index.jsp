<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
</head>
<style>
body {
	background-color: #F6F7F5;
	background-size: cover;
	background-repeat: no-repeat;
}

form {
	height: 100pt;
	width: 300pt;
	border: 1px solid grey;
	background-color: #CFD8DC;
	margin: 5px;
	text-align: center;
}

button {
	background-color: #7B8D93;
	color: white;
	padding: 14px 18px;
	border: none;
	cursor: pointer;
	width: 24%;
	margin: 10px;
}

button:hover {
	opacity: 0.7;
}

input[type=text], input[type=password] {
	width: 50%;
	padding: 8px 14px;
	margin: 8px 0;
	border: 1px solid #ccc;
	box-sizing: border-box;
	background-color: #F3F3F3;
}

#but1 {
	background-color: #7B8D93;
	color: white;
	padding: 8px;
	border: none;
	cursor: pointer;
	width: 70%;
	margin: 2px;
}

input[type=password]:hover {
	opacity: 0.7;
}

input[type=text]:hover {
	opacity: 0.7;
}

p {
	font-family: Courier New;
	font-size: 16px;
	font-style: bold;
}

h1 {
	font-family: Castellar;
	font-size: 30px;
	text-align: center;
	color: brown;
	padding: 20px 0px;
}
</style>
<body>

	<h1>
		<i>USER LOGIN</i>
	</h1>

	<center>
		<form action="Login" method="post">

			<P>
				UserName <input type="text" placeholder="Type Your Username Here"
					name="uname">
			</P>

			<P>
				Password <input type="password"
					placeholder="Type Your Password Here" name="pwd">
			</P>

			<p style="color: red"><%=request.getAttribute("errorMsg") == null ? "" : request.getAttribute("errorMsg")%></p>
		
			<button type="submit">Login</button>

			<button id=but1 type="submit" formaction="register.jsp">
				Create New User Account</button>

		</form>
	</center>

</body>
</html>
