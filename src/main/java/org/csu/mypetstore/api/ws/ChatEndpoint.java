package org.csu.mypetstore.api.ws;

import org.csu.mypetstore.api.config.WebSocketMessageVOEncoding;
import org.csu.mypetstore.api.vo.AccountVO;
import org.csu.mypetstore.api.vo.ChatUserVO;
import org.csu.mypetstore.api.vo.ContactDataVO;
import org.csu.mypetstore.api.vo.MessageVO;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/api/chat", encoders = WebSocketMessageVOEncoding.class, configurator = GetHttpSessionConfigurator.class)
@Component
public class ChatEndpoint {

    //用来存储每一个客户端对象的ChatEndPoint对象
    private static Map<String,ChatEndpoint> onlineUsers = new ConcurrentHashMap<>();

    //声明Session对象，通过该对象可以把消息发送给指定的用户
    private Session session;

    //声明一个HttpSession对象，我们之前再HttpSession对象中存储了用户信息
    private HttpSession httpSession;

    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig){
        //建立连接时调用
        //将局部的session赋值给session
        this.session = session;
        //获取HttpSession对象
        HttpSession httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;

        //从httpSession中获取登录用户，并存储到容器中
        AccountVO accountVO = (AccountVO) httpSession.getAttribute("login_account");
        onlineUsers.put(accountVO.getUsername(), this);

        //将当前在线用户的信息推送给所有客户端
        ContactDataVO contactDataVO = new ContactDataVO();
        contactDataVO.setId("");
        contactDataVO.setDisplayName(accountVO.getUsername());
        contactDataVO.setAvatar("");
        contactDataVO.setIndex(accountVO.getUsername().substring(0,1).toUpperCase());
        contactDataVO.setUnread(0);
        contactDataVO.setLastSendTime(null);
        contactDataVO.setLastContent("");

        MessageVO messageVO = new MessageVO();
        messageVO.setId("");
        messageVO.setStatus("");
        messageVO.setType("object");
        messageVO.setSendTime(new Date());
        messageVO.setContent(contactDataVO);
        //这表示该消息是发送给所有用户的
        messageVO.setToContactId(null);
        ChatUserVO chatUserVO = new ChatUserVO();
        chatUserVO.setAvatar("");
        chatUserVO.setId(accountVO.getUsername());
        chatUserVO.setDisplayName(accountVO.getUsername());
        messageVO.setFromUser(chatUserVO);

        broadcastAllUsers(messageVO);
    }


    @OnMessage
    public void onMessage(String message, Session session){
        //接收到消息时调用
    }

    @OnClose
    public void onClose(Session session){
        //链接关闭时调用
    }

    //获取当前所有在线用户
    private Set<String> getOnlineUsers(){
        return onlineUsers.keySet();
    }

    //广播消息
    private void broadcastAllUsers(MessageVO messageVO){
        try {
            Set<String> usernames = onlineUsers.keySet();
            for (String username : usernames){
                ChatEndpoint chatEndpoint = onlineUsers.get(username);
                chatEndpoint.session.getBasicRemote().sendObject(messageVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
