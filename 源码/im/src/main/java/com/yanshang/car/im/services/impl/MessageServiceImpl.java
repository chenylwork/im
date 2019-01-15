package com.yanshang.car.im.services.impl;

import com.yanshang.car.im.bean.Message;
import com.yanshang.car.im.bean.Room;
import com.yanshang.car.im.bean.User;
import com.yanshang.car.im.commons.NetMessage;
import com.yanshang.car.im.repositories.MessageRepository;
import com.yanshang.car.im.repositories.RoomRepository;
import com.yanshang.car.im.repositories.RoomUserRepository;
import com.yanshang.car.im.repositories.UserRepository;
import com.yanshang.car.im.services.MessageServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
 * @ClassName MessageServiceImpl
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/9- 16:02
 * @Version 1.0
 **/
@Service("MessageServices")
public class MessageServiceImpl implements MessageServices {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomUserRepository roomUserRepository;

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Override
    @Transactional
    public NetMessage addMessage(Message message) {
        String to = message.getTo();
        String from = message.getFrom();
        // 发送者判断
        User fromUser = userRepository.getByUsername(from);
        // 接受者判断
        if (fromUser == null) return NetMessage.failNetMessage("","消息发送者应该为用户账户！！");
        int roomid = 0;
        Room room = null;
        try{
            roomid = Integer.parseInt(to);
            room = roomRepository.getOne(roomid);
            if (room == null || room.getRoomid() == null) {
                return NetMessage.failNetMessage("","房间号错误！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return NetMessage.failNetMessage("","房间号错误！！");
        }
        // 发送者是否所属聊天室判断
        if (roomUserRepository.getByUsernameAndRoomid(from,roomid) == null) {
            return NetMessage.failNetMessage("","您不属于该聊天室！！");
        }
        if (message.getContent() == null || message.getContent().equals("")){
            return NetMessage.failNetMessage("","发送的消息为空的！！");
        }

        try {
            // 获取消息总数
            int count = room.getMessageCount() + 1;
            // 保存消息
            String nowTime = new SimpleDateFormat("yyyy-HH-dd hh-mm-ss").format(new Date());
            message.setSendTime(nowTime);
            message.setVersion(count);
            Message save = messageRepository.save(message);

            // 修改房间消息总数
            int i = roomRepository.changeCount(roomid);
            if (i > 0) {
                logger.info("房间号：" + roomid + "的消息添加了一条");
            } else {
                return NetMessage.errorNetMessage();
            }
            return NetMessage.successNetMessage("",save);
        } catch (Exception e) {
            e.printStackTrace();
            return NetMessage.errorNetMessage();
        }
    }

    @Override
    public NetMessage getUnreadMessage(String username) {
        List<Message> unreadMessage = messageRepository.getUnreadMessage(username);
        if (unreadMessage != null && !unreadMessage.isEmpty()) {
            return NetMessage.successNetMessage("",unreadMessage);
        }
        return NetMessage.failNetMessage("","没有未读的消息");
    }
    @Override
    public NetMessage getHistoryMessage(int roomid, String start, String end) {
        List<Message> historyMessage = messageRepository.getHistoryMessage(roomid, start, end);
        if (historyMessage != null && !historyMessage.isEmpty()) {
            return NetMessage.successNetMessage("",historyMessage);
        } else {
            return NetMessage.failNetMessage("","没有您需要的聊天信息！！");
        }
    }
}
