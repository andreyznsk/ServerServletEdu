package ru.edu.servlet.utils;

import ru.edu.servlet.dto.Product;

import java.util.List;

public class TableProductGetBody {
    public static String getBodyTableByProduct(List<Product> products) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table>\n")
                .append("    <tr>\n")
                .append("        <th>ID</th>\n")
                .append("        <th>Name</th>\n")
                .append("        <th>Price</th>\n")
                .append("    </tr>");
        for (Product product : products) {
            sb.append("<tr>\n")
                    .append("<td>").append(product.getId()).append("</td>\n")
                    .append("<td>").append(product.getName()).append("</td>\n")
                    .append("<td>").append(product.getPrice()).append("</td>\n")
                    .append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
}
