package org.serjk.f451.service;

import org.serjk.f451.service.impl.InitServiceImpl;

/**
 * Created with IntelliJ IDEA.
 * User: kreker
 * Date: 22.06.14
 * Time: 22:52
 * To change this template use File | Settings | File Templates.
 */
public interface InitService {

    public void initStepsTable();
    public void initTransitionsTable();
    public void initUsersTable();

}
