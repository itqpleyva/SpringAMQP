package mainPackage.Runner;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import mainPackage.Main;
import mainPackage.Receiver.Receiver;


@Component
public class Runner implements CommandLineRunner {

	  private final RabbitTemplate rabbitTemplate;
	  private final Receiver receiver;

	  public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
	    this.receiver = receiver;
	    this.rabbitTemplate = rabbitTemplate;
	  }

	  @Override
	  public void run(String... args) throws Exception {
	    System.out.println("Sending message...");
	    rabbitTemplate.convertAndSend(Main.topicExchangeName, "test", "Hello from RabbitMQ!"); //test is the exchange code setted in  Binding binding method of main class
	    receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
	  }

	}