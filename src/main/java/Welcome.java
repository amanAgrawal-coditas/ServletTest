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
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/Welcome")
public class Welcome extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection connection;
        ResultSet resultSet;
        Statement statement;
        ServletContext application = getServletConfig().getServletContext();
        PrintWriter printWriter=resp.getWriter();
        printWriter.println("<H4> Hello there....</H4>");
        String email=req.getParameter("email");
        try
        {
            Class.forName(application.getInitParameter("Driver"));
            connection = DriverManager.getConnection(application.getInitParameter("Url"), application.getInitParameter("Username"), application.getInitParameter("Password"));
            statement = connection.createStatement();
            if(email.equals("admin123@gmail.com"))
            {
                resultSet=statement.executeQuery("Select * from Person_Details");
                printWriter.println("<table border=4px>");
                printWriter.println("<tr><th>First name</th><th>Last Name</th><th>Number</th><th>age</th><th>email</th><th>city</th><tr>");
                while (resultSet.next())
                {
                    String firstName = resultSet.getString("Fname");
                    String lastName = resultSet.getString("Lname");
                    int number = resultSet.getInt("number");
                    int age = resultSet.getInt("age");
                    String mail = resultSet.getString("email");
                    String city = resultSet.getString("city");
                    printWriter.println("<tr><td>" + firstName + "</td><td>" + lastName + "</td><td>" + number + "</td>"
                            + "<td>" + age + "</td>" + "<td>" + mail + "</td>" + "<td>" + city + "</td></tr>");


                }
                printWriter.println("</table>");
                printWriter.println("<a href='Login.html'>Logout</a>");
                printWriter.println("<a href='Insert.html'>Insert</a>");
                printWriter.println("<a href='Update.html'>Update</a>");
                printWriter.println("<a href='Delete.html'>Delete</a>");
            }
            else
            {
                resultSet = statement.executeQuery("Select * from Person_Details where email='" + email + "';");
                printWriter.println("<table border=1 width=50% height=50%>");
                printWriter.println("<tr><th>First name</th><th>Last Name</th><th>Number</th><th>age</th><th>email</th><th>city</th><tr>");
                while (resultSet.next())
                {
                    String firstName = resultSet.getString("Fname");
                    String lastName = resultSet.getString("Lname");
                    int number = resultSet.getInt("number");
                    int age = resultSet.getInt("age");
                    String mail = resultSet.getString("email");
                    String city = resultSet.getString("city");
                    printWriter.println("<tr><td>" + firstName + "</td><td>" + lastName + "</td><td>" + number + "</td>"
                            + "<td>" + age + "</td>" + "<td>" + mail + "</td>" + "<td>" + city + "</td></tr>");
                    printWriter.println("</table>");
                    printWriter.println("<a href='Login.html'>Logout</a>");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
