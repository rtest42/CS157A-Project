<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<title>Movie Details</title>
</head>
<body>
	<h1>Movie Details</h1>
	<p>
		<strong>Director:</strong> ${movie.director}
	</p>
	<p>
		<strong>Genre:</strong> ${movie.genre}
	</p>
	<p>
		<strong>Release Year:</strong> ${movie.releaseYear}
	</p>
	<p>
		<strong>Rating:</strong> ${movie.rating}
	</p>
	<p>
		<strong>Description:</strong> ${movie.description}
	</p>
	<form action="${pageContext.request.contextPath}/movies/dashboard"
		method="get">
		<button name="back" value="back">Back</button>
	</form>
	<form action="${pageContext.request.contextPath}/rentals/rent"
		method="post">
		<input type="hidden" name="movieID" value="${movie.movieID}" />
		<button name="rent" value="rent"
			<c:if test="${not canRent}"> 
				disabled="disabled" 
			</c:if>>
			Rent
		</button>
	</form>
	<form action="${pageContext.request.contextPath}/reviews/review"
		method="post">
		<input type="hidden" name="movieID" value="${movie.movieID}" />
		<button name="review" value="review"
			<c:if test="${not canReview}">
				disabled="disabled" 
			</c:if>>
			Review
		</button>
	</form>
</body>
</html>