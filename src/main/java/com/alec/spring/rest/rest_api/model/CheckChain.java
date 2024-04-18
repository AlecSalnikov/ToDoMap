package com.alec.spring.rest.rest_api.model;

public class CheckChain {
    public static Status findStatus(StatusCheck statusCheck) {

        switch (statusCheck) {
            case NEW:
                return Status.NEW;
            case DONE:
                return Status.DONE;
            case IN_PROGRESS:
                return Status.IN_PROGRESS;
            default:
                return null;
        }
    }
}
