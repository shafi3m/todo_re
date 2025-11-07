<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*, todo_re.DBConnection" %>

<%
    // Check login session
    session = request.getSession(false);
    if (session == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    int id = Integer.parseInt(request.getParameter("id"));
    Connection con = DBConnection.getConnection();
    PreparedStatement ps = con.prepareStatement("SELECT * FROM todos WHERE id=?");
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    rs.next();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Task</title>
</head>
<body>

<h2>Edit Task</h2>

<form action="editTask" method="post">

    <input type="hidden" name="id" value="<%= rs.getInt("id") %>">

    <label>Title:</label><br>
    <input type="text" name="title" value="<%= rs.getString("title") %>" required><br><br>

    <label>Description:</label><br>
    <textarea name="description" rows="3"><%= rs.getString("description") %></textarea><br><br>

    <label>Status:</label><br>
    <select name="status">
        <option value="Pending"  <%= rs.getString("status").equals("Pending") ? "selected" : "" %> >Pending</option>
        <option value="Done"     <%= rs.getString("status").equals("Done") ? "selected" : "" %> >Done</option>
    </select><br><br>

    <input type="submit" value="Update Task">

</form>

<br>
<a href="home.jsp">Back to Tasks</a>

<%
    con.close();
%>

</body>
</html>
