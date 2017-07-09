package com.clps.tmp.core.common.ws;

import java.io.IOException;
import java.util.Map.Entry;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.clps.tmp.common.util.StringUtil;
/**
 * 
 * @author Seven
 * 2016年6月21日
 */
@ServerEndpoint(value = "/ws/{userId}", configurator = SpringConfigurator.class)
@Lazy(false)
public class wsServer {
	private static Logger log = Logger.getLogger(wsServer.class);

	@OnOpen
	public void open(Session session, @PathParam("userId") String userId)
			throws Exception {
		log.info("用户(ID:" + userId + ")连接成功！");
		session.getBasicRemote().sendText("Connect Successful（服务器反馈连接成功）!");
		SessionUtil.put(userId, session);// 保存连接信息
	}

	/**
	 * 接受客户端的消息，并把消息发送给所有连接的会话
	 */
	@OnMessage
	public void getMessage(String message, @PathParam("userId") String userId) throws IOException {
		log.info("收到用户(ID: " + userId + ")消息：" + message);
		if (SessionUtil.hasConnection(userId)) {
			SessionUtil.get(userId).getBasicRemote().sendText("This is the server message（这是来自服务器的消息）");
		} else {
			log.info("用户(ID:"+userId+")不存在或不在线！");
		}
	}

	@OnClose
	public void close(@PathParam("userId") String userId) {
		SessionUtil.remove(userId);
		log.info("用户(ID:" + userId + ")断开连接！");
	}

	@OnError
	public void error(Throwable t, @PathParam("userId") String userId) {
		SessionUtil.remove(userId);
		log.info("用户(ID:" + userId + ")发生错误！");
		log.info(StringUtil.getStackTrace(t));
	}
	
	/**
	 * 广播消息
	 * 2016年6月20日 Seven
	 */
	@SuppressWarnings("unused")
	private void broadcast(String message) {
		try {
			for (Entry<String, Session> entry : SessionUtil.clients.entrySet()) {
				entry.getValue().getBasicRemote().sendText(message);
			}
		} catch (IOException e) {
			log.info("发送广播消息出错！");
			e.printStackTrace();
		}
	}
	/**
	 * 悄悄话
	 * 2016年6月20日 Seven
	 */
	@SuppressWarnings("unused")
	private void qq(String message, String userId){
		try {
			if (SessionUtil.hasConnection(userId)) {
				SessionUtil.get(userId).getBasicRemote().sendText(message);
			} else {
				log.info("用户(ID:"+userId+")不存在或不在线！");
			}
		} catch (IOException e) {
			log.info("发送悄悄话出错！");
			e.printStackTrace();
		}
	}
}