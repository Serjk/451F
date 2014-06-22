package org.serjk.f451.dao;

import org.serjk.f451.model.Step;
import org.serjk.f451.model.Transition;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kreker
 * Date: 10.06.14
 * Time: 20:50
 * To change this template use File | Settings | File Templates.
 */
public interface WorkFlowDAO {

    public void addTransition(Transition transition);

    public List <Transition> listTransition();

    public void addStep(Step step);

    public Step getStep(long stepId);

    public List<Step> listStep();

    public List<Transition> incomingTransitionsID(long stepId, String role);

    public List<Transition> outgoingTransitionsID(long StepID,String role);

    public  void  runSqlQery(String sqlQuery);
}
