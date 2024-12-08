<%@taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>Movies</title>
</head>
<body>
	<h1>Movies</h1>
	<h4>Hello, ${user.firstName}!</h4>
	<form action="${pageContext.request.contextPath}/users/logout"
		method="post">
		<button name="logout" value="logout">Logout</button>
	</form>
	<form action="${pageContext.request.contextPath}/users/profile"
		method="get">
		<button name="profile" value="profile">Profile</button>
	</form>
	<table border="1">
		<tr>
			<th>Title</th>
			<th>Director</th>
			<th>Genre</th>
			<th>Release Year</th>
			<th>Rating</th>
			<th>Details</th>
		</tr>
		<!-- Iterate over the movies list -->
		<c:forEach var="movie" items="${movies}">
			<tr>
				<td>${movie.title}</td>
				<td>${movie.director}</td>
				<td>${movie.genre}</td>
				<td>${movie.releaseYear}</td>
				<td>${movie.rating}</td>
				<td><a href="details?id=${movie.movieID}">Details</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>