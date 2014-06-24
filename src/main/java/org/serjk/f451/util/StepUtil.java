package org.serjk.f451.util;

import org.serjk.f451.model.enums.Step;

import java.util.Arrays;
import java.util.List;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
public class StepUtil {

    public static Step getStepById(int id){
        for (Step step : Step.values() ){
            if(step.getId()==id)
                return step;
        }
        return  null;
    }

    public static List<Step> getStepList(){
        List <Step>  stepList= Arrays.asList(Step.values());
        return  stepList;
    }
}
