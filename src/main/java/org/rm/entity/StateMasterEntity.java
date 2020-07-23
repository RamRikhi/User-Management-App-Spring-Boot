package org.rm.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "STATES_MASTER")
public class StateMasterEntity {

    @Id
    @Column(name = "STATE_ID")
    private Integer stateId;

    @Column(name = "COUNTRY_ID")
    private Integer countryId;

    @Column(name = "STATE_NAME")
    private String stateName;

}
