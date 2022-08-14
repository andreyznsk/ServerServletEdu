package ru.sbt.servlet.utils;

import ru.sbt.servlet.dto.Product;

import java.util.List;

public class TableProductGetBody {
    public static String getBodyTableByProduct(List<Product> products) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table>\n" +
                "    <tr>\n" +
                "        <th>ID</th>\n" +
                "        <th>Name</th>\n" +
                "        <th>Price</th>\n" +
                "    </tr>");
        for (Product product : products) {
            sb.append("<tr>\n" +
                    "            <td>" + product.getId() + "</td>\n" +
                    "            <td>" + product.getName() + "</td>\n" +
                    "            <td>" + product.getPrice() + "</td>\n" +
                    "        </tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
}
