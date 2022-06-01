package org.csu.mypetstore.api.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MessageVO {
    private String id;
    private String status;
    private String type;
    private Date sendTime;
    private Object content;
    private String toContactId;
    private ChatUserVO fromUser;
}
