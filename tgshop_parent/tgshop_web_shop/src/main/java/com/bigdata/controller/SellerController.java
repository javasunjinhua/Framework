package com.bigdata.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bigdata.mypojo.Result;
import com.bigdata.pojo.Seller;
import com.bigdata.service.SellerService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2018/10/23 20:08
 * @description
 */
@RestController
@RequestMapping("seller")
public class SellerController {

    @Reference
    private SellerService sellerService;

    @RequestMapping("add")
    public Result add(@RequestBody Seller seller) throws Exception {


        try {
            // 密码加密
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode(seller.getPassword());
            seller.setPassword(password);
            sellerService.add(seller);
            return new Result(true, "添加成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败!");
        }
    }

    @RequestMapping("/findOne")
    public Seller findOne(String id) {
        return   sellerService.findOne(id);

    }
}
