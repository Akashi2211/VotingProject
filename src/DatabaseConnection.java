import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    protected static Connection initializeDatabase()
            throws SQLException, ClassNotFoundException
    {
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/VotingCentre?autoReconnect=true&useSSL=false";  // database connection URL

        Class.forName(dbDriver);
        return DriverManager.getConnection(dbURL,"root","root");
    }
}
