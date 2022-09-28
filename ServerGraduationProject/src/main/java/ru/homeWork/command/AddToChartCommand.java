package ru.homeWork.command;

import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import ru.homeWork.chartCache.ChartCache;
import ru.homeWork.repository.ProductsRepo;
import ru.homeWork.repository.ProductsRepoDbH2;

import java.io.IOException;

@Slf4j
public class AddToChartCommand extends FrontCommand {

    ProductsRepo productsRepo = ProductsRepoDbH2.getInstance();
    ChartCache chartCache = ChartCache.getInstance();

    @Override
    public void process() throws ServletException, IOException {
        String prodId = request.getParameter("prod_id");
        chartCache.addProductToChartCacheBySessionAndUser(prodId, sessionId, user);
        response.sendRedirect( request.getContextPath() + "/index");
    }
}
