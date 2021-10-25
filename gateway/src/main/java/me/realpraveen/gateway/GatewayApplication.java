package me.realpraveen.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@EnableEurekaClient
@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	// @Bean
	// @LoadBalanced
	// public WebClient loadBalancedWebClient(WebClient.Builder builder) {
	// return builder.build();
	// }

	@Bean
	@LoadBalanced
	WebClient.Builder getWebClientBuilder() {
		return WebClient.builder();
	}

}
