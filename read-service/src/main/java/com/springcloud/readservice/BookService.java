package com.springcloud.readservice;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
class BookService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Lazy
    private EurekaClient discoveryClient;

    @HystrixCommand(fallbackMethod = "reliable")
    String readingList() {
        return this.restTemplate.getForObject(URI.create(serviceUrl() + "recommended"), String.class);
    }

    @HystrixCommand(fallbackMethod = "reliableBuy")
    String buy() {
        return this.restTemplate.getForObject(URI.create(serviceUrl() + "buy"), String.class);
    }

    private String serviceUrl() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("RECOMMENDED", false);
        return instance.getHomePageUrl();
    }

    String reliableBuy() {
        return "Any Java 8 Book";
    }

    String reliable() {
        return "Cloud Native Java (O'Reilly)";
    }
}
