package com.sns.config;

import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.buffered.AmazonSQSBufferedAsyncClient;


@Configuration
public class Config {
	
	public String awsKey="AKIAYN2APJ6MFYDPQSIU";
	public String secret="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";

	@Bean
	@Primary
	public AmazonSNSClient amazonSns() {
		
		return (AmazonSNSClient) AmazonSNSClientBuilder
				.standard()
				.withRegion(Regions.AP_SOUTH_1)
				.withCredentials(
		new AWSStaticCredentialsProvider(
				new BasicAWSCredentials(awsKey,secret))
			  ).build();
				
	}
	
	@Bean
	public QueueMessagingTemplate queueMessagingTemplate() {
		
		return new QueueMessagingTemplate(buildAmazonSQSAsync());
	}


	private AmazonSQSAsync buildAmazonSQSAsync() {
		// TODO Auto-generated method stub
		return AmazonSQSAsyncClientBuilder
				.standard()
				.withRegion(Regions.AP_SOUTH_1)
				.withCredentials(
		new AWSStaticCredentialsProvider(
				new BasicAWSCredentials(awsKey,secret))
			  ).build();
	}
}
