package ru.homeWork.dataSourceConnectionConfig;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class TomCatDataSource {

    private static DataSource datasource;

    public static void conf() {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:h2:/Users/19208093/IdeaProjects/ServerServletEdu/ServerGraduationProject/src/dataBase/db/dbdefault");
        p.setDriverClassName("org.h2.Driver");
        p.setUsername("admin");
        p.setPassword("admin1234");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        DataSource datasource = new DataSource();
        datasource.setPoolProperties(p);
        TomCatDataSource.datasource = datasource;
        log.info("======TomCat dataSource configured successfully with properties:");
        log.info(p.getConnectionProperties());
    }

    public static Connection getConnection() throws SQLException {
        return datasource.getConnection();
    }

}
