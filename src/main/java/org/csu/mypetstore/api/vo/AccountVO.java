package org.csu.mypetstore.api.vo;

import lombok.Data;

@Data
public class AccountVO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String status;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    private String favouriteCategoryId;
    private String languagePreference;
    private boolean listOption;
    private boolean bannerOption;
    private String bannerName;

    //将accountVO转化成ChatUserVO
    public ChatUserVO toChatUserVO(){
        ChatUserVO chatUserVO = new ChatUserVO();
        chatUserVO.setAvatar("");
        chatUserVO.setId(this.username);
        chatUserVO.setDisplayName(this.username);
        return chatUserVO;
    }
}
