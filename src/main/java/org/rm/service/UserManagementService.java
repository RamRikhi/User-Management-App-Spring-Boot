package org.rm.service;

import org.rm.domain.CityMaster;
import org.rm.domain.StateMaster;
import org.rm.domain.UserAccount;

import java.util.Map;

public interface UserManagementService {
    Map<Integer,String> getAllCountries();
    Map<Integer,String> getStatesByCountryId(Integer countryId);
    Map<Integer,String> getCitiesByStateId(Integer stateId);
    boolean saveUser(UserAccount user);
}
