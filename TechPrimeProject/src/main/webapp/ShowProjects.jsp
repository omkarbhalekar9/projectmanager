
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.techprime.model.ProjectDetails"%>
<%@page import="com.techprime.sql.ProjectSql"%>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Project List</title>
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
    .welcome {
        padding: 30px 10%;
        text-align: center;
        font-size: 50px;
        color: #a82c0d;
    }

    th {
        text-align: center;
    }

    th, td {
        vertical-align: middle !important;
    }

    tr:nth-child(odd) {
        background-color: #eeeeee;
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
<body> <div class="header-image">
        <img src="images/Header-bg.svg" alt="Project Manager" width="1500">
        <div class="logo">
            <img src="images/Logo.svg" alt="Logo">
        </div>
        <h1>Project List <h1>
    </div>

<jsp:include page="navbar.html" />



<div class="container">
    <!-- Sorting and Search Form -->
    <form id="projectListForm" action="ProjectList" method="POST">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="searchInput">Search by Project Name:</label>
                <input type="text" class="form-control" id="searchInput" name="searchName" placeholder="Enter project name">
            </div>
            <div class="form-group col-md-6">
                <label for="sortSelect">Sort By:</label>
                <select class="form-control" id="sortSelect" name="sortOption">
                    <option value="projectName">Project Name</option>
                    <option value="type">Type</option>
                    <option value="division">Division</option>
                    <option value="reason">Reason</option>
                    <option value="category">Category</option>
                    <option value="priority">Priority</option>
                    <option value="department">Department</option>
                    <option value="location">Location</option>
                    <option value="Status">Status</option>
                </select>
            </div>
        </div>
    </form>

    <!-- Table -->
    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
            <tr>
                <th>Project Name</th>
                <th>Reason</th>
                <th>Type</th>
                <th>Division</th>
                <th>Category</th>
                <th>Priority</th>
                <th>Department</th>
                <th>Location</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
                String jsonString = (String) request.getAttribute("jsonList");
                // Parse the JSON string to retrieve the list
                List<ProjectDetails> list = new ArrayList<>();
                if (jsonString != null) {
                	 ObjectMapper mapper = new ObjectMapper();
                     list = mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(List.class, ProjectDetails.class));
                } else {
                	ProjectSql ps = new ProjectSql();
                	list = ps.getProjectDetails(null, null);
                }
                if (list != null) {
                    Iterator<ProjectDetails> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        ProjectDetails pd = iterator.next();
            %>
            <tr>
                <td><%=pd.getProjectTheme() %><br><%=pd.getDateStr()%></td>
                <td><%=pd.getReason()%></td>
                <td><%=pd.getType()%></td>
                <td><%=pd.getDivision()%></td>
                <td><%=pd.getCategory()%></td>
                <td><%=pd.getPriority()%></td>
                <td><%=pd.getDepartment()%></td>
                <td><%=pd.getLocation()%></td>
                <td><%=pd.getStatus()%></td>
                <td>
                    <button class="btn btn-primary mb-2" onclick="transfer('<%=pd.getProjectId()%>', 'Start')">Start</button><br>
                    <button class="btn btn-primary mb-2" onclick="transfer('<%=pd.getProjectId()%>', 'Close')">Close</button><br>
                    <button class="btn btn-primary" onclick="transfer('<%=pd.getProjectId()%>', 'Cancel')">Cancel</button><br>
                </td>
            </tr>
            <% 
                    }
                }
            %>
        </tbody>
    </table>
</div>

<script>
    // Submit the form when the sorting dropdown value changes
    document.getElementById("sortSelect").addEventListener("change", function() {
        document.getElementById("projectListForm").submit();
    });
    
    // Submit the form when the search input value changes
    document.getElementById("searchInput").addEventListener("input", function() {
    	if (this.value.length > 2) {
            document.getElementById("projectListForm").submit();
        }
    });

    function transfer(projectId, action) {
        var form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", "UpdateProjectStatus");

        var hiddenField1 = document.createElement("input");
        hiddenField1.setAttribute("type", "hidden");
        hiddenField1.setAttribute("name", "projectId");
        hiddenField1.setAttribute("value", projectId);

        var hiddenField2 = document.createElement("input");
        hiddenField2.setAttribute("type", "hidden");
        hiddenField2.setAttribute("name", "action");
        hiddenField2.setAttribute("value", action);

        form.appendChild(hiddenField1);
        form.appendChild(hiddenField2);

        document.body.appendChild(form);
        form.submit();
    }

    var alertDisplayed = localStorage.getItem("alertDisplayed");

    if (!alertDisplayed) {
        // Scriptlet to handle success message and refresh the page
        <% 
            String success = request.getParameter("success");
            if (success != null && success.equals("true")) {
        %>
            //alert("Project status has been updated successfully.");
            // Set flag in localStorage to indicate that the alert has been displayed
            localStorage.setItem("alertDisplayed", "true");
            location.reload();
        <%
            }
        %>
    }
</script>

</body>
</html>