package com.karlchu.mo.web.util;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by hieu on 10/19/2015.
 */
public class JdbcUtils {
    public static <T> List<T> query(String tableName, String[] projections, Class T) throws Exception {
        try {
            InitialContext ic = new InitialContext();
            DataSource dataSource = (DataSource) ic.lookup("java:jboss/datasources/MoPlatformDS");
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            StringBuilder sb = new StringBuilder();
            for (String s : projections) {
                if (sb.length() == 0) {
                    sb.append("SELECT ").append(s);
                } else {
                    sb.append(", ").append(s);
                }
            }
            sb.append(" FROM ").append(tableName);

            return jdbcTemplate.query(
                    sb.toString(), new BeanPropertyRowMapper(T));
        } catch (NamingException e) {
            throw new Exception(e);
        } catch (DataAccessException e) {
            throw new Exception(e);
        }
    }
}
