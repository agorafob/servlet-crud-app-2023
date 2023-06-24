package com.agorafob.service;

import com.agorafob.dao.departments.DepartmentDao;
import com.agorafob.dao.departments.DepartmentDaoFactory;
import com.agorafob.model.Department;

import java.util.List;

public class DepartmentManagerService {
    private final DepartmentDao departmentDao;

    public DepartmentManagerService() {
        this.departmentDao = DepartmentDaoFactory.getInstance().getDepartmentDao();
    }


    public Long addDepartment(Department department) {
        return departmentDao.addDepartment(department);
    }


    public void updateDepartment(Department department) {
        departmentDao.updateDepartment(department);
    }


    public void deleteDepartment(Long id) {
        departmentDao.deleteDepartment(id);
    }


    public Department getDepartment(Long id) {
        return departmentDao.getDepartment(id);
    }


    public List<Department> findDepartments() {
        return departmentDao.findDepartments();
    }


}
