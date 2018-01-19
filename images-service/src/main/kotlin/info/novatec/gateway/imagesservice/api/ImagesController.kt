package info.novatec.gateway.commentsservice.api

import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class ImagesController {

    @GetMapping("/images" )
    fun getComments(@RequestHeader(AUTHORIZATION) authorizationHeader: String): String {
        return "Successfully called IMAGES service with authorization header value [$authorizationHeader]."
    }
}