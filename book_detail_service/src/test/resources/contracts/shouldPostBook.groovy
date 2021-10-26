import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType


Contract.make {

    description("should post book")

    request {
        method("POST")
  
        url("/book-service")
		
		body([ "bookId":849,"userId":4523,"title":"Random fandom","authour":"Jimmys","isbn":8422,"category":"SCIENCE" ])
		
		headers {
			contentType(MediaType.APPLICATION_JSON_VALUE)
		}
    }

    response {
        status(HttpStatus.CREATED.value())
    }

}

