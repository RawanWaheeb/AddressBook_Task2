import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ContactDAO {


    private DataSource getDataSource() {
        Properties props = new Properties();
        MysqlDataSource mysqlDS = new MysqlDataSource();

        try (FileInputStream fis = new FileInputStream("src/main/resources/db.properties")) {
            props.load(fis);
            mysqlDS.setURL(props.getProperty("db.url"));
            mysqlDS.setUser(props.getProperty("db.user"));
            mysqlDS.setPassword(props.getProperty("db.password"));
        } catch (IOException e) {
            System.err.println("Error: Unable to read the db.properties file! Make sure it exists in src/main/resources/");
        }
        return mysqlDS;
    }

    public Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }


    public void updateEmailsBatch() {
        String sql = "UPDATE contact_task2 SET email = ? WHERE name = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);

            pstmt.setString(1, "mariam_updated@mail.com");
            pstmt.setString(2, "Mariam");
            pstmt.addBatch();

            pstmt.setString(1, "ali_updated@mail.com");
            pstmt.setString(2, "Ali");
            pstmt.addBatch();

            pstmt.executeBatch();
            conn.commit();
            System.out.println("Batch update on contact_task2 completed successfully!");
        } catch (SQLException e) {
            System.err.println("Batch Update Failed: " + e.getMessage());
        }
    }


    public void initializeDatabaseIfEmpty() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {


            String createTableSQL = "CREATE TABLE IF NOT EXISTS contact_task2 (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "nick_name VARCHAR(100), " +
                    "address VARCHAR(255), " +
                    "home_phone VARCHAR(20), " +
                    "work_phone VARCHAR(20), " +
                    "cell_phone VARCHAR(20), " +
                    "email VARCHAR(100), " +
                    "birthday VARCHAR(20), " +
                    "web_site VARCHAR(100), " +
                    "profession VARCHAR(100))";

            stmt.executeUpdate(createTableSQL);


            stmt.executeUpdate("TRUNCATE TABLE contact_task2");
            System.out.println("contact_task2 is created/cleaned and ready...");

            String sql = "INSERT INTO contact_task2 (name, nick_name, address, home_phone, work_phone, cell_phone, email, birthday, web_site, profession) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                conn.setAutoCommit(false);

                addFullRecord(pstmt, "Mariam", "Mimi", "Aswan", "097111", "097222", "012345", "mariam@mail.com", "2002-02-02", "www.mariam.art", "Artist");
                addFullRecord(pstmt, "Ghaidaa", "Ghid", "Mansoura", "050111", "050222", "011222", "ghaidaa@mail.com", "1998-05-05", "www.ghid.com", "Designer");
                addFullRecord(pstmt, "Hamza", "Hamzo", "Cairo", "02111", "02222", "010999", "hamza@mail.com", "2010-03-03", "www.hamza.com", "Student");
                addFullRecord(pstmt, "Ali", "Alwa", "Alexandria", "03111", "03222", "015888", "ali@mail.com", "1992-12-12", "www.ali.com", "Engineer");

                pstmt.executeBatch();
                conn.commit();
                System.out.println("New English records inserted into contact_task2!");
            }
        } catch (SQLException e) {
            System.err.println("Database Initialization Failed: " + e.getMessage());
        }
    }

    private void addFullRecord(PreparedStatement pstmt, String... values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            pstmt.setString(i + 1, values[i]);
        }
        pstmt.addBatch();
    }


    public ResultSet getAllContactsScrollable() throws SQLException {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return stmt.executeQuery("SELECT * FROM contact_task2");
    }

    public static void main(String[] args) {
        ContactDAO dao = new ContactDAO();

        dao.initializeDatabaseIfEmpty();
        dao.updateEmailsBatch();
        System.out.println("Execution Finished! Check your GUI and XAMPP.");
    }
}