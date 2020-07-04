import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/Reset")

public class Reset extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        try{
            Connection con = DatabaseConnection.initializeDatabase();

            Statement stmt = con.createStatement();
            int voteReset = stmt.executeUpdate("update Candidates SET Votes = 0 ");

            PrintWriter out = new PrintWriter(new OutputStreamWriter(
                    response.getOutputStream(), StandardCharsets.UTF_8), true);


            out.println("<html>" +
                    "<head>" +
                    "<meta charset=\"utf-8\">" +
                    "<html lang=\"pl-PL\">" +
                    "</head>" +
                    "<body>");
            out.println("Resetowanie głosów przebiegło pomyślnie!");

            out.println("<form action=\"index.jsp\" method=\"get\">");
            out.println("<input type=\"submit\" value=\"Menu\"/>");
            out.println("</form>");

            out.println("<form action=\"ShowResult\" method=\"get\">");
            out.println("<input type=\"submit\" value=\"Wyniki\"/>");
            out.println("</form>");

            out.println("</body>" +
                    "</html>");



        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}