package com.future.observercommon.constant;

/**
 * 非法信息处理状态
 */
public enum ProcessingStatus {

    UNTREATED("未处理"),
    PROCESSING("处理中"),
    PROCESSED("已处理");

    private String name;

    ProcessingStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
