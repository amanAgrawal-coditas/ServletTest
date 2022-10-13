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
@WebServlet("/Insert")
public class Insert extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection;
        PreparedStatement preparedStatement;
        Statement statement;
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        ServletContext application = getServletConfig().getServletContext();
        String firstName = req.getParameter("Firstname");
        String lastName = req.getParameter("LastName");
        int number = Integer.parseInt(req.getParameter("number"));
        int age = Integer.parseInt(req.getParameter("age"));
        String email = req.getParameter("email");
        String city = req.getParameter("city");

        try {
            Class.forName(application.getInitParameter("Driver"));

            connection = DriverManager.getConnection(application.getInitParameter("Url"),
                    application.getInitParameter("Username"), application.getInitParameter("Password"));

            preparedStatement = connection.prepareStatement("insert into Person_Details values(?,?,?,?,?,?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, number);
            preparedStatement.setInt(4, age);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, city);
            preparedStatement.executeUpdate();
            printWriter.println("<a href='Login.html'>Logout</a>");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}