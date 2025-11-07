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
@WebServlet("/editTask")
public class EditTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String desc = req.getParameter("description");
        String status = req.getParameter("status");

        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "UPDATE todos SET title=?, description=?, status=? WHERE id=?");
            ps.setString(1, title);
            ps.setString(2, desc);
            ps.setString(3, status);
            ps.setInt(4, id);
            ps.executeUpdate();
            res.sendRedirect("tasks.jsp");
        } catch (Exception e) {
            e.printStackTrace(res.getWriter());
        }
    }
}
