package org.serjk.f451.service.impl;

import org.serjk.f451.model.Step;
import org.serjk.f451.model.Transition;
import org.serjk.f451.service.WorkFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.serjk.f451.dao.WorkFlowDAO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kreker
 * Date: 10.06.14
 * Time: 22:04
 * To change this template use File | Settings | File Templates.
 */

@Service
public class WorkFlowServiceImpl implements WorkFlowService {

    @Autowired
    private WorkFlowDAO workFlowDAO;

    @Override
    public List<Transition> incomingTransitionsID(long stepId,String role){
        return workFlowDAO.incomingTransitionsID(stepId,role);
    }

    @Override
    public List<Transition> outgoingTransitionsID(long stepId,String role){
        return workFlowDAO.outgoingTransitionsID(stepId,role);
    }

    @Transactional
    public void addTransition(Transition transition){
        workFlowDAO.addTransition(transition);
    }

    @Override
    public List <Transition> listTransition(){
    return  workFlowDAO.listTransition();
    }

    @Override
    public void addStep(Step step) {
        workFlowDAO.addStep(step);

    }

    @Override
    public Step getStep(long stepId) {
        return  workFlowDAO.getStep(stepId);
    }

    @Override
    public List<Step> listStep(){
        return workFlowDAO.listStep();
    }
}
