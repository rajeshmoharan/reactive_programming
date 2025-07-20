package org.rajesh.users.service;

import lombok.RequiredArgsConstructor;
import org.rajesh.users.data.UserEntity;
import org.rajesh.users.data.UserRepository;
import org.rajesh.users.presentation.CreateUserRequest;
import org.rajesh.users.presentation.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Mono<UserRest> createUser(Mono<CreateUserRequest> createUserRequest) {
        return createUserRequest
                .mapNotNull(this::convertToEntity)
                .flatMap(userRepository::save)
                .map(this::convertToRest);
    }

    @Override
    public Mono<UserRest> getUserById(UUID id) {
        return userRepository.findById(id)
                .mapNotNull(userEntity -> convertToRest(userEntity));
    }

    public UserEntity convertToEntity(CreateUserRequest createUserRequest) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(createUserRequest,userEntity);
        return userEntity;
    }

    public UserRest convertToRest(UserEntity userEntity) {
        UserRest userRest = new UserRest();
        BeanUtils.copyProperties(userEntity,userRest);
        return userRest;
    }
}
