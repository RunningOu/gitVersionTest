package com.IpManage.config;

import com.baidu.boot.disconf.ConfigureProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import uyun.bird.tenant.api.entity.Product;

/**
 * @author: yangfei
 * @desc:
 * @date: created in 2018-08-07 12:39
 * @modifed by:
 */
@Configuration
@Component
public class ProductConfig {

    private String productNum;

    private String productName;


    private String description;

    private String productUrl;

    @Bean
    public Product getProduct(){

        productNum = (String) ConfigureProperties.getInstance().get("product.IpManage.productNum");
        productName = (String) ConfigureProperties.getInstance().get("product.IpManage.productName");
        description = (String) ConfigureProperties.getInstance().get("product.IpManage.description");
        productUrl = (String) ConfigureProperties.getInstance().get("product.IpManage.productUrl");

        Product product = new Product();
        product.setProductNum(productNum);
        product.setProductName(productName);
        product.setDescription(description);
        product.setProductUrl(productUrl);
        return product;
    }
}
