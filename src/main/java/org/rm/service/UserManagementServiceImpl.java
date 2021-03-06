package org.rm.service;

import org.rm.contants.PazzwordLength;
import org.rm.contants.UserAccountStatus;
import org.rm.domain.ForgotPazzword;
import org.rm.domain.LoginUser;
import org.rm.domain.UserAccount;
import org.rm.entity.CityMasterEntity;
import org.rm.entity.CountryMasterEntity;
import org.rm.entity.StateMasterEntity;
import org.rm.entity.UserAccountEntity;
import org.rm.repository.CityMasterRepository;
import org.rm.repository.CountryMasterRepository;
import org.rm.repository.StateMasterRepository;
import org.rm.repository.UserAccountRepository;
import org.rm.util.EmailUtils;
import org.rm.util.MessageUtils;
import org.rm.util.PazzwordGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserManagementServiceImpl implements UserManagementService{

    //injecting country repository into service
    @Autowired
    private CountryMasterRepository countriesRepo;

    //injecting states repository into service
    @Autowired
    private StateMasterRepository statesRepo;

    //injecting cities repository into service
    @Autowired
    private CityMasterRepository citiesRepo;

    //injecting user repository into service
    @Autowired
    private UserAccountRepository userRepo;

    //Injecting EmailUtils into service
    @Autowired
    private EmailUtils emailUtils;

    //Injecting MessageUtils into Service
    @Autowired
    private MessageUtils message;

    /**
     * getAllCountries() will interact with repository interface and retrieve all the countries from the database
     * @return
     */
    @Override
    public Map<Integer, String> getAllCountries() {
        Map<Integer,String> countriesMap = new LinkedHashMap<>();
        List<CountryMasterEntity> countryList = countriesRepo.findAll();
        for (CountryMasterEntity countryEntity:countryList) {
            countriesMap.put(countryEntity.getCountryId(),countryEntity.getCountryName());
        }
        return countriesMap;
    }

    /**
     * getStatesByCountryId() will interact with stateRespository and retrieve all the states based on country id
     * @param countryId
     * @return
     */
    @Override
    public Map<Integer, String> getStatesByCountryId(Integer countryId) {
        Map<Integer,String> statesMap = new LinkedHashMap<>();
        List<StateMasterEntity> statesList = statesRepo.findAllByCountryId(countryId);
        for (StateMasterEntity statesEntity:statesList) {
            statesMap.put(statesEntity.getStateId(),statesEntity.getStateName());
        }
        return statesMap;
    }

    /**
     * getCitiesByStateId() will interact with cityRepository and will retrieve all the cities based on
     * state Id
     * @param stateId
     * @return
     */
    @Override
    public Map<Integer, String> getCitiesByStateId(Integer stateId) {
        Map<Integer,String> citiesMap = new LinkedHashMap<>();
        List<CityMasterEntity> citiesList = citiesRepo.findAllByStateId(stateId);
        for (CityMasterEntity citiesEntity : citiesList){
            citiesMap.put(citiesEntity.getCityId(),citiesEntity.getCityName());
        }
        return citiesMap;
    }

    @Override
    public boolean saveUser(UserAccount user) {
        user.setUserPwd(PazzwordGenerator.generatePazzword(PazzwordLength.TEMP_PWD_LENGTH_SIX));
        user.setAccountStatus(String.valueOf(UserAccountStatus.LOCKED));

        UserAccountEntity userEntity = new UserAccountEntity();

        BeanUtils.copyProperties(user,userEntity);
        UserAccountEntity save = userRepo.save(userEntity);
        if (save.getUserId() != null){
            return emailUtils.sendUserAccUnlockEmail(user);
        }
        return false;
    }

    @Override
    public UserAccount isTempPwdValid(String email, String pazzword) {
        UserAccountEntity userAccEntity = userRepo.findByUserEmailAndUserPwd(email, pazzword);
        UserAccount userAccount = null;
        if(userAccEntity != null){
            userAccount = new UserAccount();
            BeanUtils.copyProperties(userAccEntity,userAccount);
        }
        return userAccount;
    }

    @Override
    public boolean updateUserAccount(UserAccount userAccount) {
        UserAccountEntity userEntity = new UserAccountEntity();

        BeanUtils.copyProperties(userAccount,userEntity);

        UserAccountEntity savedEntity = userRepo.save(userEntity);
        return savedEntity != null;
    }

    @Override
    public String findByEmail(String email) {
        UserAccountEntity uniqueEmailCheck = userRepo.findByUserEmail(email);
        if(uniqueEmailCheck != null){
            return "Duplicate";
        }
        return "Unique";
    }

    @Override
    public String isUserExist(LoginUser loginUser) {
        UserAccountEntity userAccEntity = userRepo.findByUserEmailAndUserPwd(loginUser.getUserEmail(), loginUser.getPazzword());
        if (userAccEntity != null){
            if(String.valueOf(UserAccountStatus.UNLOCKED).equals(userAccEntity.getAccountStatus())){
                return "VALID";
            }else {
                return "LOCKED";
            }
        }
        return "INVALID";
    }

    @Override
    public String passwordRecover(String email) {
        boolean isSent = false;
        String userMobile = null;
        UserAccountEntity userEntity = userRepo.findByUserEmail(email);
        if(userEntity != null){
            if (userEntity.getAccountStatus().equals("UNLOCKED")){
                userMobile = String.valueOf(userEntity.getUserMobile());
                isSent = message.sendSMS(userMobile, userEntity.getUserPwd());
                if(isSent){
                    return userMobile;
                }else {
                    return "Not sent";
                }
            }else{
                return "LOCKED";
            }
        }
        return "Unique";
    }

}
