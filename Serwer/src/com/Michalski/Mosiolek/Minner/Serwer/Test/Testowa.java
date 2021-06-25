package com.Michalski.Mosiolek.Minner.Serwer.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.Michalski.Mosiolek.Minner.Serwer.Database.AdminLogin;
import com.Michalski.Mosiolek.Minner.Serwer.Database.DataBaseManager;

class Testowa {

    @Test
    void test() {

        AdminLogin al = new AdminLogin("admin", "tigerbonzo123");

        if(!DataBaseManager.AdminLoginManager.isPasswordGood(al.getNick(), al.getPassword())) {
            fail("Haslo jest niepoprawne");
        }
        
        if(!DataBaseManager.AdminLoginManager.updatePassword(al.getNick(), "tigerbonzo123")) {
            fail("Nie udalo sie zaktualizowac hasla");
        }
   



    }

}
