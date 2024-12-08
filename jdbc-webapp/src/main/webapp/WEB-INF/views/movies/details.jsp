<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Movie Details</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<!-- Custom CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body class="bg-light">

    <div class="container py-5 ard p-4 shadow-lg">
        <h1 class="text-center mb-4">Movie Details</h1>

        <!-- Movie Details -->
        <div class="row mb-4">
            <div class="col-12">
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

    </div>

</body>
</html>
