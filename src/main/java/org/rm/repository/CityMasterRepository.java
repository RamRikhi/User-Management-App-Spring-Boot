package org.rm.repository;

import org.rm.entity.CityMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface CityMasterRepository extends JpaRepository<CityMasterEntity, Serializable> {
    List<CityMasterEntity> findAllByStateId(Integer stateId);
}
