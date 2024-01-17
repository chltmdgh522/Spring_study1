package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManger() throws SQLException {
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        log.info("connection={}, class={}",con1,con1.getClass());
        log.info("connection={}, class={}",con2,con2.getClass());
    }

    @Test
    void dataSourceDriverManger() throws SQLException {
        //DriverMangerDataSource - 항상 새로운 커넥션을 획득
        DataSource driverManagerDataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(driverManagerDataSource);
    }

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {

        //커넥션 풀링
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(URL);
        hikariDataSource.setUsername(USERNAME);
        hikariDataSource.setPassword(PASSWORD);
        hikariDataSource.setMinimumIdle(10);
        hikariDataSource.setMaximumPoolSize(10);
        hikariDataSource.setPoolName("MyPool");

        useDataSource(hikariDataSource);
        Thread.sleep(10000);


    }
    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();

        log.info("connection={}, class={}",con1,con1.getClass());
        log.info("connection={}, class={}",con2,con2.getClass());
    }
}
