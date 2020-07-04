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
import java.text.DecimalFormat;

@WebServlet("/ShowResult")

public class ShowResult extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement stmt = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            PrintWriter out = new PrintWriter(new OutputStreamWriter(
                    response.getOutputStream(), StandardCharsets.UTF_8), true);

            ResultSet requestAllVotes = stmt.executeQuery("select SUM(Votes) FROM Candidates ");
            ResultSet candidateResult = stmt2.executeQuery("select FirstName, LastName, Votes FROM Candidates");

            long allVotes = -1;
            requestAllVotes.first();
            allVotes = requestAllVotes.getLong(1);
            requestAllVotes.close();

            out.println("<html>" +
                    "<head>" +
                    "<meta charset=\"utf-8\">" +
                    "<html lang=\"pl-PL\">" +
                    "</head>" +
                    "<body>");


            while(candidateResult.next()){
                String FirstName = candidateResult.getString("FirstName");
                String LastName = candidateResult.getString("LastName");
                long votes = 0;
                votes = candidateResult.getLong("Votes");
                float percent = 0;

                if(allVotes != 0) {
                    percent = ((float) votes / (float) allVotes) * 100;
                }

                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);

                out.println(FirstName + " " + LastName + " poparcie: " + df.format(percent) + "%" + " ( " + votes + " oddanych)");
                out.println("</br></br>");
            }
            out.println("<form action=\"index.jsp\" method=\"get\">");
            out.println("<input type=\"submit\" value=\"Menu\"/>");
            out.println("</form>");
            out.println("</html></body>");



            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


