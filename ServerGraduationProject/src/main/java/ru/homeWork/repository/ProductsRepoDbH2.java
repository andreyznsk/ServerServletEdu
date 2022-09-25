package ru.homeWork.repository;

import ru.homeWork.dataSourceConnectionConfig.TomCatDataSource;
import ru.homeWork.dto.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsRepoDbH2 implements ProductsRepo {

    private static final ProductsRepoDbH2 PRODUCTS_REPO_IMPL = new ProductsRepoDbH2();
    private static final String SELECT_ALL_SQL = "SELECT * FROM PRODUCTS;";

    private ProductsRepoDbH2(){}

    public static ProductsRepo getInstance() {
        return PRODUCTS_REPO_IMPL;
    }


    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> result = new ArrayList<>();
        try(Connection connection = TomCatDataSource.getConnection();
            PreparedStatement psAccountSelectAll = connection.prepareStatement(SELECT_ALL_SQL)) {
            ResultSet rs = psAccountSelectAll.executeQuery();
            while (rs.next()){
                Product product = new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getLong(3));
                result.add(product);
            }
        }
            return result;
    }
}
