<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    // Block access if not logged in
    session = request.getSession(false);
    if (session == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Task</title>
</head>
<body>

<h2>Add New Task</h2>

<form action="addTask" method="post">

    <label>Task Title:</label><br>
    <input type="text" name="title" required><br><br>

    <label>Description:</label><br>
    <textarea name="description" rows="4"></textarea><br><br>

    <input type="submit" value="Add Task">

</form>

<br>
<a href="tasks.jsp">Back to To-Do List</a>

</body>
</html>
