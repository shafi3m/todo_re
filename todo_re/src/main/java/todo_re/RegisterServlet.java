package todo_re;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.ldap.Rdn;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.apache.catalina.startup.SetContextPropertiesRule;

@WebServlet("/register")
public class  RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

    	PrintWriter  out = res.getWriter();
    	res.setContentType("text/html");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");

        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users (name, email, password) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, pass);
            int result = ps.executeUpdate();

            if (result > 0)
            	
            	
                res.sendRedirect("login.jsp");
            else
                res.getWriter().println("Registration Failed!");
        } catch (Exception e) {
            e.printStackTrace(res.getWriter());
        }
    }
}
