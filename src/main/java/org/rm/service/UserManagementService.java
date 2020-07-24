package org.rm.service;

import org.rm.domain.*;

import java.util.Map;

public interface UserManagementService {
    Map<Integer,String> getAllCountries();
    Map<Integer,String> getStatesByCountryId(Integer countryId);
    Map<Integer,String> getCitiesByStateId(Integer stateId);
    boolean saveUser(UserAccount user);
    UserAccount isTempPwdValid(String email,String pazzword);
    boolean updateUserAccount(UserAccount userAccount);
    String findByEmail(String email);
    String isUserExist(LoginUser loginUser);
    String passwordRecover(String email);
}
