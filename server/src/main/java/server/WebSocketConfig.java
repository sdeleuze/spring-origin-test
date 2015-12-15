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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author Sebastien Deleuze
 */
//@Configuration
//@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(myHandler(), "/myHandler");
		//registry.addHandler(myHandler(), "/myHandler").setAllowedOrigins("*");
		//registry.addHandler(myHandler(), "/myHandler").setAllowedOrigins("http://localhost:7070", "http://localhost:8080", "http://10.0.2.2:7070", "http://10.0.2.2:8080", "http://testdomain:8080");
		//registry.addHandler(myHandler(), "/myHandler").setAllowedOrigins("http://toto:8080");
	}

	@Bean
	public MyWebsocketHandler myHandler() {
		return new MyWebsocketHandler();
	}
}
