package com.agorafob.dao.departments;

import com.agorafob.model.Department;

import java.util.List;

public interface DepartmentDao {
    Long addDepartment(Department department);
    void updateDepartment(Department department);
    void deleteDepartment (Long id);
    Department getDepartment(Long id);
    List<Department> findDepartments();
}
