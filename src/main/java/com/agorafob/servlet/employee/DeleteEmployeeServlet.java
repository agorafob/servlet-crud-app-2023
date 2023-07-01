package com.agorafob.servlet.employee;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.agorafob.service.EmployeeManageService;

import java.io.IOException;

@WebServlet("/employees/delete")
public class DeleteEmployeeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(req.getParameter("id"));
        EmployeeManageService ems = new EmployeeManageService();
            ems.delete(id);
            resp.sendRedirect(req.getContextPath());
    }
}
