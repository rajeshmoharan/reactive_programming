package org.rajesh.users.data;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;


public interface UserRepository extends ReactiveCrudRepository<UserEntity, UUID> {
}
