<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.*" session="true"%>
<html>
<head>
<title>user-welcome</title>
</head>
<style>
body {
	background-color: #F6F7F5;
	background-size: cover;
	background-repeat: no-repeat;
}

.info {
	background-color: black;
	text-size: 14pt;
	height: 190pt;
	width: 330pt;
	color: white;
}

#para1 {
	height: 170pt;
	width: 400pt;
	background-color: #CFD8DC;
	border: 10px solid grey;
	margin: 20px;
}

#but2 {
	background-color: #7B8D93;
	color: white;
	padding: 6px;
	border: none;
	cursor: pointer;
	width: 40%;
	margin: 3px;
}

#but3 {
	background-color: red;
	color: white;
	padding: 8px;
	font-size: 16px;
	border: none;
	cursor: pointer;
	width: 50%%;
	margin: 5px;
}

input[type=text] {
	width: 47%;
	padding: 2px;
	border: 2px solid #ccc;
	box-sizing: border-box;
	background-color: #F3F3F3;
}

h3 {
	font-family: Algerian;
	font-size: 24px;
	text-align: center;
	color: brown;
}

h4 {
	font-family: Times New Roman;
	font-size: 20px;
	color: green;
}

input[type=text]:hover {
	opacity: 0.7;
}

button:hover {
	opacity: 0.7;
}

.hide {
	display: none;
	color: red;
}

.myDIV:hover+.hide {
	display: block;
	color: red;
}

#new1 {
	font-family: Algerian;
	font-size: 19px;
	text-align: center;
	color: purple;
}

#new2 {
	font-family: Algerian;
	font-size: 18px;
	text-align: center;
	color: brown;
}
</style>
<body>

	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String user = null;
		Object obj_user = session.getAttribute("CURRENT_USER");

		if (obj_user == null) {
			System.out.println("No Server Variable");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			user = obj_user.toString();
			System.out.println(user);
		}
		String session_cookie = session.getId();
		String browser = request.getHeader("User-Agent");

		Integer counter = (Integer) session.getAttribute("counter");
		if (counter == null) {
			counter = new Integer(1);
		} else {
			counter = new Integer(counter.intValue() + 1);
		}

		session.setAttribute("counter", counter);
	%>

	<center>
		<div class="info">
			<b>Welcome: <%=user%><br> <br>Session ID: <%=session_cookie%><br>
				<br> BrowserType: <%=browser%> <br>
			<br>Session creation time: <%=new Date(session.getCreationTime())%><br>
				Last accessed time: <%=new Date(session.getLastAccessedTime())%><br>
				Number of times you've been here: <%=counter%>
			</b>
			<form action="Logout" method="post">
				<button id=but3 type="submit">Logout</button>
			</form>
		</div>
	</center>
	<!--style="position: relative; left: 1150px; top: 10px;">  -->


	<center>
		<form id=para1 action="Converter" method="post">
			<h3>-------------CONVERTER-------------</h3>
			<p id=para2>
				<input pattern="[0-1]{1,8}" type="text"
					title="A Binary Number Can have only 1 and 0 digits And Cannot exceed 8 digits"
					placeholder="Please input a binary number" name="bin-number"
					required> Convert To <Select id=123 name="DecHex">
					<option value="Decimal">Decimal</option>
					<option value="HexaDecimal">HexaDecimal</option>
				</Select><br> <br>

				<button id=but2 type="submit">Convert</button>
			</p>

			<%
				String result = session.getAttribute("Ans") + "";
			%>

			<h4>
				Answer:
				<%=result%></h4>
		</form>

		<div class="myDIV">
			<p id=new1>Hover Over Me To Get Your History.</p>
		</div>
		<div class="hide">
			<p id=new2><%=request.getAttribute("output")%></p>
		</div>
	</center>

</body>
</html>