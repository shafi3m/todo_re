<%@page import="todo_re.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*, todo_re.DBConnection" %>

<%
    // Check session
    session = request.getSession(false);
    if (session == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    int userId = (int) session.getAttribute("userId");
    String userName = (String) session.getAttribute("userName");
    
%>
<%="hi"+userId %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My To-Do List</title>
</head>
<body>

<h2>Welcome, <%= userName %></h2>

<a href="logout">Logout</a> |
<a href="addTask.jsp">Add New Task</a>

<h3>Your Tasks</h3>

<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>

<%
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        con = DBConnection.getConnection();
        ps = con.prepareStatement("SELECT * FROM todos WHERE user_id=?");
        ps.setInt(1, userId);
        rs = ps.executeQuery();

        boolean found = false;

        while (rs.next()) {
            found = true;
%>
    <tr>
        <td><%= rs.getString("title") %></td>
        <td><%= rs.getString("description") %></td>
        <td><%= rs.getString("status") %></td>
        <td>
            <a href="edittask.jsp?id=<%= rs.getInt("id") %>">Edit</a> |
            <a href="deleteTask?id=<%= rs.getInt("id") %>">Delete</a>
        </td>
    </tr>
<%
        }

        if (!found) {
%>
    <tr>
        <td colspan="4" align="center">No tasks found. Add one!</td>
    </tr>
<%
        }
    } catch (Exception e) {
        out.print("Error: " + e.getMessage());
    } finally {
        try { if(rs != null) rs.close(); } catch (Exception e){}
        try { if(ps != null) ps.close(); } catch (Exception e){}
        try { if(con != null) con.close(); } catch (Exception e){}
    }
%>

</table>

</body>
</html>
