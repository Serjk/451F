package org.serjk.f451.service;

import org.serjk.f451.model.Step;
import org.serjk.f451.model.Transition;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kreker
 * Date: 10.06.14
 * Time: 0:07
 * To change this template use File | Settings | File Templates.
 */
public interface WorkFlowService {

    public List<Transition> incomingTransitionsID(long StepID,String role);

    public List<Transition> outgoingTransitionsID(long StepID,String role);

    public void addTransition(Transition transition);

    public List <Transition> listTransition();

    public void addStep(Step step);

    public Step getStep(long stepId);

    public List<Step> listStep();

}
