package org.serjk.f451.model.enums;

/**
 * Created with IntelliJ IDEA.
 * User: kreker
 * Date: 23.06.14
 * Time: 23:18
 * To change this template use File | Settings | File Templates.
 */
public enum Step {

    STEP6(1,"Новый донос"),
    STEP1(2,"Обработка полицией"),
    STEP3(3,"Обработка пожарными"),
    STEP4(4,"Сожжение"),
    STEP5(5,"Отчет о выезде"),
    STEP7(6,"Отклонен");

    private final int id;
    private final String stepName;


    private Step(int id, String stepName) {
        this.id = id;
        this.stepName = stepName;
    }

    public int  getId() {
        return id;
    }

    public String getStepName() {
        return stepName;
    }

}
