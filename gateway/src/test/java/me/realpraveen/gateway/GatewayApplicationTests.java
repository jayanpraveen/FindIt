package me.realpraveen.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
public class GatewayApplicationTests {

  @Autowired
  private WebTestClient webClient;

  @LocalServerPort
  private String port;

  @Test
  void contextLoads() throws Exception {

    String url = "http://localhost:" + port + "/api/gateway/3";

    // replace with wiremock
    webClient.get().uri(url)
                  .exchange()
                  .expectStatus().isOk()
                  .expectBody()
                  .jsonPath("$.name")
                  .isEqualTo("tommy");

  }

}
