package com.codesqaude.cocomarco.domain.user;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByGithubId(Long id);

    Optional<User> findByLocalId(String localId);

    Optional<User> findByLocalIdAndAuthStatusTrue(String localId);
}
