package org.rm.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "CITIES_MASTER")
public class CityMasterEntity {

    @Id
    @Column(name = "CITY_ID")
    private Integer cityId;

    @Column(name = "STATE_ID")
    private Integer stateId;

    @Column(name = "CITY_NAME")
    private String cityName;
}
