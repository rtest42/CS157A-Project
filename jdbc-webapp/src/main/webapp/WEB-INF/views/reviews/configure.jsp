<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Review Details</title>
</head>
<body>
    <h1>Review Details</h1>
    <p>
        <strong>Movie:</strong> ${review.movieID}
    </p>

    <!-- Display current rating and comment -->
    <form action="${pageContext.request.contextPath}/reviews/update" method="post">
        <input type="hidden" name="reviewID" value="${review.reviewID}" />
        <label for="rating">Rating:</label>
        <input type="number" id="rating" name="rating" min="1" max="5" value="${review.rating}" required />
        <br />

        <label for="comment">Comment:</label>
        <textarea id="comment" name="comment" rows="4" cols="50" required>${review.comment}</textarea>
        <br />

        <button type="submit">Update</button>
    </form>

    <!-- Back Button -->
    <form action="${pageContext.request.contextPath}/reviews/list" method="get">
        <button name="back" value="back">Back</button>
    </form>

    <!-- Delete Review -->
    <form action="${pageContext.request.contextPath}/reviews/remove" method="post">
        <input type="hidden" name="reviewID" value="${review.reviewID}" />
        <button type="submit">Delete</button>
    </form>
</body>
</html>
