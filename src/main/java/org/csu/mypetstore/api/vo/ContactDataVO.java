package org.csu.mypetstore.api.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ContactDataVO {
    private String id;
    private String displayName;
    private String avatar;
    private String index;
    private int unread;
    private Date lastSendTime;
    private String lastContent;
}
