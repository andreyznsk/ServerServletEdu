package ru.homeWork.command;

import lombok.extern.slf4j.Slf4j;
import ru.homeWork.chartCache.ChartCache;
import ru.homeWork.repository.ProductsRepo;
import ru.homeWork.repository.ProductsRepoDbH2;

import java.io.IOException;

import static ru.homeWork.command.CommandTypeAndParam.INDEX_CONTEXT;
import static ru.homeWork.command.CommandTypeAndParam.PROD_PARAM;

@Slf4j
public class AddToChartCommand extends FrontCommand {

    ProductsRepo productsRepo = ProductsRepoDbH2.getInstance();
    ChartCache chartCache = ChartCache.getInstance();

    @Override
    public void process() throws IOException {
        String prodId = request.getParameter(PROD_PARAM.getCommand());
        chartCache.addProductToChartCacheBySessionAndUser(prodId, sessionId, user);
        response.sendRedirect( request.getContextPath() + INDEX_CONTEXT.getCommand());
    }
}
