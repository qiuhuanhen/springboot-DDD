package com.qiuhuanhen.dto;

public class HungryDTO {
    static int count = 0;
    private HungryDTO() {
        count++;
        System.out.println(System.currentTimeMillis()+"---执行时间");
        if (count > 1) {
            throw new RuntimeException();
        }
    }
    public volatile static HungryDTO hungry = new HungryDTO();


    public static HungryDTO getInstance() {
        return hungry;
    }
}
