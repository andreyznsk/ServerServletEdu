package ru.homeWork.tagHandler;

import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import ru.homeWork.dto.Product;

import java.io.IOException;
import java.util.List;

public class ProductListPrinter extends SimpleTagSupport {

    private List<Product> products;

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        out.println("<h2>Products list</h2><br>");
        if (products.isEmpty()) {
            out.print("Empty product list!");
        } else {
            for (Product product : products) {
                out.println(product + "<br>");
            }
        }

    }
}