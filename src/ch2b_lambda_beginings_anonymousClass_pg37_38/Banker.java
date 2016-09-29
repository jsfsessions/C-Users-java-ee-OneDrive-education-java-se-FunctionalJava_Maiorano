package ch2b_lambda_beginings_anonymousClass_pg37_38;

import book.Account;
import java.util.logging.Logger;

/**
 *
 * @author java ee
 */
public class Banker {

    // assumes the current class is called MyLogger
    private final static Logger LOGGER = Logger.getLogger(Banker.class.getName());

    public void doOverdraft(Account account, double amount) {
        alertOverdrawn(account);
    }

    public void alertOverdrawn(Account account) {
        String msg = "the account is overdrawn, good credit rating; an exception for a withdrawal has been made.  fees may apply";
        LOGGER.severe(msg);
    }

    public void logAccess(Account account) {
        System.out.println("Account: " + account);
    }

    //Exemptable is an interface it was created so that the client can alter the conditions of how customers qualify to be exempt so as to withdraw money; this saves on alot of coding lines.
    public boolean isBalanceSufficient(Account account, double amount, Exemptable ex) {
        logAccess(account);

        boolean isBalanceSufficient = account.getBalance() - amount > 0;
        if (!isBalanceSufficient) {
            if (ex.isExempt(account)) {
                isBalanceSufficient = true;
                alertOverdrawn(account);
            }
        }
        return isBalanceSufficient;
    }

    public static void main(String[] args) {
        Banker banker = new Banker();
        double anAmount = 10;
        Account anAccount = new Account("10");

        banker.isBalanceSufficient(anAccount, anAmount, new Exemptable() { //
            @Override
            public boolean isExempt(Account account) {
                return account.getBalance() - anAmount > 700;
            }
        }); 

/*
        the building of anonymous class which precedes the birth of lambda starts like this 
                1-  create an Exemtable interface
                2- create a new Exemptable object
                3-  Override the isExempt() method.
                4- include the @Override annotation
      

         
        */        

//in the next session we show the birth of lambda from the new Exemptable interfaces that contains only one method still giving the user a way to vary the condition and give more control
        
        
    }
}
