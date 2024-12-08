<!DOCTYPE html>
<html>
	<head>
	    <title>Register</title>
		</head>
	<body>
	    <h1>Register</h1>
	    <form action="<%= request.getContextPath() %>/users/register" method="post">
	        <label for="firstName">First Name:</label>
	        <input type="text" name="firstName" id="firstName" required><br>
	        <label for="lastName">Last Name:</label>
	        <input type="text" name="lastName" id="lastName" required><br>
	        <label for="email">Email:</label>
	        <input type="email" name="email" id="email" required><br>
			<label for="phone">Phone:</label>
			<input type="tel" name="phone" id="phone" required><br>
	        <label for="password">Password:</label>
	        <input type="password" name="password" id="password" required><br>
	        <button type="submit">Register</button>
	    </form>
	</body>
</html>
