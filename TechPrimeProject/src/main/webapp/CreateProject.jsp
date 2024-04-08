<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Project Details</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                background-color: #f8f9fa;
            }

            .container {
                margin-top: 50px;
                padding: 20px;
            }

            .form-group {
                margin-bottom: 20px;
            }

            .box {
                border: 1px solid #ced4da;
                padding: 20px;
                margin-bottom: 20px;
                border-radius: 5px;
                background-color: #fff;
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

    <body>
        <div class="header-image">
        <img src="images/Header-bg.svg" alt="Project Manager" width="1500">
        <div class="logo">
            <img src="images/Logo.svg" alt="Logo">
        </div>
       <h2>Project Details</h2>
    </div>
        <%@ include file="navbar.html" %>
            <div class="container">
                <div class="col-md-6 offset-md-3">
                    
                    <form id="projectForm" action="SaveProjectDetails" method="POST" onsubmit="return validateForm()">
                        <div class="form-group">
                            <label for="theme">Project Theme:</label> <input type="text" class="form-control" id="theme"
                                name="theme">
                        </div>
                        <div class="form-group">
                            <label for="date1">Start Date:</label> <input type="date" class="form-control"
                                id="start_date" name="start_date">
                        </div>
                        <div class="form-group">
                            <label for="date2">End Date:</label> <input type="date" class="form-control" id="end_date"
                                name="end_date">
                        </div>
                        <div class="form-group">
                            <label for="reason">Reason :</label> <select class="form-control" id="reason"
                                name="reason">
                                <option value="">Select Reason</option>
                                <option value="Business">Business</option>
                                <option value="Dealership">Dealership</option>
                                <option value="Transport">Transport</option>
                                <!-- Add more options as needed -->
                            </select>
                        </div>
                        <!-- Repeat similar form-group blocks for the remaining dropdowns -->
                        <div class="form-group">
                            <label for="category">Category :</label> <select class="form-control" id="category"
                                name="category">
                                <option value="">Select Category</option>
                                <option value="Category A">Category A</option>
                                <option value="Category B">Category B</option>
                                <option value="Category C">Category C</option>
                                <option value="Category D">Category D</option>
                                <!-- Add more options as needed -->
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="department">Department :</label> <select class="form-control" id="department"
                                name="department">
                                <option value="">Select Department</option>
                                <option value="Strategy">Strategy</option>
                                <option value="Finance">Finance</option>
                                <option value="Quality">Quality</option>
                                <option value="Maintenance">Maintenance</option>
                                <option value="Stores">Stores</option>
                                <!-- Add more options as needed -->
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="division">Division :</label> <select class="form-control" id="division"
                                name="division">
                                <option value="">Select Division</option>
                                <option value="Compressor">Compressor</option>
                                <option value="Filters">Filters</option>
                                <option value="Pumps">Pumps</option>
                                <option value="Glass">Glass</option>
                                <option value="Water Heater">Water Heater</option>
                                <!-- Add more options as needed -->
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="location">Location :</label> <select class="form-control" id="location"
                                name="location">
                                <option value="">Select Location</option>
                                <option value="Pune">Pune</option>
                                <option value="Delhi">Delhi</option>
                                <option value="Mumbai">Mumbai</option>
                                <!-- Add more options as needed -->
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="priority">Priority :</label> <select class="form-control" id="priority"
                                name="priority">
                                <option value="">Select Priority</option>
                                <option value="High">High</option>
                                <option value="Medium">Medium</option>
                                <option value="Low">Low</option>
                                <!-- Add more options as needed -->
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="type">Type :</label> <select class="form-control" id="type" name="type">
                                <option value="">Select Type</option>
                                <option value="Internal">Internal</option>
                                <option value="External">External</option>
                                <option value="Vendor">Vendor</option>
                                <!-- Add more options as needed -->
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>

            <% String success=request.getParameter("success"); if (success !=null && success.equals("true")) { %>
                <div id="successMessage" style="color: green; margin-top: 10px;">Status: Registered</div>
                <% } %>

                    <% String projectId=request.getParameter("projectId"); if (projectId !=null) { %>
                        <div id="projectId" style="color: green; margin-top: 20px;">
                            Created Project ID:
                            <%=projectId%>
                        </div>
                        <% } %>


                            <script>
                                function validateField(field, errorMessage) {
                                    if (field.trim() === "") {
                                        alert(errorMessage);
                                        return false;
                                    }
                                    return true;
                                }

                                function validateForm() {
                                    var theme = document.getElementById("theme").value.trim();
                                    var start_date = document.getElementById("start_date").value.trim();
                                    var end_date = document.getElementById("end_date").value.trim();
                                    var reason = document.getElementById("reason").value.trim();
                                    var category = document.getElementById("category").value.trim();
                                    var department = document.getElementById("department").value.trim();
                                    var division = document.getElementById("division").value.trim();
                                    var location = document.getElementById("location").value.trim();
                                    var priority = document.getElementById("priority").value.trim();
                                    var type = document.getElementById("type").value.trim();

                                    if (!validateField(theme, "Please enter project theme details") ||
                                        !validateField(start_date, "Please select a start date") ||
                                        !validateField(end_date, "Please select an end date") ||
                                        !validateField(reason, "Please select an option for Dropdown 1") ||
                                        !validateField(category, "Please select an option for Dropdown 2") ||
                                        !validateField(department, "Please select an option for Dropdown 3") ||
                                        !validateField(division, "Please select an option for Dropdown 4") ||
                                        !validateField(location, "Please select an option for Dropdown 5") ||
                                        !validateField(priority, "Please select an option for Dropdown 6") ||
                                        !validateField(type, "Please select an option for Dropdown 7")) {
                                        return false;
                                    }

                                    // Check if start_date is greater than end_date
                                    if (new Date(start_date) > new Date(end_date)) {
                                        alert("Start date cannot be greater than end date");
                                        return false;
                                    }

                                    // Check if end_date is less than start_date
                                    if (new Date(end_date) < new Date(start_date)) {
                                        alert("End date cannot be less than start date");
                                        return false;
                                    }

                                    return true;
                                }
                            </script>



                            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
                            <script
                                src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
                            <script
                                src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>

    </html>