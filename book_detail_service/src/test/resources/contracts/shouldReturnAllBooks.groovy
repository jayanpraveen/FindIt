
import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType


Contract.make {

    description("should return all books")

    request {
        method("GET")
        url("/book-service")
    }

    response {
        status(HttpStatus.OK.value())
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }

        body([ [ "bookId":10,"userId":675,"title":"awesome title","authour":"cow","isbn":2345,"category":"UNLISTED" ] ])
    }

}


