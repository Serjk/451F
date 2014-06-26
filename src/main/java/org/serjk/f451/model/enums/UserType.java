package org.serjk.f451.model.enums;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */

public enum UserType {

    ROLE_USER(1,"Гражданин","ROLE_USER"),
    ROLE_POLICE(2, "Полицейский", "ROLE_POLICE"),
    ROLE_FIREMAN(3,"Пожарный", "ROLE_FIREMAN"),
    ROLE_OFFICIAL(4, "Чиновник", "ROLE_OFFICIAL");

    private final int roleId;
    private final String ruName;
    private final String dbRoleId;


    private UserType(int roleId,
                     String  ruName, String dbRoleId) {
        this.roleId = roleId;
        this.ruName  = ruName;
        this.dbRoleId = dbRoleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public String   getRuName() {
        return ruName;
    }

    public String getDbRoleId(){ return dbRoleId;  }

}
