package com.example.cisco.service;

import com.example.cisco.controller.UserController;
import com.example.cisco.exception.NoDataFoundException;
import com.example.cisco.exception.NoPhoneFoundException;
import com.example.cisco.exception.UserNotFoundException;
import com.example.cisco.model.Phone;
import com.example.cisco.model.User;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

@Service
public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    /**
    *  users list for having users data indexing with userId
    * phones list for having all the phones indexed with phonesId
    * */
    private Map<UUID, User> users = new HashMap<UUID, User>();
    private Map<UUID, Phone> phones = new HashMap<UUID, Phone>();

    /**
     * mapPreferredPhonePhone a map with preferredPhone number as key and list of phones
     * */
    private Map<String, ArrayList<Phone>> mapPreferredPhonePhone = new HashMap<String, ArrayList<Phone>>();

    /**
     * This function maps the phones with user using preferred phone no. as unique key
     * @param phone - the new phone added to use the preferred phone as key and phone as value
     */
    public synchronized void mapUserPhoneWithPreferredPhone(Phone phone){
        UUID id= phone.getUserId();
        phones.put(id,phone);
        String preferredPhone= phone.getPhoneNumber();
        ArrayList<Phone> phoneList= mapPreferredPhonePhone.get(preferredPhone);

        // if list does not exist create it
        if(phoneList == null) {
            phoneList = new ArrayList<Phone>();
            phoneList.add(phone);
            mapPreferredPhonePhone.put(preferredPhone,phoneList);
        } else {
            // add if item is not already in list
            if(!phoneList.contains(preferredPhone)) phoneList.add(phone);
        }
    }

    /**
     * This function delete the specific user's phone
     * @param uuid - the phone-id of the phone to be deleted
     */
    public synchronized void deleteUserPhoneWithPreferredPhone(UUID uuid){
        if(phones.containsKey(uuid)){
            Phone phone= phones.get(uuid);
            String preferredPhone= phone.getPhoneNumber();
            ArrayList<Phone> phoneList = mapPreferredPhonePhone.get(preferredPhone);

            // if list does not exist create it
            if(phoneList == null) {
                throw new NoDataFoundException();
            } else {
                // add if item is not already in list
                if(phoneList.contains(preferredPhone)) phoneList.remove(phone);
            }
        }
        else throw new NoDataFoundException();
    }
    /**
     * This function returns the user after adding successfully form the map.
     * @para User to add the
     * @return user
     */
    public User addUser(User user){

        UUID id= user.getUserId();
        users.put(id,user);
        return users.get(id);
    }
    /**
     * This function deletes the user if exist.
     */
    public void deleteUser(UUID uuid){
        if(users.containsKey(uuid)) users.remove(uuid);
        else throw new UserNotFoundException(uuid);
    }

    /**
     * returns all the users
     */
    public Collection<User> getAllUsers(){
        if(users.isEmpty()) throw new NoDataFoundException();
        return users.values();
    }

    /**
     * @para user id
     * @return list of phones associated with user id
     */
    public List<Phone> getAllUserPhone(UUID uuid){
        if(users.containsKey(uuid)) {
            User user= users.get(uuid);
            String preferredPhone= user.getPreferredPhoneNumber();
            ArrayList<Phone> phoneList= mapPreferredPhonePhone.get(preferredPhone);
            if (phoneList.isEmpty()) throw new NoPhoneFoundException(user);
            return phoneList;
        }
        else throw new UserNotFoundException(uuid);
    }

    /**
     * This function will update the user preferred mobile no.
     * @para User with the updated or the new data
     */
    public void updatePreferredPhone(User user){
        UUID uuid= user.getUserId();
        if(users.containsKey(uuid)){
            users.put(uuid,user);
        }
        else throw new UserNotFoundException(uuid);
    }


    public void addPhoneToUser(Phone phone){
        mapUserPhoneWithPreferredPhone(phone);
    }

    public void deletePhone(UUID uuid){
        deleteUserPhoneWithPreferredPhone(uuid);
    }


}
