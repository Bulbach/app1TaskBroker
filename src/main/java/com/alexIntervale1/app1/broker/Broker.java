package com.alexIntervale1.app1.broker;

import com.sun.xml.bind.v2.TODO;

import java.util.Deque;

public class Broker {
    private Deque<BaseMessage> requestQueue;
    private Deque<BaseMessage> responseQueue;

    // TODO принимает RequestRequest, возвращает RequestResponse
    public void sendRequestRequest(BaseMessage request) {
        // TODO сохранить в бд и в очередь
        // SQL insert and add in the queue
    }


    // TODO принимает ResponseRequest, возвращает ResponseResponse
    public void sendResponseRequest(BaseMessage request) {
        // TODO сохранить в бд и в очередь
        // SQL insert and add in the queue
    }


    // TODO принимает RequestId, возвращает ResponseResponse
    public void getResponseRequest(int requestId) {

    }

    // TODO принимает возвращает RequestResponse
    public void getRequestRequest() {

    }


    // TODO принимает (скорее всего ID запроса) возвращает AskResponse
    public void askRequest(int id) {

    }
}
