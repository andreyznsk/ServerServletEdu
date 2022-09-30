package ru.homeWork.repository;

import ru.homeWork.dataSourceConnectionConfig.TomCatDataSource;
import ru.homeWork.dto.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsRepoDbH2 implements ProductsRepo {

    private static final ProductsRepoDbH2 PRODUCTS_REPO_IMPL = new ProductsRepoDbH2();
    private static final String SELECT_ALL_SQL = "SELECT * FROM PRODUCTS;";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM PRODUCTS WHERE PROD_ID IN %s;";

    private ProductsRepoDbH2(){}

    public static ProductsRepo getInstance() {
        return PRODUCTS_REPO_IMPL;
    }


    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> result = new ArrayList<>();
        try(Connection connection = TomCatDataSource.getConnection();
            PreparedStatement select = connection.prepareStatement(SELECT_ALL_SQL)) {
            ResultSet rs = select.executeQuery();
            while (rs.next()){
                Product product = new Product(rs.getString(1),
                        rs.getDouble(2),
                        rs.getString(3));
                result.add(product);
            }
        }
            return result;
    }

    @Override
    public List<Product> getByIds(List<String> id) throws SQLException {
        List<Product> result = new ArrayList<>();
        if(id.isEmpty()) {
            return result;
        } else {
            String sqlIN = id.stream()
                    .collect(Collectors.joining("','", "('", "')"));
            String formattedSql = String.format(SELECT_BY_ID_SQL, sqlIN);

            try (Connection connection = TomCatDataSource.getConnection();
                 PreparedStatement select = connection.prepareStatement(formattedSql)) {

                ResultSet rs = select.executeQuery();
                while (rs.next()) {
                    Product product = new Product(rs.getString(1),
                            rs.getDouble(2),
                            rs.getString(3));
                    result.add(product);
                }
            }
            return result;
        }
    }
}
