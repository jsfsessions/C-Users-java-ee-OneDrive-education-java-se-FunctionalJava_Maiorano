package ch2a_before_lambdas_pg35_36;

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

    public boolean isBalanceSufficient(Account account, double amount) {
        logAccess(account);
        boolean isBalanceSufficient = account.getBalance() - amount > 0;

        if (!isBalanceSufficient) {
            // it would be nice to let the caller vary this condition; this if statment leads to hard coding every time you need to change the clients procedure in qualifying how customers get approval for withdrawing money when account is below sufficient funds.
            if (account.getCreditRating() > 650) {
                isBalanceSufficient = true;
                doOverdraft(account, amount);
            }
        }
        return isBalanceSufficient;
    }

    private void runTheBank(Account account, double amount) { 
        isBalanceSufficient(account, amount);                
    }

    public static void main(String[] args) {
        Banker banker = new Banker();
        double anAmount = 10;
        Account anAccount = new Account("10");

        banker.runTheBank(anAccount, 800);
        
        //in the next session we will create an anonymous class to give the user a way to vary the condition and give more control
    }

}
