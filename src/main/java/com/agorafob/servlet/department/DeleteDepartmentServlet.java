package com.agorafob.servlet.department;

import com.agorafob.service.DepartmentManagerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/departments/delete")
public class DeleteDepartmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DepartmentManagerService dms = new DepartmentManagerService();
        long id = Long.parseLong(req.getParameter("id"));
        dms.deleteDepartment(id);
        resp.sendRedirect(req.getContextPath());
    }
}
