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
import java.sql.Statement;

@WebServlet("/Vote")

public class Vote extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            Statement stmt = con.createStatement();
            int candidateId = Integer.parseInt(request.getParameter("contact"));
            ResultSet importVotes = stmt.executeQuery("select Votes from Candidates WHERE ID="+candidateId);

            long numberOfVotes = -1;
            importVotes.first();
            numberOfVotes = importVotes.getLong("Votes");

            long vote = numberOfVotes + 1;

            stmt.executeUpdate("update Candidates SET Votes ="+vote+" WHERE ID="+candidateId);




            PrintWriter out = new PrintWriter(new OutputStreamWriter(
                    response.getOutputStream(), StandardCharsets.UTF_8), true);

            out.println("<html>" +
                    "<head>" +
                    "<meta charset=\"utf-8\">" +
                    "<html lang=\"pl-PL\">" +
                    "</head>" +
                    "<body>");
            out.println("Dziekujemy za oddanie glosu!");

            out.println("<form action=\"index.jsp\" method=\"get\">");
            out.println("<input type=\"submit\" value=\"Menu\"/>");
            out.println("</form>");

            out.println("<form action=\"ShowResult\" method=\"get\">");
            out.println("<input type=\"submit\" value=\"Wyniki\"/>");
            out.println("</form>");

            out.println("</body>" +
                    "</html>");


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
