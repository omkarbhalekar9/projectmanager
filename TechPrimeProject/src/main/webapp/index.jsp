<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
body {
    background-color: #f8f9fa;
}

.container {
    margin-top: 50px;
}

.header-image {
    text-align: center;
    margin-bottom: 10px;
}

.logo {
    position: absolute;
    top: 30px; /* Adjust as needed */
    left: 700px; /* Adjust as needed */
}

.logo img {
    max-width: 100px; /* Adjust width as needed */
    height: auto;
}

.form-group {
    margin-bottom: 20px;
}
</style>
</head>
<body>
    <div class="header-image">
        <img src="images/Header-bg.svg" alt="Project Manager" width="1500">
        <div class="logo">
            <img src="images/Logo.svg" alt="Logo">
        </div>
        <h1>Project Manager<h1>
    </div>

    <div class="container">
        <div class="col-md-6 offset-md-3">
            <h2>Login</h2>
            <form id="loginForm" action="LoginUser" method="POST" onsubmit="return validateForm()">
                <div class="form-group">
                    <label for="login_username">Username:</label> <input type="text"
                        class="form-control" id="login_username" name="login_username"
                        required>
                </div>
                <div class="form-group">
                    <label for="login_password">Password:</label> <input
                        type="password" class="form-control" id="login_password"
                        name="login_password" required>
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
            <p>
                Don't have an account? <a href="register.jsp">Register here</a>
            </p>
        </div>
    </div>

    <% 
        String success = request.getParameter("success");
        if (success != null && success.equals("true")) { 
    %>
        <div id="successMessage" style="color: green; margin-top: 10px;">User successfully LoggedIn!</div>
    <% 
        }
    %>

    <script>
        function validateForm() {
            var username = document.getElementById("login_username").value;
            var password = document.getElementById("login_password").value;
            
            if (username.trim() === "" || password.trim() === "") {
                alert("Username and password are required");
                return false;
            }
            return true;
        }

        window.onload = function() {
            var successMessage = document.getElementById("successMessage");
            if (successMessage) {
                setTimeout(function() {
                    window.location.href = "CreateProject.jsp";
                }, 1000);
            }
        };
    </script>

</body>
</html>
