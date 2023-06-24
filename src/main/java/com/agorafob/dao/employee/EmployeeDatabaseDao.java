package com.agorafob.dao.employee;

import com.agorafob.dbconnect.ConnectionBuilderFactory;
import com.agorafob.dbconnect.DbConnectionBuilder;
import com.agorafob.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.agorafob.util.AppConstants.*;

public class EmployeeDatabaseDao implements EmployeeDao {
    private static final String SELECT = "SELECT * FROM employee";
    private static final String GET_EMPLOYEE = "SELECT * FROM employee WHERE id = ?";
    private static final String INSERT = "INSERT INTO employee(name, salary, department_id, chief_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE employee SET name = ?, salary = ?, department_id = ?, chief_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM employee WHERE id = ?";

    private final DbConnectionBuilder builder = ConnectionBuilderFactory.getInstance().getDbConnectionBuilder();


    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long addEmployee(Employee employee) {
        long employeeId = -1;
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, employee.getName());
            stmt.setInt(2, employee.getSalary());
            stmt.setLong(3, employee.getDepartmentId());
            if (Objects.isNull(employee.getChiefId())) {
                stmt.setNull(4, Types.NULL);
            } else {
                stmt.setLong(4, employee.getChiefId());
            }
            stmt.executeUpdate();


            ResultSet resultSet = stmt.getGeneratedKeys();
            while (resultSet.next()) {
                employeeId = resultSet.getLong(COLUMN_ID);
                System.out.println("================= " + resultSet.getString(COLUMN_ID));
                System.out.println("================= " + resultSet.getString(COLUMN_NAME));
                System.out.println("================= " + resultSet.getString(COLUMN_SALARY));
                System.out.println("================= " + resultSet.getString(COLUMN_DEPARTMENT_ID));
                System.out.println("================= " + resultSet.getString(COLUMN_CHIEF_ID));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeId;
    }

    @Override
    public void updateEmployee(Employee employee) {
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(UPDATE)
        ) {
            stmt.setString(1, employee.getName());
            stmt.setInt(2, employee.getSalary());
            stmt.setLong(3, employee.getDepartmentId());
            if (Objects.isNull(employee.getChiefId())) {
                stmt.setNull(4, Types.NULL);
            } else {
                stmt.setLong(4, employee.getChiefId());
            }
            stmt.setLong(5, employee.getId());
            int count = stmt.executeUpdate();
            System.out.println(count + " rows updated");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(DELETE)
        ) {
            stmt.setLong(1, id);
            int count = stmt.executeUpdate();
            System.out.println(count + " rows deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee getEmployee(Long id) {
        Employee temp = null;
        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(GET_EMPLOYEE)) {
            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long chief_id = rs.getLong(COLUMN_CHIEF_ID);
                if(rs.wasNull()){
                    chief_id=null;
                }
                temp = new Employee(rs.getLong(COLUMN_ID),
                        rs.getString(COLUMN_NAME),
                        rs.getInt(COLUMN_SALARY),
                        rs.getLong(COLUMN_DEPARTMENT_ID),
                        chief_id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return temp;
    }

    @Override
    public List<Employee> findEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(SELECT);
            while (rs.next()) {
                Long chief_id = rs.getLong(COLUMN_CHIEF_ID);
                if(rs.wasNull()){
                    chief_id=null;
                }

                Employee temp = new Employee(rs.getLong(COLUMN_ID),
                        rs.getString(COLUMN_NAME),
                        rs.getInt(COLUMN_SALARY),
                        rs.getLong(COLUMN_DEPARTMENT_ID),
                        chief_id);
                employeeList.add(temp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }
}
