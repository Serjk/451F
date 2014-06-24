package org.serjk.f451.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.serjk.f451.BaseTest;
import org.serjk.f451.model.User;
import org.serjk.f451.model.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserDAOTest extends BaseTest {

    @Autowired
    private UserDAO userDAO;

    private String DEFAULT_NAME = "Name0";
    private String DEFAULT_LOGIN = "Login0";

    @Test
    public void testCreateUser() throws Exception{
        createUserDefault();
        User user = userDAO.getUserById(1);
        checkUserById(user);
    }

    @Test
    public void testRemoveUser() {
        createUserDefault();
        User user = userDAO.getUserById(1);
        checkUserById(user);
        userDAO.removeUser(1);
        User user0 = userDAO.getUserById(1);
        assertNull(user0);
    }

    @Test
    public void testListUser() {
        createUserDefault();
        createUserDefault();
        List<User> userList = userDAO.listContact();
        assertEquals(2, userList.size());
    }

    @Test
    public void testGetUserByRole() {
        createUserDefault();
        List<User> userListEmpty = userDAO.getUsersByRole(UserType.ROLE_FIREMAN.name());
        assertNull(userListEmpty);
        List<User> userList = userDAO.getUsersByRole(UserType.ROLE_USER.name());
        assertEquals(1, userList.size());
    }

    @Test
    public void testGetUSerByLogin() {
        createUserDefault();
        User user = userDAO.getUserByLogin(DEFAULT_LOGIN);
        checkUserById(user);
    }

    private void createUserDefault() {
        User user = new User();
        user.setFirstName(DEFAULT_NAME);
        user.setLogin(DEFAULT_LOGIN);
        user.setRole(UserType.ROLE_USER.name());
        userDAO.addUser(user);
    }

    private void checkUserById(User user) {
        assertEquals(user.getFirstName(), DEFAULT_NAME);
        assertEquals(user.getLogin(), DEFAULT_LOGIN);
    }
}
