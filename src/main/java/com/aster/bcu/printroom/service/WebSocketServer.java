package com.aster.bcu.printroom.service;

import com.aster.bcu.printroom.entity.PrBills;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/myWs")
public class WebSocketServer {

    private static Map<String,Session> taskMap=new HashMap();
    private static Map<String,LinkedHashMap<String, PrBills>> printerBillMap =new HashMap();

    private static int onlineCount = 0;
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    private Session session;

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static Boolean doOpenDoor(String printerPk,String bill){
        return true;
    }

    public static Boolean doDrop(String printerPk,String bill){
        try {
            printerBillMap.get(printerPk).remove(bill);
            sendMessage("newTaskList",printerPk);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static int getTaskCont(String printerPk){
        try {
            return printerBillMap.get(printerPk).size();
        }catch (Exception e){
            return 0;
        }

    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
    public static void sendMessage(String message,String printerId) throws IOException {
        Session session = taskMap.get(printerId);
        session.getBasicRemote().sendText(message);
    }
    public static void sendInfo(String message) throws IOException {
        System.out.println(message);
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }


    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            System.out.println("websocket IO异常");
        }
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        String[] strings=message.split(" ");
        switch (strings[0]){
            case "login":
                taskMap.put(strings[1],session);
                break;
            case "info":

                break;
            case "finish":
                break;
        }
        //群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }




}
