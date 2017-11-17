package info.novatec.gateway.usersservice.api

import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController {

    @GetMapping("/users" )
    fun getUsers(@RequestHeader(AUTHORIZATION) authorizationHeader: String): String {
        return "Successfully called users service with authorization header value [$authorizationHeader]."
    }
}