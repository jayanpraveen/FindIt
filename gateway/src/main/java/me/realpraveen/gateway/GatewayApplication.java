package me.realpraveen.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	@LoadBalanced
	RestTemplate loadBalancedRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	@LoadBalanced
	WebClient.Builder loadBalancedWebClient() {
		return WebClient.builder();
	}

	// @Bean
	// RouteLocator cRouteLocator(RouteLocatorBuilder builder) {
	// return builder.routes().route("path_route", r ->
	// r.method(HttpMethod.GET).and().path("/api/**")
	// .filters(f -> f.stripPrefix(1)).uri("lb://Book-Detail-Service")).build();
	// }

	// todo: add circuit breaker bean below

}
