<%@taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>Reviews</title>
</head>
<body>
	<h1>Reviews</h1>
	<h4>Hello, ${user.firstName}!</h4>
	<form action="${pageContext.request.contextPath}/movies/dashboard"
			method="get">
			<button name="dashboard" value="dashboard">Get Movies</button>
		</form>
	<table border="1">
		<tr>
			<th>Movie</th>
			<th>Rating</th>
			<th>Comment</th>
			<th>Edit</th>
		</tr>
		<!-- Iterate over the movies list -->
		<c:forEach var="review" items="${reviews}">
			<tr>
				<td>${review.movieID}</td>
				<td>${review.rating}</td>
				<td>${review.comment}</td>
				<td><a href="configure?id=${review.reviewID}">Edit</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>