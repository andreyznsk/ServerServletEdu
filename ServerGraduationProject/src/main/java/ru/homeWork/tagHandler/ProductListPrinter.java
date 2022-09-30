package ru.homeWork.tagHandler;

import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import lombok.Setter;
import ru.homeWork.dto.Product;

import java.io.IOException;
import java.util.List;

import static ru.homeWork.command.CommandTypeAndParam.COMMAND_CONTEXT;
import static ru.homeWork.command.CommandTypeAndParam.PROD_PARAM;

@Setter
public class ProductListPrinter extends SimpleTagSupport {

    private List<Product> products;

    private String action;


    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        out.println("<h2>Products list</h2><br>");
        if (products.isEmpty()) {
            out.print("Empty product list!");
        } else {
            final PageContext pageContext = (PageContext) getJspContext();
            String contextPath = pageContext.getServletContext().getContextPath();

            StringBuilder sb = new StringBuilder();
            sb.append("<table>\n")
                    .append("<tr>\n")
                    .append("<th>ID</th>\n")
                    .append("<th>Name</th>\n")
                    .append("<th>Price</th>\n")
                    .append("<th>Action</th>\n")
                    .append("</tr>");
            for (Product product : products) {
                sb.append("<tr>\n")
                        .append("<td>").append(product.getId()).append("</td>\n")
                        .append("<td>").append(product.getName()).append("</td>\n")
                        .append("<td>").append(product.getPrice()).append("</td>\n")
                        .append("<td>").append("<a href=\"").append(contextPath)
                            .append(COMMAND_CONTEXT.getCommand()).append(action).append("&").append(PROD_PARAM.getCommand()).append("=")
                        .append(product.getId()).append("\" charset=\"UTF-8\">").append(action).append("</a></td>\n")
                        .append("</tr>");
            }
            sb.append("</table>");
            out.println(sb.toString());
        }

    }
}