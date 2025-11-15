package com.vanhuynh.aopdemo.service;

import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class TrafficFortuneServiceImpl implements TrafficFortuneService {
    @Override
    public String getFortune() {
        // simulate
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // return a fortune
        return "Expect heavy traffic this morning";
    }
}
