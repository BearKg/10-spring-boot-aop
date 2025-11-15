package com.vanhuynh.aopdemo;

import com.vanhuynh.aopdemo.service.TrafficFortuneService;
import com.vanhuynh.aopdemo.dao.AccountDAO;
import com.vanhuynh.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

    @Bean public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO,
                                                     MembershipDAO theMembershipDAO,
                                                     TrafficFortuneService theTrafficFortuneService) {
        return runner -> {
            // demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
            // demoTheAfterReturningAdvice(theAccountDAO);
            // demoTheAfterThrowingAdvice(theAccountDAO);
            // demoTheAfterAdvice(theAccountDAO);
            demoTheAroundAdvice(theTrafficFortuneService);
        };
    }

    private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {
        System.out.println("\nMain Program: demoTheAroundAdvice");
        System.out.println("Calling getFortune()");
        String data = theTrafficFortuneService.getFortune();
        System.out.println("\nMy fortune is: " + data);
        System.out.println("Finished");
    }

    private void demoTheAfterAdvice(AccountDAO theAccountDAO) {
        // call method to find the accounts
        List<Account> theAccounts = null;
        try {
            // add a boolean flag to simulate exception
            boolean tripWire = true;
            theAccounts = theAccountDAO.findAccounts(tripWire);
        } catch(Exception ex) {
            System.out.println("\n\nMain Program: ... caught exception" +ex);
        }
        theAccountDAO.findAccounts();

        // display the accounts
        System.out.println("\n\nMain Progress: demoTheAfterReturnAdvice");
        System.out.println("----");

        System.out.println(theAccounts);

        System.out.println("\n");
    }

    private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {
        // call method to find the accounts
        List<Account> theAccounts = null;
        try {
            // add a boolean flag to simulate exception
            boolean tripWire = true;
            theAccounts = theAccountDAO.findAccounts(tripWire);
        } catch(Exception ex) {
            System.out.println("\n\nMain Program: ... caught exception" +ex);
        }
        theAccountDAO.findAccounts();

        // display the accounts
        System.out.println("\n\nMain Progress: demoTheAfterReturnAdvice");
        System.out.println("----");

        System.out.println(theAccounts);

        System.out.println("\n");
    }

    private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {
        // call method to find the accounts
        List<Account> theAccounts = theAccountDAO.findAccounts();

        // display the accounts
        System.out.println("\n\nMain Progress: demoTheAfterReturnAdvice");
        System.out.println("----");

        System.out.println(theAccounts);

        System.out.println("\n");

    }

    private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {
        // call the business method
        Account theAccount = new Account();
        theAccount.setName("Madhu");
        theAccount.setLevel("Platinum");
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
