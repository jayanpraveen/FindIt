import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType


Contract.make {

    description("should return user info")

    request {
        method("GET")
        url("/user-service/12")
    }

    response {
        status(HttpStatus.OK.value())
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }

        body([ "userId":12,"name":"david","email":"dave@dom.com","institute":"SCHOOL","instituteName":"somescl" ])
    }

}
