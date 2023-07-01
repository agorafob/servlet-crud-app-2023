package com.agorafob.servlet.department;

import com.agorafob.model.Department;
import com.agorafob.service.DepartmentManagerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/departments/create")
public class CreateDepartmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/view/department/create/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DepartmentManagerService dms = new DepartmentManagerService();
        String name = req.getParameter("name");
        Department department = new Department(name);
        dms.addDepartment(department);
        resp.sendRedirect(req.getContextPath());
    }
}
