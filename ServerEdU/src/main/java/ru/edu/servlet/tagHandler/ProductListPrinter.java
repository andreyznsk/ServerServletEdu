package ru.edu.servlet.tagHandler;

import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import ru.edu.servlet.dto.ProductDesc;

import java.io.IOException;
import java.util.List;

public class ProductListPrinter extends SimpleTagSupport {

    private List<ProductDesc> products;

    public void setProducts(List<ProductDesc> products) {
        this.products = products;
    }

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        out.println("<h2>Products list</h2><br>");
        if (products.isEmpty()) {
            out.print("Empty product list!");
        } else {
            for (ProductDesc product : products) {
                out.println(product + "<br>");
            }
        }

    }
}