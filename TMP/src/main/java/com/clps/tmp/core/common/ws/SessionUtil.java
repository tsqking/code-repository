package com.clps.tmp.core.common.ws;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.Session;
/**
 * 存储WebSocket客户端Session信息
 * @author Seven
 * 2016年6月20日
 */
public class SessionUtil {
	//存放客户端的连接，使用线程安全的HashMap
	public static Map<String, Session> clients = new ConcurrentHashMap<>();
	/**
	 * 保存连接
	 * 2016年6月20日 Seven
	 */
	public static void put(String userId,Session session) {
		clients.put(userId, session);
	}
	/**
	 * 获取连接
	 * 2016年6月20日 Seven
	 */
	public static Session get(String userId) {
		return clients.get(userId);
	}
	/**
	 * 移除连接
	 * 2016年6月20日 Seven
	 */
	public static void remove(String userId) {
		clients.remove(userId);
	}
	/**
	 * 判断是否有连接
	 */
	public static boolean hasConnection(String userId) {
		return clients.containsKey(userId);
	}
}