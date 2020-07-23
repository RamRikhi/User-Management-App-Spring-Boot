package org.rm.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "USER_ACCOUNTS")
public class UserAccountEntity {

    @Id
    @Column(name = "USER_ID")
    @SequenceGenerator(
            name = "user_id_seq_gen",
            sequenceName = " USER_ACC_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "user_id_seq_gen",
            strategy = GenerationType.SEQUENCE
    )
    private Integer userId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "USER_MOBILE")
    private Long userMobile;

    @Column(name = "DOB")
    private Date dob;

    @Column(name = "USER_PWD")
    private String userPwd;

    @Column(name = "GENDER")
    private Character gender;

    @Column(name = "ACC_STATUS")
    private String accountStatus;

    @Column(name = "COUNTRY_ID")
    private Integer countryId;

    @Column(name = "STATE_ID")
    private Integer stateId;

    @Column(name = "CITY_ID")
    private Integer cityId;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_DATE",updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "UPDATED_DATE",insertable = false)
    private Date updatedDate;
}
