package com.qiuhuanhen.vo;

public class OperVO {

    private volatile static OperVO instance;

    private OperVO() {
    }

    public static OperVO getInstance() {
        if (null == instance) {
            synchronized (OperVO.class) {
                if (null == instance) {
                    instance = new OperVO();
                }
            }
        }
        return instance;
    }

    private String testDesc;

    public static void setInstance(OperVO instance) {
        OperVO.instance = instance;
    }

    public String getTestDesc() {
        return testDesc;
    }

    public void setTestDesc(String testDesc) {
        this.testDesc = testDesc;
    }
}
