package org.serjk.f451.util;

import org.serjk.f451.model.enums.UserType;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kreker on 26.06.14.
 */
public class UserTypeUtil {

    public static UserType getUserTypeByDbRoleId(String DbRoleId){
        for (UserType userType : UserType.values() ){
            if(userType.getDbRoleId().equals(DbRoleId))
                return userType;
        }
        return  null;
    }

    public static List<UserType> getUserTypeList(){
        List <UserType>  userTypeList= Arrays.asList(UserType.values());
        return  userTypeList;
    }

}
