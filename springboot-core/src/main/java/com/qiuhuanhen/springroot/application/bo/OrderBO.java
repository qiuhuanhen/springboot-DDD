package com.qiuhuanhen.springroot.application.bo;

import cn.vae.singer.framework.mq.bean.RocketMqBaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderBO extends RocketMqBaseDTO {
    private String context;

    public OrderBO() {
    }

    public OrderBO(String context) {
        this.context = context;
    }
}
