package org.serjk.f451.service.impl;

import org.serjk.f451.dao.ReportDAO;
import org.serjk.f451.dao.UserDAO;
import org.serjk.f451.model.User;
import org.serjk.f451.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: kreker
 * Date: 22.06.14
 * Time: 22:47
 * To change this template use File | Settings | File Templates.
 */

@Service
public class InitServiceImpl implements InitService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    ReportDAO reportDAO;

    @Override
    public void initUsersTable(){
        String [] firstName  = {"Admin",   "User",
                                "Fireman", "Policeman",
                                "Иван",    "Галактион",
                                "Гарри",   "Гастон",
                                "Гаяс",    "Гевор",
                                "Геворг",  "Геннадий",
                                "Генрих",  "Георгий",
                                "Геральд", "Герасим",
                                "Герман",  "Татьяна",
                                "Глеб" ,   "Гордей" };

        String [] lastName  = { "Admin",     "User",
                                "Fireman",   "Policeman",
                                "Иардан",    "Иафетов",
                                "Иашвили",   "Иашвиль",
                                "Ибердусов", "Ивахненко",
                                "Ибранский", "Ибянский",
                                "Ивацевич",  "Ивашев",
                                "Ивачев",    "Ивачковский",
                                "Ивашенков", "Ивашенцевич",
                                "Ивашенцов", "Ивашенцович"};

        String [] login  =   {  "admin",       "user",
                                "fireman",     "policeman",
                                "policeman1",  "policeman2",
                                "policeman3",  "policeman4",
                                "fireman1",    "fireman2",
                                "user1",       "user2",
                                "user3",       "user4",
                                "user5",       "user9",
                                "user7",       "user8",
                                "user9",       "user10"};

        String [] password = {"21232f297a57a5a743894a0e4a801fc3", "ee11cbb19052e40b07aac0ca060c23ee",
                              "4fe814abb1adb58a7788269de4408c8f", "441216d0829401b6ae1b500f3d8e0b57",
                              "202cb962ac59075b964b07152d234b70", "202cb962ac59075b964b07152d234b70",
                              "202cb962ac59075b964b07152d234b70", "202cb962ac59075b964b07152d234b70",
                              "202cb962ac59075b964b07152d234b70", "202cb962ac59075b964b07152d234b70",
                              "202cb962ac59075b964b07152d234b70", "202cb962ac59075b964b07152d234b70",
                              "202cb962ac59075b964b07152d234b70", "202cb962ac59075b964b07152d234b70",
                              "202cb962ac59075b964b07152d234b70", "202cb962ac59075b964b07152d234b70",
                              "202cb962ac59075b964b07152d234b70", "202cb962ac59075b964b07152d234b70",
                              "202cb962ac59075b964b07152d234b70", "202cb962ac59075b964b07152d234b70"};

        String [] addres = {  "Новоизмайловский 16 к3", "Новоизмайловский 16 к2",
                              "Новоизмайловский 16 к4", "Новоизмайловский 16 к1",
                              "Новоизмайловский 16 к3", "Новоизмайловский 16 к2",
                              "Новоизмайловский 16 к4", "Новоизмайловский 16 к1",
                              "Кронверский 49",         "Кронверский 49",
                              "Новоизмайловский 16 к3", "Новоизмайловский 16 к2",
                              "Новоизмайловский 16 к4", "Новоизмайловский 16 к1",
                              "Новоизмайловский 16 к3", "Новоизмайловский 16 к2",
                              "Новоизмайловский 16 к4", "Новоизмайловский 16 к1",
                               "Кронверский 49",        "Кронверский 49"};

        String [] role = {  "ROLE_OFFICIAL", "ROLE_USER",
                            "ROLE_FIREMAN",  "ROLE_POLICE",
                            "ROLE_POLICE",   "ROLE_POLICE",
                            "ROLE_POLICE",   "ROLE_POLICE",
                            "ROLE_FIREMAN",  "ROLE_FIREMAN",
                            "ROLE_USER",     "ROLE_USER",
                            "ROLE_USER",     "ROLE_USER",
                            "ROLE_USER",     "ROLE_USER",
                            "ROLE_USER",     "ROLE_USER",
                            "ROLE_USER",     "ROLE_USER",};

        String sqlQuery = "DELETE FROM f_user;";
        reportDAO.runSqlQery(sqlQuery);

        for (int i = 0; i<firstName.length; i++){
            User user =new User();
            user.setFirstName(firstName[i]);
            user.setLastName(lastName[i]);
            user.setLogin(login[i]);
            user.setPassword(password[i]);
            user.setAddress(addres[i]);
            user.setRole(role[i]);
            userDAO.addUser(user);
        }
    }

    @Override
    public void initBankTable(){
        long id[] = {10};
        double buget[] = {100};
        long wageId[] = { 11};

        String sqlQuery = "DELETE FROM f_bank;";
        reportDAO.runSqlQery(sqlQuery);

        for (int i = 0; i<id.length; i++){
            sqlQuery = "INSERT INTO f_bank(id, buget, wageid) " +
                    "VALUES ("+id[i]+", "+buget[i]+ ", "+wageId[i]+");";
            reportDAO.runSqlQery(sqlQuery);
        }
    }

    @Override
    public void initWageTable(){
        long id[] = {11};
        double cash[] = {100};
        String type[] = {"Топливо"};

        String sqlQuery = "DELETE FROM f_wage;";
        reportDAO.runSqlQery(sqlQuery);

        for (int i = 0; i<id.length; i++){
            sqlQuery = "INSERT INTO f_wage(id, cash, type) " +
                   "VALUES ("+id[i]+", "+cash[i]+ ", '"+type[i]+"');";
            reportDAO.runSqlQery(sqlQuery);
        }
    }
}
