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

import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sebastien Deleuze
 */
@RestController
public class MyController {

	//@CrossOrigin
	@RequestMapping("/hello")
	public String getHelloWorld() {
		return "hello World!";
	}

	//@CrossOrigin
	@RequestMapping(method = RequestMethod.PUT, value = "/hello")
	public String putHelloWorld(@RequestBody String body) {
		return "hello World! " + body;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/hello2")
	public String putHelloWorld2(@RequestBody String body) {
		return "hello World! " + body;
	}
	
	private interface MyJacksonView1 {}
	private interface MyJacksonView2 {}

	@SuppressWarnings("unused")
	private static class JacksonViewBean {

		@JsonView(MyJacksonView1.class)
		private String withView1;

		@JsonView(MyJacksonView2.class)
		private String withView2;

		private String withoutView;

		public String getWithView1() {
			return withView1;
		}

		public void setWithView1(String withView1) {
			this.withView1 = withView1;
		}

		public String getWithView2() {
			return withView2;
		}

		public void setWithView2(String withView2) {
			this.withView2 = withView2;
		}

		public String getWithoutView() {
			return withoutView;
		}

		public void setWithoutView(String withoutView) {
			this.withoutView = withoutView;
		}
	}
	
	@JsonView(MyJacksonView2.class)
	@SubscribeMapping("/foo")
	public ListenableFuture<JacksonViewBean> foo() {
		JacksonViewBean bean = new JacksonViewBean();
		bean.setWithView1("foo");
		bean.setWithView2("foo");
		bean.setWithoutView("foo");
		SettableListenableFuture<JacksonViewBean> future = new SettableListenableFuture<JacksonViewBean>();
		future.set(bean);
		return future;
	}
	
	@JsonView(MyJacksonView2.class)
	@MessageMapping("/bar")
	public CompletableFuture<JacksonViewBean> bar() {
		JacksonViewBean bean = new JacksonViewBean();
		bean.setWithView1("foo");
		bean.setWithView2("foo");
		bean.setWithoutView("foo");
		CompletableFuture<JacksonViewBean> future = CompletableFuture.completedFuture(bean);
		return future;
	}

}
