package todo_re;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        String title = req.getParameter("title");
        String desc = req.getParameter("description");

        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO todos (user_id, title, description, status) VALUES (?, ?, ?, ?)");
            ps.setInt(1, userId);
            ps.setString(2, title);
            ps.setString(3, desc);
            ps.setString(4, "Pending");
            ps.executeUpdate();

            res.sendRedirect("tasks.jsp");
        } catch (Exception e) {
            e.printStackTrace(res.getWriter());
        }
    }
}

