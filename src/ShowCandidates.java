import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.*;

@WebServlet("/ShowCandidates")

public class ShowCandidates extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection con = DatabaseConnection.initializeDatabase();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Candidates");


            PrintWriter out = new PrintWriter(new OutputStreamWriter(
                    response.getOutputStream(), StandardCharsets.UTF_8), true);
            out.println("<html lang=\"pl\">" +
                    "<head>" +
                    "<meta charset=\"UTF-8\">" +
                    "</head>" +
                    "<body>");
            out.println("Proszę wybrać kandydata: ");
            request.getParameter("id");

            out.println("<form action=\"./Vote\" method=\"get\">");
            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");

                out.println("<input type=\"radio\" id=\""+id+"\"name=\"contact\" value=\""+id+"\">");
                out.println("<label for=\""+id+"\">"+firstName+" "+lastName+"</label>");
                out.println("</br></br>");


            }
            out.println("<button type=\"submit\">Głosuj</button>");
            out.println("</form>");

            out.println("<form action=\"index.jsp\" method=\"get\">");
            out.println("<input type=\"submit\" value=\"Powrót\"/>");
            out.println("</form></body></html>");


            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

