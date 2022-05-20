package com.demo.shop.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnData<T> {
    private Integer code;

    private String msg;

    private T data;
}
