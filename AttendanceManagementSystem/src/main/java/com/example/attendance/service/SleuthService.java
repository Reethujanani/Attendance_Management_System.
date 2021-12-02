package com.example.attendance.service;


import com.sun.istack.logging.Logger;

import org.springframework.stereotype.Service;

@Service
public class SleuthService<Span> {

    public void doSomeWorkSameSpan() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Logger logger = null;
        logger.info("Doing some work");
    }
}

    
    

