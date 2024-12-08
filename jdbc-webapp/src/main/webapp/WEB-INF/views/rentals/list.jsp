<%@taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>Rentals</title>
</head>
<body>
	<h1>Rentals</h1>
	<h4>Hello, ${user.firstName}!</h4>
	<form action="${pageContext.request.contextPath}/movies/dashboard"
			method="get">
			<button name="dashboard" value="dashboard">Get Movies</button>
		</form>
	<table border="1">
		<tr>
			<th>Movie</th>
			<th>Start Date</th>
			<th>Return Date</th>
			<th>Returned?</th>
			<th>Edit</th>
		</tr>
		<!-- Iterate over the movies list -->
		<c:forEach var="rental" items="${rentals}">
			<tr>
				<td>${rental.movieID}</td>
				<td>${rental.startDate}</td>
				<td>${rental.returnDate}</td>
				<td>${rental.returned}</td>
				<td><a href="configure?id=${rental.rentalID}">Details/Configure</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>