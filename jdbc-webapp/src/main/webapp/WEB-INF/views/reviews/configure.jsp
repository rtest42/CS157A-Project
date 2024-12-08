<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Review Details</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<!-- Custom CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body class="bg-light">

    <div class="container py-5 ard p-4 shadow-lg">
        <h1 class="text-center mb-4">Review Details</h1>

        <!-- Movie and Review Details -->
        <div class="row mb-4">
            <div class="col-12">
                <p><strong>Movie:</strong> ${movie.title}</p>
            </div>
        </div>

        <!-- Edit Review Form -->
        <div class="row mb-4">
            <div class="col-12">
                <form action="${pageContext.request.contextPath}/reviews/update" method="post">
                    <input type="hidden" name="reviewID" value="${review.reviewID}" />

                    <div class="form-group">
                        <label for="rating">Rating:</label>
                        <input type="number" id="rating" name="rating" class="form-control" min="1" max="10" step="0.1" value="${review.rating}" required />
                    </div>

                    <div class="form-group">
                        <label for="comment">Comment:</label>
                        <textarea id="comment" name="comment" class="form-control" rows="4" required>${review.comment}</textarea>
                    </div>

                    <button type="submit" class="btn btn-primary">Update</button>
                </form>
            </div>
        </div>

        <!-- Navigation Buttons -->
        <div class="row mb-4">
            <div class="col-12">
                <form action="${pageContext.request.contextPath}/reviews/list" method="get" class="d-inline">
                    <button type="submit" class="btn btn-secondary">Back</button>
                </form>
                <form action="${pageContext.request.contextPath}/reviews/remove" method="post" class="d-inline">
                    <input type="hidden" name="reviewID" value="${review.reviewID}" />
                    <button type="submit" class="btn btn-danger ml-2">Delete</button>
                </form>
            </div>
        </div>

    </div>

</body>
</html>
