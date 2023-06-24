package com.agorafob.dao.departments;

import com.agorafob.dbconnect.ConnectionBuilderFactory;
import com.agorafob.dbconnect.DbConnectionBuilder;
import com.agorafob.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.agorafob.util.AppConstants.COLUMN_ID;
import static com.agorafob.util.AppConstants.COLUMN_NAME;

public class DepartmentsDatabaseDao implements DepartmentDao {
    private static final String SELECT = "SELECT * FROM department";
    private static final String GET_DEPARTMENT = "SELECT * FROM department WHERE id = ?";
    private final DbConnectionBuilder builder = ConnectionBuilderFactory.getInstance().getDbConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }


    @Override
    public Long addDepartment(Department department) {
        Long id = -1L;
        try (Connection con = getConnection();
             CallableStatement stmt1 = con.prepareCall("{call insertdepartment(?)}");
             CallableStatement stmt2 = con.prepareCall("{call getdepartmentbyname(?,?)}"))
        {
            stmt1.setString(1, department.getName());
            stmt1.execute();

            stmt2.setString(1, department.getName());
            stmt2.registerOutParameter(2,Types.BIGINT);
            stmt2.execute();

            id=stmt2.getLong(2);
            System.out.println(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public void updateDepartment(Department department) {
        try(Connection con = getConnection();
        CallableStatement stmt = con.prepareCall("{call updatedepartment(?,?)}")){
            stmt.setLong(1,department.getId());
            stmt.setString(2, department.getName());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteDepartment(Long id) {
try(Connection con = getConnection();
    CallableStatement stmt = con.prepareCall("{call deletedepartmentbyid(?)}")){
        stmt.setLong(1,id);
    stmt.execute();
} catch (SQLException e) {
    throw new RuntimeException(e);
}

    }

    @Override
    public Department getDepartment(Long id) {
        Department temp = null;
        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(GET_DEPARTMENT)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                temp = new Department(rs.getLong(COLUMN_ID), rs.getString(COLUMN_NAME));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return temp;
    }

    @Override
    public List<Department> findDepartments() {
        List<Department> departmentList = new ArrayList<>();
        try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(SELECT);
            while (rs.next()) {
                Department temp = new Department(rs.getLong(COLUMN_ID), rs.getString(COLUMN_NAME));
                departmentList.add(temp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return departmentList;
    }
}
