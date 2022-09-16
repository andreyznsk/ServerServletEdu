package ru.edu.servlet.repository;

import ru.edu.servlet.dto.ProductDesc;

import java.sql.SQLException;
import java.util.List;

public interface ProductsRepo {
    List<ProductDesc> getAll() throws SQLException;
}
