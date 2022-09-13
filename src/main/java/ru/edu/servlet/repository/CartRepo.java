package ru.edu.servlet.repository;

import ru.edu.servlet.dto.Product;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartRepo {

    private static final CartRepo cartRepo = new CartRepo();
    private final Map<String, List<Product>> cartCache = new HashMap<>();

    private CartRepo(){
        this.cartCache.put("anyUser",
                Arrays.asList(
                new Product("1", "pan", 10L),
                new Product("5", "MACBOOK", 99999L)
        ));
    }

    public static CartRepo getCartRepo(){
        return cartRepo;
    }

    public Map<String, List<Product>> getAll() {
        return this.cartCache;
    }

    public List<Product> getByUser(String id) {
        return cartCache.get(id);
    }

}
