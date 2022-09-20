package ru.edu.servlet.repository;

import ru.edu.servlet.dataSourceConnectionConfig.TomCatDataSource;
import ru.edu.servlet.dto.ProductDesc;

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
    public List<ProductDesc> getAll() throws SQLException {
        List<ProductDesc> result = new ArrayList<>();
        try(Connection connection = TomCatDataSource.getConnection();
            PreparedStatement psAccountSelectAll = connection.prepareStatement(SELECT_ALL_SQL)) {
            ResultSet rs = psAccountSelectAll.executeQuery();
            while (rs.next()){
                ProductDesc productDesc = new ProductDesc(rs.getLong(1),
                        rs.getString(2),
                        rs.getInt(3));
                result.add(productDesc);
            }
        }
            return result;
    }
}
