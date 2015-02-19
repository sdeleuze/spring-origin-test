/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package server;

import java.io.IOException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author Sebastien Deleuze
 */
public class MyWebsocketHandler extends TextWebSocketHandler {

	private WebSocketSession session;
	private Long count = 0L;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		this.session = session;
	}

	@Scheduled(fixedDelay=500)
	public void sendTradeNotifications() throws IOException {
		if(this.session != null && this.session.isOpen()) {
			String message = "Result " + this.count++;
			session.sendMessage(new TextMessage(message));
		}
	}
}
