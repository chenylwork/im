package com.yanshang.car.im.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanshang.car.im.bean.Message;
import com.yanshang.car.im.bean.RoomUser;
import com.yanshang.car.im.bean.User;
import com.yanshang.car.im.commons.Character;
import com.yanshang.car.im.commons.NetMessage;
import com.yanshang.car.im.commons.RedisUtils;
import com.yanshang.car.im.commons.SerializeUtil;
import com.yanshang.car.im.repositories.UserRepository;
import com.yanshang.car.im.services.MessageServices;
import com.yanshang.car.im.services.RoomServices;
import com.yanshang.car.im.services.RoomUserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * @ClassName JSR356WebSocketServer
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/11- 8:52
 * @Version 1.0
 **/
@ServerEndpoint(value = "/socket/{username}")
@Component
public class JSR356WebSocketServer {

    private static String messageToAllToken; // 发送给全部用户标识
    private static RoomUserServices roomUserServices;
    private static RoomServices roomServices;
    private static MessageServices messageServices;
    private static UserRepository userRepository;

    private static final Logger logger =  LoggerFactory.getLogger(JSR356WebSocketServer.class);
    private static int onlineCount = 0; // 在线人数
    private static final Map<String,Session> sessionMap = new HashMap<>();
    /**
     * 创建的用户给予创建webSocket的会话session
     * 没有创建的session
     * @param username
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {
        User user = userRepository.getByUsername(username);
        if (user == null) {
            session.close();
            return;
        }
        onlineSocket(username,session);
        System.out.println("在线人数："+JSR356WebSocketServer.onlineCount);
        // 发送未读消息
        List<Message> messageList = null;
        NetMessage unreadMessage = messageServices.getUnreadMessage(username);
        if (unreadMessage.getStatus() == NetMessage.SUCCESS) {
            messageList = (List<Message>)unreadMessage.getContent();
            sendMessageMoreToOne(username,messageList);
        }
    }
    @OnMessage
    public void onMessage(String content,Session session) {
        ObjectMapper objectMapper = new ObjectMapper();
        String to = "";
        NetMessage netMessage = null;
        try {
            Message message = objectMapper.readValue(content, Message.class);
            // 入库
            netMessage = messageServices.addMessage(message);
            if (netMessage.getStatus() == NetMessage.FAIl) {
                logger.info(netMessage.getContent()+"");
                return;
            }
            // 设置自己已读
            roomUserServices.changeCount(message.getFrom(),Integer.parseInt(message.getTo()));
            // 发送给接受者
            sendMessageOneToMore(message);
        } catch (Exception e) {
            e.printStackTrace();
            if (netMessage != null && netMessage.getStatus() == NetMessage.FAIl) {
                logger.info(netMessage.getContent()+"");
            }
            return;
        }
    }


    @OnError
    public void onError(Throwable throwable,@PathParam("username")String username) {
        throwable.printStackTrace();
    }

    @OnClose
    public void myOnClose(CloseReason reason,@PathParam("username")String username,Session session){
        logger.info("由于"+ reason.getReasonPhrase()+"而关闭WebSocket");
        offline(username);
    }

    /**
     * 上线用户
     * @param username
     * @param session
     */
    public void onlineSocket(String username,Session session) {
        String key = getKey(username);
        System.out.println(username+":"+key);
        sessionMap.put(key,session);
        JSR356WebSocketServer.onlineCount++;
    }

    /**
     * 下线用户
     * @param username
     */
    public void offline(String username) {
        String key = getKey(username);
        sessionMap.remove(key);
        JSR356WebSocketServer.onlineCount--;
    }

    /**
     * 发送一条消息给多个人
     * @param message 消息POJO
     */
    public void sendMessageOneToMore(Message message) {
        String roomid = message.getTo();
        int roomID = Integer.parseInt(roomid);
        List<RoomUser> roomUsers = roomUserServices.getByRoomid(roomID);
        roomUsers.forEach(data -> {
            String username = data.getUsername();
            sendMessage(username,message);
        });
    }
    /**
     * 发送多个消息给一个人
     * @param username
     * @param messageList
     */
    public void sendMessageMoreToOne(String username,List<Message> messageList) {
        for (Message message : messageList) {
            sendMessage(username,message);
        }
    }

    /**
     * 发送消息
     * @param username
     * @param message
     */
    public void sendMessage(String username,Message message) {
        String content = "";
        String from = message.getFrom();
        int roomid = Integer.parseInt(message.getTo());
        try {
            content = new ObjectMapper().writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String key = getKey(username);
        if (sessionMap.containsKey(key) && !username.equals(from)) {
            Session session = sessionMap.get(key);
            session.getAsyncRemote().sendText(content);
            roomUserServices.changeCount(username,roomid);
        }

    }



    /**
     * 获取存储key
     * @param username
     * @return
     */
    public String getKey(String username) {
        String encodeUsername = Character.redisKeyEncode(username);
        return KEY_PRIFIX+encodeUsername;
    }
    /**
     * redis存储session的标识头
     */
    private static final String KEY_PRIFIX = "socket_";
    @Value("${socket.message.to.all.token}")
    private void setMessageToAllToken(String messageToAllToken) {
        JSR356WebSocketServer.messageToAllToken=messageToAllToken;
    }
    @Autowired
    public void setRoomUserServices(RoomUserServices roomUserServices) {
        JSR356WebSocketServer.roomUserServices = roomUserServices;
    }
    @Autowired
    public void setRoomServices(RoomServices roomServices) {
        JSR356WebSocketServer.roomServices = roomServices;
    }
    @Autowired
    public void setMessageServices(MessageServices messageServices) {
        JSR356WebSocketServer.messageServices = messageServices;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        JSR356WebSocketServer.userRepository = userRepository;
    }
}
