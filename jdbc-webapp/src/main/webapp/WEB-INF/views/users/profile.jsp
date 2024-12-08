<!DOCTYPE html>
<html>
	<head>
	    <title>User Profile</title>
		</head>
	<body>
	    <h1>User Profile</h1>
	    <p>Name: ${user.firstName} ${user.lastName}</p>
	    <p>Email: ${user.email}</p>
	    <p>Phone: ${user.phone}</p>
	    <p>Password: ${user.password}</p>
	    <p>Join Date: ${user.joinDate}</p>
	    <form action="${pageContext.request.contextPath}/movies/dashboard"
			method="get">
			<button name="back" value="back">Back</button>
		</form>
		<form action="${pageContext.request.contextPath}/rentals/list"
			method="get">
			<button name="rentals" value="rentalsk">View Your Rentals</button>
		</form>
		<form action="${pageContext.request.contextPath}/reviews/list"
			method="get">
			<button name="reviews" value="reviews">View Your Reviews</button>
		</form>
	</body>
</html>
