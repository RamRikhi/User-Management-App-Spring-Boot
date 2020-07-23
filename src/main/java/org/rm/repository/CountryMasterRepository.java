package org.rm.repository;

import org.rm.entity.CountryMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface CountryMasterRepository extends JpaRepository<CountryMasterEntity, Serializable> {
}
