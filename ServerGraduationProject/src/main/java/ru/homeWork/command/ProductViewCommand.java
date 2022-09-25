package ru.homeWork.command;

import jakarta.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;

public class ProductViewCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        /*Book book = new BookshelfImpl().getInstance()
                .findByTitle(request.getParameter("title"));
        if (book != null) {
            request.setAttribute("book", book);
            forward("book-found");
        } else {
            forward("book-notfound");
        }*/
        String user = "anyUser";

        PrintWriter out = response.getWriter();
        out.println("<h2>cart by user: [" + user + "} catalog</n2>");
        //List<Product> byUser = cartRepo.getByUser(user);
        out.println("getBodyTableByProduct(byUser)");
    }
}
