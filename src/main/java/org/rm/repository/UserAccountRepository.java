package org.rm.repository;

import org.rm.domain.UserAccount;
import org.rm.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Serializable> {

    //@Query(value="select userId from user_accounts ",nativeQuery)
    UserAccountEntity findByUserEmail(String userEmail);

    UserAccountEntity findByUserEmailAndUserPwd(String email, String tempPwd);
}
