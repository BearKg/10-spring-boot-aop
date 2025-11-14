package com.vanhuynh.aopdemo;

import com.vanhuynh.aopdemo.dao.AccountDAO;
import com.vanhuynh.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

    @Bean public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {
        return runner -> {
            demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
        };
    }

    private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {
        // call the business method
        Account theAccount = new Account();
        theAccountDAO.addAccount(theAccount, true);
        theMembershipDAO.addSillyMember();

        // call the accountDAO getter/setter
        theAccountDAO.setName("foo");
        theAccountDAO.setServiceCode("bar");

        theAccountDAO.getName();
        theAccountDAO.getServiceCode();

        // call the membership business method
        theAccountDAO.doWork();
        theMembershipDAO.goToSleep();
    }
}
