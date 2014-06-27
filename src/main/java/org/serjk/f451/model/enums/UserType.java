package org.serjk.f451.model.enums;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */

public enum UserType {

    ROLE_USER("Гражданин","ROLE_USER"),
    ROLE_POLICE("Полицейский", "ROLE_POLICE"),
    ROLE_FIREMAN("Пожарный", "ROLE_FIREMAN"),
    ROLE_OFFICIAL("Чиновник", "ROLE_OFFICIAL");

    private final String ruName;
    private final String dbRoleId;


    private UserType(String  ruName, String dbRoleId) {
        this.ruName  = ruName;
        this.dbRoleId = dbRoleId;
    }

    public String   getRuName() {
        return ruName;
    }

    public String getDbRoleId(){ return dbRoleId;  }

}
