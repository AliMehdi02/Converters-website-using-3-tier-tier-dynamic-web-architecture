<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Register</title>
</head>
<style>
body {
	background-color: #F6F7F5;
	background-size: cover;
	background-repeat: no-repeat;
}

form {
	height: 200pt;
	width: 400pt;
	border: 1px solid grey;
	background-color: #CFD8DC;
	margin: 20px;
}

#but1 {
	background-color: #7B8D93;
	color: white;
	padding: 8px;
	border: none;
	cursor: pointer;
	width: 40%;
	margin: 2px;
}

button {
	background-color: #7B8D93;
	color: white;
	padding: 8px;
	border: none;
	cursor: pointer;
	width: 24%;
	margin: 1px;
	text-align: center;
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
	font-size: 25px;
	text-align: center;
	color: brown;
	padding: 30px 0px;
}

h4 {
	font-family: Castellar;
	font-size: 15px;
	text-align: center;
	color: black;
	padding: 100px 0px;
}
</style>
<body>
	<h1>
		<i>REGISTRATION FORM</i>
	</h1>
	<center>
		<form action="Store_User" method="post">

			<P>
				<b>First Name: </b> <input type="text"
					placeholder="Type Your First Name Here" name="firstName" required>
			</P>

			<p>
				<b>Last Name: </b><input type="text"
					placeholder="Type Your Last Name Here" name="lastName" required>
			</p>

			<p>
				<b>User Name: </b> <input type="text"
					placeholder="Type Your UserName Here" name="userName" required>
			</p>

			<p>
				<b>Password: </b> <input type="password"
					placeholder="Type Your Password Here" name="password" required>
			</p>

			<p style="color: red"><%=request.getAttribute("prb") == null ? "" : request.getAttribute("prb")%></p>

			<button type="submit">Register</button>
			<button id=but1 onclick="window.location.href='index.jsp';">
				Login</button>

		</form>
	</center>



</body>
</html>

