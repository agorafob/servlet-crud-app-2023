package com.agorafob.servlet.department;

import com.agorafob.model.Department;
import com.agorafob.service.DepartmentManagerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/departments/edit")
public class EditDepartmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DepartmentManagerService dms = new DepartmentManagerService();
        long id = Long.parseLong(req.getParameter("id"));
        Department department = dms.getDepartment(id);
        if (Objects.nonNull(department)) {
            req.setAttribute("department", department);
            req.getServletContext().getRequestDispatcher("/view/department/edit/index.jsp").forward(req, resp);
        } else {
            req.getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DepartmentManagerService dms = new DepartmentManagerService();
        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        Department department = new Department(id,name);
        dms.updateDepartment(department);
        resp.sendRedirect(req.getContextPath());
    }
}
