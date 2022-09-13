package ru.edu.servlet.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import ru.edu.servlet.dataSourceConnectionConfig.TomCatDataSource;
import ru.edu.servlet.dto.ProductDesc;
import ru.edu.servlet.repository.ProductsRepo;
import ru.edu.servlet.repository.ProductsRepoImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "DataBaseControllerServlet", urlPatterns = {"/data_base_controller_servlet"}, loadOnStartup = 1)
@Slf4j
public class DataBaseControllerServlet extends HttpServlet {

    private ProductsRepo productsRepo;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            List<ProductDesc> all = productsRepo.getAll();
            req.setAttribute("products", all);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/ProductDescView.jsp");
            rd.forward(req, resp);
        } catch (SQLException e) {
            log.error("Sql err: {}", e.toString());
        }

    }

    @Override
    public void init() throws ServletException {
        TomCatDataSource.conf();
        productsRepo = ProductsRepoImpl.getInstance();
        super.init();
    }
}
