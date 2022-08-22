package ru.edu.servlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.edu.servlet.dto.Product;
import ru.edu.servlet.repository.CatalogRepo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static ru.edu.servlet.utils.TableProductGetBody.getBodyTableByProduct;

public class CatalogController extends HttpServlet {

  CatalogRepo catalogRepo = CatalogRepo.getCatalogRepo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        /*String catalogView = "/" + getInitParameter("catalogView");
        req.setAttribute("productList", catalog);
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(catalogView);
        dispatcher.include(req, resp);*/

        PrintWriter out = resp.getWriter();
        out.println("<h2>Product catalog</n2>");
        List<Product> all = catalogRepo.getAll();
        out.println(getBodyTableByProduct(all));
        out.close();
    }
}
