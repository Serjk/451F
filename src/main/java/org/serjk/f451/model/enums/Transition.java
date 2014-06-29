package org.serjk.f451.model.enums;

/**
 * Created with IntelliJ IDEA.
 * User: kreker
 * Date: 23.06.14
 * Time: 23:18
 * To change this template use File | Settings | File Templates.
 */
public enum  Transition {

    TRANSITION1("Взять в работу",    1, 2, "ROLE_POLICE"),
    TRANSITION4("Передать пожарным", 2, 3, "ROLE_POLICE"),
    TRANSITION5("Сжечь книги",       3, 4, "ROLE_FIREMAN"),
    TRANSITION6("Закрыть", 4, 5, "ROLE_FIREMAN");

    private final String name;
    private final int stepIn;
    private final int stepOut;
    private final String permission;

    private Transition(String transitionName,
                       int stepIn, int stepOut, String permission) {
        this.name = transitionName;
        this.stepIn = stepIn;
        this.stepOut = stepOut;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public int  getStepIn() {
        return stepIn;
    }

    public int getStepOut(){
        return stepOut;
    }

    public String getPermission() {
        return permission;
    }
}
