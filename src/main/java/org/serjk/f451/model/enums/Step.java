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
    STEP2(3,"Проверка псом"),
    STEP3(4,"Обработка пожарными"),
    STEP4(5,"Сожжение"),
    STEP5(6,"Отчет о выезде"),
    STEP7(7,"Отклонен");

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
