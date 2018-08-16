package com.forezp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by fangzhipeng on 2017/4/6.
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    public String hiService(String name) {
        //service-hi  注册2个实例，端口8762 8763，访问方法：localhost:8762/hi?name=123.这是单例访问方法。
        //通过ribbon  负载均衡，则通过 SERVICE-HI  来代表 localhost:8762
        //hiService  方法代理了localhost:8762/hi?name=123  这个URL。
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);


//        在浏览器上多次访问http://localhost:8764/hi?name=forezp，浏览器交替显示：
//
//        hi forezp,i am from port:8762
//        hi forezp,i am from port:8763
//        这说明当我们通过调用restTemplate.getForObject(“http://SERVICE-HI/hi?name=“+name,String.class)方法时，已经做了负载均衡，访问了不同的端口的服务实例。
    }

}
