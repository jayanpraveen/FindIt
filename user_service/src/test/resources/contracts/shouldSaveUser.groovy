import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.MediaType
import org.springframework.http.HttpStatus


Contract.make {

    description("should save user")

    request {
        method("POST")

        url("/user-service")

		headers {
			contentType(MediaType.APPLICATION_JSON_VALUE)
		}

		body([ "userId":12,"name":"david","email":"dave@dom.com", "password":"2bd%osj", "institute":"SCHOOL","instituteName":"somescl" ])
		
    }

    response {
        status(HttpStatus.CREATED.value())
    }

}
