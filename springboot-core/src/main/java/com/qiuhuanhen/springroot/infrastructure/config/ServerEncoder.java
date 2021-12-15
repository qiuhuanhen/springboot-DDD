package com.qiuhuanhen.springroot.infrastructure.config;

import com.alibaba.fastjson.JSON;
import com.qiuhuanhen.dto.MessageDTO;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.nio.charset.StandardCharsets;

/**
 * ws 编码器
 * @author qkj
 */
public class ServerEncoder implements Encoder.Text<MessageDTO> {


    @Override
    public String encode(MessageDTO message) {

        return new String(String.valueOf(JSON.toJSON(message)).getBytes(), StandardCharsets.UTF_8);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

}
