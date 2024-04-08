<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registration</title>
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
</style>
</head>
<body><div class="header-image">
        <img src="images/Header-bg.svg" alt="Project Manager" width="1500">
        <div class="logo">
            <img src="images/Logo.svg" alt="Logo">
        </div>
         <h2>Registration</h2>
    </div>

    <div class="container">
        <div class="col-md-6 offset-md-3">
           
            <form id="registrationForm" action="RegisterUser" method="POST"
                onsubmit="return validateForm()">
                <div class="form-group">
                    <label for="username">Username:</label> <input type="text"
                        class="form-control" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label> <input type="email"
                        class="form-control" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label> <input type="password"
                        class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary">Register</button>
            </form>
            <p>
                Already have an account? <a href="index.jsp">Login here</a>
            </p>
        </div>
    </div>

    <% 
        String success = request.getParameter("success");
        if (success != null && success.equals("true")) { 
    %>
        <div id="successMessage" style="color: green; margin-top: 10px;">User successfully created!</div>
    <% 
        }
    %>

    <script>
        function validateForm() {
            var username = document.getElementById("username").value;
            var email = document.getElementById("email").value;
            var password = document.getElementById("password").value;

            if (username == "" || email == "" || password == "") {
                alert("All fields are required");
                return false;
            }
            return true;
        }
    </script>

</body>
</html>
