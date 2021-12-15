package com.qiuhuanhen.springroot.infrastructure.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author Administrator
 */
@TableName("s_test")
public class Oper {

    @TableField("test_desc")
    private String testDesc;

    @TableField("test_xxx")
    private String testXxx;

    public String getTestDesc() {
        return testDesc;
    }

    public void setTestDesc(String testDesc) {
        this.testDesc = testDesc;
    }

    public String getTestXxx() {
        return testXxx;
    }

    public void setTestXxx(String testXxx) {
        this.testXxx = testXxx;
    }

    private static Oper oper;



}
