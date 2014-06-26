package org.serjk.f451.util;

import org.serjk.f451.model.enums.Transition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kreker on 26.06.14.
 */
public class TransitionUtil {

    public static List<Transition> getOutgoingTransitionsID(int stepId, String role){
        List <Transition>  transitionList  =  new ArrayList<Transition>();
        for (Transition transition : Transition.values()){
            if(transition.getStepIn()==stepId && transition.getPermission().equals(role))
                transitionList.add(transition);
        }
        if (transitionList.isEmpty() )return  null;
        else  return  transitionList;
    }

}
