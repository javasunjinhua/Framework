package com.bigdata.service;

import com.bigdata.pojo.Seller;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/10/24 16:17
 * @description
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    private SellerService sellerService;
    public void setSellerService(SellerService sellerService) {

        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String  username) throws UsernameNotFoundException {

        Seller seller = sellerService.findOne(username);
        if (seller != null && seller.getStatus().equals("1")){
            //实际中还可以根据username查询该用户具有的角色权限,然后放入到list中
            List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
            list.add(new SimpleGrantedAuthority("ROLE_SELLER"));
            return new User(username,seller.getPassword(), list);
        }else{
            return null;
        }
    }
}
