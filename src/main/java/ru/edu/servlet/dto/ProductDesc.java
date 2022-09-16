package ru.edu.servlet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductDesc {
    private final Long id;
    private final String name;
    private Integer desc;
}
