package com.qiuhuanhen.springroot.infrastructure.DO;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_test_t")
public class TestT {
    @TableField("des")
    private String des;
    @TableField("mark")
    private Integer mark;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getMark() {
        return mark;
    }

    public  void setMark(Integer mark) {
        this.mark = mark;
    }

    public TestT(String des, Integer mark) {
        this.des = des;
        this.mark = mark;
    }

    public TestT() {
    }
}
