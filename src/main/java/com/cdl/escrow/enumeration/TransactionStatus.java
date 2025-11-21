package com.cdl.escrow.enumeration;

import lombok.Getter;

@Getter
public enum TransactionStatus {

    SUCCESS("success"),
    FAILED("failed"),
    RETRIED("retried"),
    RETRYING("retrying"),
    SCHEDULED("scheduled");

    private final String value;

    TransactionStatus(String value) {
        this.value = value;
    }

}
