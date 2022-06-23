package org.gradientworks.passwordmanager.repo;

import org.gradientworks.passwordmanager.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
