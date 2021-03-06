package info.novatec.gateway.commentsservice.api

import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class CommentsController {

    @GetMapping("/comments" )
    fun getComments(@RequestHeader(AUTHORIZATION) authorizationHeader: String): String {
        return "Successfully called COMMENTS service with authorization header value [$authorizationHeader]."
    }
}