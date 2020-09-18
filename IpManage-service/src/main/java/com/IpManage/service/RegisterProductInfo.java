package com.IpManage.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import uyun.bird.tenant.api.ProductService;
import uyun.bird.tenant.api.entity.Product;

/**
 * Date: 2017/9/26
 * Author: yangfei
 * Description:
 */
@Service
public class RegisterProductInfo implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger log = LoggerFactory.getLogger(RegisterProductInfo.class);

    @Reference(protocol="dubbo")
    private ProductService productService;

    @Override
    @SuppressWarnings("static-access")
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        Product producy_bat = context.getBean(Product.class);
        if (producy_bat == null) {
            log.error("Product does not exist! process aborted");
            System.exit(1);
        }
        log.info("Pushdata to Protal task thread Start......");

        int retry = 6;
        do {
            if (register(producy_bat)) {
                break;
            }

            try {
                // 等10秒准备重连租户
                Thread.currentThread().sleep(10 * 1000);
            } catch (Throwable e) {
                // 吃掉
            }
            retry = retry - 1;
        } while (retry > 0);
    }

    private boolean register(Product producy_bat) {
        try {
            log.warn("-----------------------------");
            log.warn(producy_bat.getProductNum());
            log.warn(producy_bat.getProductName());
            log.warn(producy_bat.getDescription());
            log.warn(producy_bat.getProductUrl());
            log.warn("-----------------------------");
            productService.save(producy_bat);
            log.info("register product success!");
            return true;
        } catch (Throwable e) {
            log.error("Product fail to register！" + e.getMessage());
            if (log.isDebugEnabled()) {
                log.debug("Stack：", e);
            }
            return false;
        }
    }


}
