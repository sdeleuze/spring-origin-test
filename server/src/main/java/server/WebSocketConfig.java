package server;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableScheduling
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {


	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//registry.addEndpoint("/stomp").withSockJS().setClientLibraryUrl("http://cdn.sockjs.org/sockjs-0.3.js");
		registry.addEndpoint("/stomp").setAllowedOrigins().withSockJS().setClientLibraryUrl("http://cdn.sockjs.org/sockjs-0.3.js");
		//registry.addEndpoint("/stomp").setAllowedOrigins("http://10.0.2.2:7070", "http://10.0.2.2:8080", "http://testdomain:8080").withSockJS().setClientLibraryUrl("http://cdn.sockjs.org/sockjs-0.3.js");
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/queue/", "/topic/");
		registry.setApplicationDestinationPrefixes("/app");
	}

}
