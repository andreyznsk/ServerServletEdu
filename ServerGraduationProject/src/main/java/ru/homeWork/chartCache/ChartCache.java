package ru.homeWork.chartCache;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ChartCache {

    private final ConcurrentHashMap<String, List<String>> userChart;
    private static final ChartCache CHART_CACHE = new ChartCache();


    private ChartCache() {
        this.userChart = new ConcurrentHashMap<>();
    }

    public static ChartCache getInstance(){
        return CHART_CACHE;
    }

    public List<String> getProductPksBySessionAndUser(String session, String user){
       return userChart.get(getKey(session, user)) == null ? Collections.emptyList() : userChart.get(getKey(session, user));
    }

    public int getQuantityBySessionAndUser(String session, String user){
        return userChart.getOrDefault(getKey(session, user), Collections.emptyList()).size();

    }

    public void addProductToChartCacheBySessionAndUser(String prod_id, String session, String user) {
        userChart.computeIfAbsent(getKey(session, user), s -> new ArrayList<>()).add(prod_id);
    }

    public void removeProductFromChartBySessionAndUser(String prod_id, String session, String user) {
        List<String> orDefault = userChart.getOrDefault(getKey(session, user), Collections.emptyList());
        orDefault.remove(prod_id);

    }

    private void removeAllChartBySessionAndUser(String session, String user) {
        userChart.remove(getKey(session, user));
    }

    private String getKey(String session, String user) {
        return session + ":" + user;
    }

}
