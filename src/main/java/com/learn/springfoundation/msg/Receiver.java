package com.learn.springfoundation.msg;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
class Receiver {

    private final CountDownLatch latch = new CountDownLatch(1);

    public void receiveMsg(String msg) {
        System.out.println("MSG Received: " + msg);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
