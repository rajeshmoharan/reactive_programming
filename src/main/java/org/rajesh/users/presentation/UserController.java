package org.rajesh.users.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rajesh.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
//    @ResponseStatus(HttpStatus.CREATED) //another way to set the status code
    public Mono<ResponseEntity<UserRest>> createUser(@RequestBody @Valid Mono<CreateUserRequest> createUserRequest) {
        return userService.createUser(createUserRequest)
                .map(userRest -> ResponseEntity.status(HttpStatus.CREATED)
                        .location(URI.create("/users/" + userRest.getId()))
                        .body(userRest));
    }

    @GetMapping("/{userId}")
    public Mono<ResponseEntity<UserRest>> getUser(@PathVariable UUID userId) {
        return userService.getUserById(userId)
                .map(userRest ->
                        ResponseEntity.status(HttpStatus.OK)
                                .body(userRest))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @GetMapping
    public Flux<UserRest> getAllUsers(@RequestParam(name = "offset", defaultValue = "0") int offset,
                                      @RequestParam(name = "limit", defaultValue = "10") int limit
                                      ){
        return userService.findAll(offset,limit);
    }

}
