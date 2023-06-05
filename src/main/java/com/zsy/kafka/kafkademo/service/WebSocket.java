package com.zsy.kafka.kafkademo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName WebSocket
 * @Author yqq
 * @create 2023/6/5 11:08
 */
@Component
@ServerEndpoint("/websocket")
@Slf4j
public class WebSocket {

    //实例一个session,这个session是websocket的session
    private Session session;
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        log.info("【websocket消息】有新的连接, 总数:{}", webSocketSet.size());

    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        log.info("【websocket消息】连接断开, 总数:{}", webSocketSet.size());
    }

    //前端向后端发送消息
    @OnMessage
    public void onMessage(String message){
        log.info("【websocket消息】收到客户端发来的消息:{}", message);
    }

    //新增一个方法用于主动向客户端发送消息
    public void sendMessage(String message){
        for (WebSocket webSocket: webSocketSet) {
            log.info("【websocket消息】广播消息, message={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
