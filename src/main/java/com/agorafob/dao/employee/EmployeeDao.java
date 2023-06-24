package com.agorafob.dao.employee;

import com.agorafob.model.Employee;

import java.util.List;

public interface EmployeeDao {
    Long addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee (Long id);
    Employee getEmployee(Long id);
    List<Employee> findEmployees();
}
