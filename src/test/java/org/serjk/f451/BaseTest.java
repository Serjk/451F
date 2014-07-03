package org.serjk.f451;

import org.hibernate.SessionFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */

@ContextConfiguration(locations="file:src/test/resources/context.xml")
public class BaseTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Before
    public void setUp() throws Exception {
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(sessionFactory.openSession()));
    }

    @After
    public void tearDown() throws Exception {
        SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);
        SessionFactoryUtils.closeSession(sessionHolder.getSession());
    }
}