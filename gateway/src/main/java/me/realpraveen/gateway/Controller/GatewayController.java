package me.realpraveen.gateway.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.realpraveen.gateway.Applications.BookServiceClient;

@RestController
@RequestMapping("/api")
public class GatewayController {

	private BookServiceClient bookClient;

	@Autowired
	public GatewayController(BookServiceClient bookClient) {
		this.bookClient = bookClient;
	}

}
