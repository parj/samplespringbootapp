package io.github.parj.hello;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.sql.*;
import java.util.logging.Logger;

@Testcontainers
public class TContainerTest {
    private Logger log = Logger.getLogger("TContainerTest");

    @Container
    private PostgreSQLContainer pSqlContainer = new PostgreSQLContainer<>("postgres:16")
        .withDatabaseName("foo")
        .withUsername("hello")
        .withPassword("moo");

    @Test
    void checkOK() {
        assertTrue(pSqlContainer.isRunning());
    }
    
    @Test
    void getSimpleSelect() throws SQLException {
        String connURL = pSqlContainer.getJdbcUrl() + "&user=hello&password=moo&ssl=false&database=foo";
        int count = 0;

        try( Connection conn = DriverManager.getConnection(connURL);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT '1'");) 
             {
                
                while (rs.next())
                    ++count;

                log.info("Count: " + count);
                
             } catch (SQLException e) {
                e.printStackTrace();
             }
             finally {
                assertTrue(count > 0);
             }

             
             
        
    }
}
