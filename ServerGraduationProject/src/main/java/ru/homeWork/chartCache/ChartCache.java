package ru.homeWork.chartCache;

import ru.homeWork.dto.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ChartCache {

    private final ConcurrentHashMap<String, List<Product>> userChart;
    private static final ChartCache CHART_CACHE = new ChartCache();


    private ChartCache() {
        this.userChart = new ConcurrentHashMap<>();
    }

    public static ChartCache getInstance(){
        return CHART_CACHE;
    }

    public List<Product> getAllBySession(String session){
       return userChart.get(session);
    }

    public int getQuantityBySession(String session){
        return userChart.getOrDefault(session, Collections.emptyList()).size();

    }

    public void addProductToChartCacheBySession(Product product, String session) {
        userChart.computeIfAbsent(session, s -> new ArrayList<>()).add(product);
    }

    private void removeAllChartBySession(String session) {
        userChart.remove(session);
    }

}
