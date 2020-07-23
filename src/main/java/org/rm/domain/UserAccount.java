package org.rm.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserAccount {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String userEmail;
    private Long userMobile;
    private Date dob;
    private String userPwd;
    private Character gender;
    private String accountStatus;
    private Integer countryId;
    private Integer stateId;
    private Integer cityId;
    private Date createdDate;
    private Date updatedDate;

}
