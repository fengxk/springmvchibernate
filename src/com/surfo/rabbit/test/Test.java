package com.surfo.rabbit.test;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractApplicationContext ctx =
		        new ClassPathXmlApplicationContext("spring/spring-rabbit.xml");
		    RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
		    template.convertAndSend("Hello, world!");
		    try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    ctx.destroy();
	}

}
