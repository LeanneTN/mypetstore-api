package org.csu.mypetstore.api.vo;

import lombok.Data;

@Data
public class ChatUserVO {
    private String id;
    private String displayName;
    private String avatar;

    public ContactDataVO toContactDataVO(){
        ContactDataVO contactDataVO = new ContactDataVO();
        contactDataVO.setId(this.id);
        contactDataVO.setDisplayName(this.displayName);
        contactDataVO.setAvatar(this.avatar);
        contactDataVO.setIndex(this.displayName.substring(0,1).toUpperCase());
        contactDataVO.setUnread(0);
        contactDataVO.setLastSendTime(null);
        contactDataVO.setLastContent("");
        return contactDataVO;
    }
}
