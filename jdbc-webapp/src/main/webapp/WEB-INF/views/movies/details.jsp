<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Movie Details</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body class="bg-light">

    <div class="container py-5 ard p-4 shadow-lg">
        <h1 class="text-center mb-4">Movie Details</h1>

        <!-- Movie Details -->
        <div class="row mb-4">
            <div class="col-12">
            	<p><strong>Title:</strong> ${movie.title}</p>
                <p><strong>Director:</strong> ${movie.director}</p>
                <p><strong>Genre:</strong> ${movie.genre}</p>
                <p><strong>Release Year:</strong> ${movie.releaseYear}</p>
                <p><strong>Rating:</strong> ${movie.rating}</p>
                <p><strong>Description:</strong> ${movie.description}</p>
            </div>
        </div>

        <!-- Navigation and Actions -->
        <div class="row">
            <div class="col-12">
                <!-- Back Button -->
                <form action="${pageContext.request.contextPath}/movies/dashboard" method="get" class="d-inline">
                    <button type="submit" class="btn btn-secondary">Back</button>
                </form>

                <!-- Rent Button -->
                <form action="${pageContext.request.contextPath}/rentals/rent" method="post" class="d-inline">
                    <input type="hidden" name="movieID" value="${movie.movieID}" />
                    <button type="submit" name="rent" value="rent" class="btn btn-primary"
                        <c:if test="${not canRent}"> 
                            disabled
                        </c:if>>
                        Rent
                    </button>
                </form>

                <!-- Review Button -->
                <form action="${pageContext.request.contextPath}/reviews/review" method="post" class="d-inline">
                    <input type="hidden" name="movieID" value="${movie.movieID}" />
                    <button type="submit" name="review" value="review" class="btn btn-success"
                        <c:if test="${not canReview}">
                            disabled
                        </c:if>>
                        Review
                    </button>
                </form>
            </div>
        </div>
		<!-- Reviews Section -->
        <div class="row mb-4 py-5">
            <div class="col-12">
                <h2 class="mb-4">Reviews</h2>
                <c:if test="${empty reviews}">
                    <p class="text-muted">No reviews available for this movie.</p>
                </c:if>
                <c:forEach var="review" items="${reviews}" varStatus="status">
                    <div class="card mb-3">
                        <div class="card-body">
                            <h5 class="card-title mb-1">
                                ${users[status.index].firstName} ${users[status.index].lastName} 
                                <small class="text-muted">(Joined: ${users[status.index].joinDate})</small>
                            </h5>
                            <p class="card-text mb-1"><strong>Rating:</strong> ${review.rating}</p>
                            <p class="card-text">${review.comment}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

</body>
</html>
