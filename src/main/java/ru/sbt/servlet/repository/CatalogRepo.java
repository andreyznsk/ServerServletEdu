package ru.sbt.servlet.repository;

import ru.sbt.servlet.dto.Product;

import java.util.Arrays;
import java.util.List;

public class CatalogRepo {

    private static CatalogRepo catalogRepo = new CatalogRepo();
    private List<Product> productListCache;

    private CatalogRepo(){
         this.productListCache = Arrays.asList(
                new Product("1", "pan", 10L),
                new Product("2", "calculator", 20L),
                new Product("3", "book", 110L),
                new Product("4", "notepad", 10L),
                new Product("5", "MACBOOK", 99999L)
        );
    }

    public static CatalogRepo getCatalogRepo(){
        return catalogRepo;
    }

    public List<Product> getAll() {
        return this.productListCache;
    }


}
