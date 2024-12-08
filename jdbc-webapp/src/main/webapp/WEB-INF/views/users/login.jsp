<!DOCTYPE html>
<html>
	<head>
	    <title>Login</title>
		</head>
	<body>
	    <h1>Login</h1>
	    <form action="<%= request.getContextPath() %>/users/login" method="post">
	        <label for="email">Email:</label>
	        <input type="email" name="email" id="email" required><br>
	        <label for="password">Password:</label>
	        <input type="password" name="password" id="password" required><br>
	        <button type="submit">Login</button>
	    </form>
	</body>
</html>
