package org.rajesh.users.presentation;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/create")
//    @ResponseStatus(HttpStatus.CREATED) //another way to set the status code
    public Mono<ResponseEntity<UserRest>> createUser(@RequestBody @Valid Mono<CreateUserRequest> createUserRequest) {
        return createUserRequest
                .map(request -> new UserRest(UUID.randomUUID(),request.getFirstName(),request.getLastName(),request.getEmail()))
                .map(userRest -> ResponseEntity.status(HttpStatus.CREATED)
                        .location(URI.create("/users/" + userRest.getId()))
                        .body(userRest)
                );
    }

    @GetMapping("/{userId}")
    public Mono<UserRest> getUser(@PathVariable UUID userId) {
        return  Mono.just(new UserRest(userId,"Rajesh","Moharana","rajesh@gmail.com"));
    }

    @GetMapping
    public Flux<UserRest> getAllUsers(@RequestParam(name = "offset", defaultValue = "0") int offset,
                                      @RequestParam(name = "limit", defaultValue = "10") int limit
                                      ){
        return Flux.just(
                new UserRest(UUID.randomUUID(),"Rajesh","Moharana","rajesh@gmail.com"),
                new UserRest(UUID.randomUUID(),"Satya","Moharana","satya@gmail.com"),
                new UserRest(UUID.randomUUID(),"Ravi","Moharana","ravi@gmail.com"),
                new UserRest(UUID.randomUUID(),"Deepak","Moharana","Deepak@gmail.com")
        );
    }

}
