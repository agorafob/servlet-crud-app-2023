package com.agorafob.servlet.department;

import com.agorafob.model.Department;
import com.agorafob.service.DepartmentManagerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/departments/find")
public class FindAllDepartmentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DepartmentManagerService dms = new DepartmentManagerService();
        List<Department> departmentList = dms.findDepartments();
        req.setAttribute("departments",departmentList);
        req.getServletContext().getRequestDispatcher("/view/department/findAll/index.jsp").forward(req,resp);
    }
}
