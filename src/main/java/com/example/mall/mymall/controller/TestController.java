package com.example.mall.mymall.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.mall.mymall.entity.SaleGoods;

@RestController
public class TestController {
    
    @RequestMapping("/test/type/conversion")
    public void typeConversionTest(String goodsName, float weight, int type, Boolean onSale) {
        System.out.println("goodsName:" + goodsName);
        System.out.println("weight:" + weight);
        System.out.println("type:" + type);
        System.out.println("onSale:" + onSale);
    }

    @RequestMapping(value="/test/httpmessageconverter", method = RequestMethod.POST)
    public SaleGoods httpMessageConverterTest(@RequestBody SaleGoods saleGoods) {
        System.out.println(saleGoods.toString());
        saleGoods.setType(saleGoods.getType());
        saleGoods.setGoodsName("Goods Name:" + saleGoods.getGoodsName());
        return saleGoods;
    }
}
