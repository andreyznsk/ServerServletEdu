package ru.homeWork.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CommandTypeAndParam {
    PRODUCT_LIST_PARAM("products"),
    TOTAL_PRICE("totalPrice"),
    CHART_QUANTITY_PARAM("charQuantity"),
    PROD_PARAM("prod_id"),
    USER_PARAM("username"),
    COMMAND_PARAM("command"),
    INDEX_CONTEXT("/index"),
    COMMAND_CONTEXT("/index?command="),
    AUTH("Auth"),
    PRODUCT_VIEW("ProductView"),
    SHOW_CHART("ShowChart");

    private final String command;

}
