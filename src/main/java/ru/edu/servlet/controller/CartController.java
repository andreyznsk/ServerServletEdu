package ru.edu.servlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.edu.servlet.dto.Product;
import ru.edu.servlet.repository.CartRepo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static ru.edu.servlet.utils.TableProductGetBody.getBodyTableByProduct;

public class CartController extends HttpServlet {

    CartRepo cartRepo = CartRepo.getCartRepo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        /*String catalogView = "/" + getInitParameter("catalogView");
        req.setAttribute("productList", catalog);
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(catalogView);
        dispatcher.include(req, resp);*/
        String user = "anyUser";

        PrintWriter out = resp.getWriter();
        out.println("<h2>cart by user: [" + user + "} catalog</n2>");
        List<Product> byUser = cartRepo.getByUser(user);
        out.println(getBodyTableByProduct(byUser));
        out.close();
    }
}
