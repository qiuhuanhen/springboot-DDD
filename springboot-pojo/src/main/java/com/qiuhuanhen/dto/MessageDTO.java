package com.qiuhuanhen.dto;


import java.io.Serializable;


/**
 * <p>
 *
 * </p>
 *
 * @author zlx
 * @since 2021-03-19
 */


public class MessageDTO implements Serializable {

    private static final long serialVersionUID=1L;

    private String messageId;

    private Integer messageType;

    private String messageContent;

    private String messageSender;

    private String messageReceiver;

    private Integer pushStatus;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(String messageSender) {
        this.messageSender = messageSender;
    }

    public String getMessageReceiver() {
        return messageReceiver;
    }

    public void setMessageReceiver(String messageReceiver) {
        this.messageReceiver = messageReceiver;
    }

    public Integer getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Integer pushStatus) {
        this.pushStatus = pushStatus;
    }
}
