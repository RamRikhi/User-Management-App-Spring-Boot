package org.rm.repository;

import org.rm.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Serializable> {
}
