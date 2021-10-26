
import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType


Contract.make {

    description("should return book by Id")

    request {
        method("GET")
        url("/book-service/12")
    }

    response {
        status(HttpStatus.OK.value())
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }

        body(["bookId":12,"userId":45,"title":"Random fandom","authour":"Jimmy","isbn":8422,"category":"SCIENCE"])
    }

}
