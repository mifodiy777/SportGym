package ru.innopolis.sportgym.DAO.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Кирилл on 29.10.2016.
 */
public class MySQLDAO {

    private DataSource dataSource;

    {
        try {
            Context initCtx = new InitialContext();
            DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/dataSource");
            this.dataSource = ds;
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
