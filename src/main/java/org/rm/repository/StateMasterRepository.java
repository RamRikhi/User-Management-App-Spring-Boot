package org.rm.repository;

import org.rm.entity.StateMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface StateMasterRepository extends JpaRepository<StateMasterEntity, Serializable> {
    List<StateMasterEntity> findAllByCountryId(Integer countryId);
}
