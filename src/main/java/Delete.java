import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
@WebServlet("/Delete")
public class Delete extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Connection connection;
        PreparedStatement preparedStatement;
        Statement statement;
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        ServletContext application = getServletConfig().getServletContext();
        String email=req.getParameter("email");
        try
        {
            Class.forName(application.getInitParameter("Driver"));

            connection = DriverManager.getConnection(application.getInitParameter("Url"),
                    application.getInitParameter("Username"), application.getInitParameter("Password"));
            preparedStatement=connection.prepareStatement("Delete from Person_Details where email='"+email+"'");
            preparedStatement.executeUpdate();
            printWriter.println("Deleted successfully");
            printWriter.println("<a href='Login.html'>Logout</a>");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    }

