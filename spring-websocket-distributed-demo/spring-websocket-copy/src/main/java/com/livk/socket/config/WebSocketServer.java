package com.livk.socket.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * WebSocketServer
 * </p>
 *
 * @author livk
 * @date 2021/9/22
 */
@Slf4j
@EqualsAndHashCode
@ServerEndpoint("/api/websocket/{sid}")
public class WebSocketServer {

	private static final AtomicInteger onlineCount = new AtomicInteger(0);

	private static final CopyOnWriteArraySet<WebSocketServer> websocketSet = new CopyOnWriteArraySet<>();

	private Session session;

	private boolean repeatLogin = false;

	@Getter
	private String sid = "";

	public void init() {
		log.info("web socket init");
	}

	public void destroy() {
		log.info("web socket destroy");
	}

	@OnOpen
	public void onOpen(Session session, @PathParam("sid") String sid) throws IOException {
		this.session = session;
		for (final var socketServer : websocketSet) {
			if (socketServer.sid.equals(sid)) {
				this.repeatLogin = true;
				sendMessage("客户端" + sid + "已经登录！");
				this.session.close();
				return;
			}
		}
		websocketSet.add(this);
		this.sid = sid;
		addOnlineCount();
		try {
			sendMessage("conn_success");
			log.info("有新客户端开始监听，sid={},当前在线人数：{}", sid, getOnlineCount());
		}
		catch (IOException e) {
			log.error("websocket IO Exception");
		}
	}

	@OnClose
	public void onClose() {
		if (repeatLogin) {
			log.info("客户端{}重复登录,", sid);
			return;
		}
		websocketSet.remove(this);
		subOnlineCount();
		log.info("释放的sid={}的客户端", sid);
		releaseResource();
	}

	@OnMessage
	public void onMessage(String json) {
		log.info("收到来自客户端sid={}，的消息：{}", sid, json);
		sendMessage("客户端" + this.sid + "发布消息：",
				websocketSet.stream().map(WebSocketServer::getSid).collect(Collectors.toSet()));
	}

	@OnError
	public void onError(Session session, Throwable error) {
		log.error("{}客户端发生错误:{}", session.getBasicRemote(), error);
	}

	private void releaseResource() {
		log.info("有一连接关闭！当前在线人数为：{}", getOnlineCount());
	}

	public static void sendMessage(String message, Set<String> toSids) {
		log.info("推送消息到客户端{}，推送内容：{}", toSids, message);
		for (final var item : websocketSet) {
			try {
				if (toSids.size() <= 0) {
					item.sendMessage(message);
				}
				else if (toSids.contains(item.sid)) {
					item.sendMessage(message);
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static List<String> getOnlineSids(List<String> sids) {
		Stream<String> stringStream = websocketSet.stream().map(WebSocketServer::getSid);
		return sids.isEmpty() ? stringStream.toList() : stringStream.filter(sids::contains).toList();
	}

	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}

	public static int getOnlineCount() {
		return onlineCount.get();
	}

	public static void addOnlineCount() {
		onlineCount.getAndIncrement();
	}

	public static void subOnlineCount() {
		onlineCount.getAndDecrement();
	}

	public static CopyOnWriteArraySet<WebSocketServer> getWebsocketSet() {
		return websocketSet;
	}

	@Scheduled(cron = "0/5 * * * * ?")
	public void heartbeat() throws IOException {
		for (var server : websocketSet) {
			server.sendMessage("心跳检查");
		}
	}

}
