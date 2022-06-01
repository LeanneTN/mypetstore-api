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
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/api/chat", encoders = WebSocketMessageVOEncoding.class, configurator = GetHttpSessionConfigurator.class)
@Component
public class ChatEndpoint {

    //用来存储每一个客户端对象的ChatEndPoint对象
    private static Map<ChatUserVO,ChatEndpoint> onlineUsers = new ConcurrentHashMap<>();

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
        ChatUserVO chatUser = accountVO.toChatUserVO();
        onlineUsers.put(chatUser, this);

        //向其他所有用户通知有新的用户登录
        newUserEnter(chatUser);

        //向该用户发送当前在线用户
        otherUsers(chatUser);
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
    private Set<ChatUserVO> getOnlineUsers(){
        return onlineUsers.keySet();
    }

    //向其他所有用户通知有新的用户登录
    public void newUserEnter(ChatUserVO chatUserVO){
        ContactDataVO contactDataVO = chatUserVO.toContactDataVO();
        List<ContactDataVO> contactDataVOList = new ArrayList<>();
        contactDataVOList.add(contactDataVO);
        MessageVO messageVO = broadCastMessage(contactDataVOList);
        try {
            Set<ChatUserVO> chatUsers = onlineUsers.keySet();
            for (ChatUserVO chatUser : chatUsers){
                //如果是其他用户，就发送消息
                if(!chatUser.getId().equals(chatUserVO.getId())){
                    ChatEndpoint chatEndpoint = onlineUsers.get(chatUser);
                    chatEndpoint.session.getBasicRemote().sendObject(messageVO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //向某个用户发送其他在线用户的信息
    public void otherUsers(ChatUserVO chatUserVO){
        ChatEndpoint chatEndpoint = onlineUsers.get(chatUserVO);

        List<ContactDataVO> contactDataVOList = new ArrayList<>();
        Set<ChatUserVO> chatUsers = onlineUsers.keySet();
        for (ChatUserVO chatUser : chatUsers){
            //如果是其他用户，就添加进列表
            if(!chatUser.getId().equals(chatUserVO.getId())){
                contactDataVOList.add(chatUser.toContactDataVO());
            }
        }
        try {
            chatEndpoint.session.getBasicRemote().sendObject(broadCastMessage(contactDataVOList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //创建一个广播消息（广播消息的发送者和接收者均为null）
    public MessageVO broadCastMessage(Object obj){
        MessageVO messageVO = new MessageVO();
        messageVO.setId("");
        messageVO.setStatus("");
        messageVO.setType("object");
        messageVO.setSendTime(new Date());
        messageVO.setContent(obj);
        //这表示该消息是发送给所有用户的
        messageVO.setToContactId(null);
        messageVO.setFromUser(null);

        return messageVO;
    }
}
