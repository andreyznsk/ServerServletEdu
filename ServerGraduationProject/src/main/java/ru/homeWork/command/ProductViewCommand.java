package ru.homeWork.command;

import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import ru.homeWork.chartCache.ChartCache;
import ru.homeWork.dto.Product;
import ru.homeWork.repository.ProductsRepo;
import ru.homeWork.repository.ProductsRepoDbH2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static ru.homeWork.command.CommandTypeAndParam.CHART_QUANTITY_PARAM;
import static ru.homeWork.command.CommandTypeAndParam.PRODUCT_LIST_PARAM;
import static ru.homeWork.command.CommandTypeAndParam.PRODUCT_VIEW;

@Slf4j
public class ProductViewCommand extends FrontCommand {

    ProductsRepo productsRepo = ProductsRepoDbH2.getInstance();
    ChartCache chartCache = ChartCache.getInstance();

    @Override
    public void process() throws ServletException, IOException {
        List<Product> all;
        try {
            all = productsRepo.getAll();
        } catch (SQLException e) {
            log.error("SQL error:{}", e.toString());
            response.sendError(500, e.getMessage());
            return;
        }
        request.setAttribute(PRODUCT_LIST_PARAM.getCommand(), all);
        int quantityProdInChart = chartCache.getQuantityBySessionAndUser(request.getSession().getId(), user);

        request.setAttribute(CHART_QUANTITY_PARAM.getCommand(), quantityProdInChart);

        forward(PRODUCT_VIEW.getCommand());

    }
}
