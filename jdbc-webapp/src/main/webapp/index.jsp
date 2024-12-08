<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>CS157A Project</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body class="bg-light d-flex justify-content-center align-items-center vh-100">

    <div class="text-center container py-5">
        <!-- Welcome Header -->
        <h1 class="mb-4 text-primary">Welcome to a CS157A Movie Project!</h1>
        <p class="mb-5 text-secondary">Please select an option below to get started:</p>

        <!-- Navigation Buttons -->
        <div class="d-flex flex-column align-items-center">
            <a href="${pageContext.request.contextPath}/movies/dashboard" class="btn btn-primary btn-lg mb-3">Browse Movies</a>
            <a href="${pageContext.request.contextPath}/users/login" class="btn btn-secondary btn-lg mb-3">Login</a>
            <a href="${pageContext.request.contextPath}/users/register" class="btn btn-success btn-lg">Register</a>
        </div>
    </div>

</body>
</html>
