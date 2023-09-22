package com.sns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;

@RestController
public class OTPController {

	@Autowired
	private AmazonSNSClient aSnsClient;
	
	@Autowired
	private QueueMessagingTemplate qMessageTemp;
	
	private String ARN = "arn:aws:sns:ap-south-1:579418935192:my-sns-topic";
	
	@Value("${cloud.aws.end-point.uri}")
	private String endpoint;
	
	
	
	
	@GetMapping("/subscribe/{email}")
	public String subscribe(@PathVariable("email") String email) {
		
		SubscribeRequest sRequest = new SubscribeRequest(ARN, "email",email);
		
		aSnsClient.subscribe(sRequest);
		return "Please Check your email";

     }
	
	
	
	
	@GetMapping("/publish/{msg}")
	public String publish(@PathVariable("msg")String msg) {
		
		PublishRequest publishRequest = new PublishRequest(ARN, msg, "Greetins From Venkatesh");
		aSnsClient.publish(publishRequest);
		
		return "Message Published";
	}
	
	
	@GetMapping("/put/{mes}")
	public String putMesToSQS(@PathVariable("mes") String mes) {
		
		qMessageTemp.send(endpoint, MessageBuilder.withPayload(mes).build());
		return  "Message Posted Successfully";
	}
	
	@SqsListener("sqs-queue")
	public void sqsListener(String mes) {
		
		System.out.println("This is the mesasge from AWS-SQS "+mes);
	}
	
}
