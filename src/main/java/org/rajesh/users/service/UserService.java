package org.rajesh.users.service;

import org.rajesh.users.presentation.CreateUserRequest;
import org.rajesh.users.presentation.UserRest;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {
    Mono<UserRest> createUser(Mono<CreateUserRequest> createUserRequest);

    Mono<UserRest> getUserById(UUID id);
}
