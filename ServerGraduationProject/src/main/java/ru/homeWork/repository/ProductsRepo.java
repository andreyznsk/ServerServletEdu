package ru.homeWork.repository;


import ru.homeWork.dto.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductsRepo {
    List<Product> getAll() throws SQLException;

    List<Product> getByIds(List<String> id) throws SQLException;

}
