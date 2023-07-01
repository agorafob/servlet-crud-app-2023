package com.agorafob.servlet.employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.agorafob.model.Department;
import com.agorafob.model.Employee;
import com.agorafob.model.EmployeeWithDepartment;
import com.agorafob.service.DepartmentManagerService;
import com.agorafob.service.EmployeeManageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employees/find")
public class FindAllEmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeManageService ems = new EmployeeManageService();
        DepartmentManagerService dms = new DepartmentManagerService();
        List<EmployeeWithDepartment> employeeWithDepartmentList = new ArrayList<>();
        List<Employee> employeeList = ems.findAll();
        List<Department> departmentList = dms.findDepartments();
        for (Employee e : employeeList) {
            String departmentName = departmentList.stream()
                    .filter(d -> d.getId().equals(e.getDepartmentId()))
                    .map(Department::getName)
                    .findAny()
                    .orElse(null);

            employeeWithDepartmentList.add(new EmployeeWithDepartment(
                    e.getId(),
                    e.getName(),
                    e.getSalary(),
                    e.getDepartmentId(),
                    e.getChiefId(),
                    departmentName
            ));
        }
        employeeWithDepartmentList.sort((o1, o2) -> {
            String x1 = o1.getDepartment();
            String x2 = o2.getDepartment();
            int sComp = x1.compareTo(x2);
            if (sComp != 0) {
                return sComp;
            }
            x1 = o1.getName();
            x2 = o2.getName();
            return x1.compareTo(x2);
        });

        req.setAttribute("employees", employeeWithDepartmentList);
        req.getServletContext().getRequestDispatcher("/view/employee/findAll/index.jsp").forward(req, resp);
    }
}
