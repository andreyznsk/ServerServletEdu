package ru.homeWork.command;

import lombok.extern.slf4j.Slf4j;
import ru.homeWork.chartCache.ChartCache;
import ru.homeWork.repository.ProductsRepo;
import ru.homeWork.repository.ProductsRepoDbH2;

import java.io.IOException;

import static ru.homeWork.command.CommandTypeAndParam.COMMAND_CONTEXT;
import static ru.homeWork.command.CommandTypeAndParam.PROD_PARAM;
import static ru.homeWork.command.CommandTypeAndParam.SHOW_CHART;

@Slf4j
public class RemoveFromChartCommand extends FrontCommand {

    ProductsRepo productsRepo = ProductsRepoDbH2.getInstance();
    ChartCache chartCache = ChartCache.getInstance();

    @Override
    public void process() throws IOException {
        String prodId = request.getParameter(PROD_PARAM.getCommand());
        chartCache.removeProductFromChartBySessionAndUser(prodId, sessionId, user);
        response.sendRedirect(request.getContextPath() + COMMAND_CONTEXT.getCommand() + SHOW_CHART.getCommand());
    }
}
