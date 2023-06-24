package com.agorafob.dao.departments;

import java.util.Objects;

public class DepartmentDaoFactory {
    private static volatile DepartmentDaoFactory instance;
    private DepartmentDao departmentDao;

    public DepartmentDaoFactory() {
        departmentDao = new DepartmentsDatabaseDao();
    }

    public static synchronized DepartmentDaoFactory getInstance(){
        if(Objects.isNull(instance)){
            instance=new DepartmentDaoFactory();
        }
        return instance;
    }

    public DepartmentDao getDepartmentDao(){
        return departmentDao;
    }
}
