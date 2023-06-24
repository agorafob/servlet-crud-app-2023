package com.agorafob.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class EmployeeWithDepartment extends Employee {
    private final String department;

    public EmployeeWithDepartment(Long id, String name, int salary, Long departmentId, Long chiefId, String department) {
        super(id, name, salary, departmentId, chiefId);
        this.department = department;
    }
}
