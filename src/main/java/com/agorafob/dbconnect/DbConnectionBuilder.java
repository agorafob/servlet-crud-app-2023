package com.agorafob.dbconnect;

import java.sql.Connection;
import java.sql.SQLException;

public interface DbConnectionBuilder {
    Connection getConnection() throws SQLException;
}
