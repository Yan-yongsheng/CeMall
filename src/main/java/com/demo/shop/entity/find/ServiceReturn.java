package com.demo.shop.entity.find;

import lombok.Data;

import java.util.List;

@Data
public class ServiceReturn {
    public int totalService;

    public List<ServiceFind> serviceFindsList;
}
