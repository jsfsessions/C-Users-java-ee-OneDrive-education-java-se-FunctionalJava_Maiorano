package ch3a_lambda_pg45;

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
        double amount = 10;
        Account account = new Account("10");

        //    A)    banker.isBalanceSufficient(account, amount, new Exemptable() { //
        //            @Override
        //            public boolean isExempt(Account account) {
        //                return account.getBalance() - amount > 700;
        //            }
        //        }); 

        /*the evolution from an anonymous class Ref A to a lambda declaration depends on the following factors:
          
        the most basic form of lambda incarnation is 
        (parameter declaration) -> { lambda body }
        
        lambda bodies can be empty too as in () -> { }
        
         
        
         */
//    borrowing from our reference A anonymous class the transformation begins     (parameter declaration) -> { lambda body }   our main interest is the implementation of the isExempt(Account account) { return account.getBalance() - amount > 700 }
        /**
         * Exemptable ex = (Account acc) -> {return acc.getCreditRating() -
         * 700;};
         *
         *
         */
        /**
         * from ref A above what is ex defined as the interface Exemptable
         *
         * @FunctionalInterface public interface Exemptable { boolean
         * isExempt(Account account);
         */
        // Call third version with anonymous class
//        Exemptable ex;
        banker.isBalanceSufficient(account, amount,
                new Exemptable() {
            @Override
            public boolean isExempt(Account account) {
                return account.getCreditRating() > 700;
            }
        });

        // Lambda: form 1
        // Our very first Java lambda!
        Exemptable ex = (Account acc) -> {
            return acc.getCreditRating() > 700;
        };

        banker.isBalanceSufficient(account, amount, ex);

        // Lambda: form 2
        ex = (Account acc) -> acc.getCreditRating() > 700;
        banker.isBalanceSufficient(account, amount, ex);

        // Lambda: form 3
        ex = (acc) -> acc.getCreditRating() > 700;
        banker.isBalanceSufficient(account, amount, ex);

        // Lambda: form 4
        ex = acc -> acc.getCreditRating() > 700;
        banker.isBalanceSufficient(account, amount, ex);

//         Lambda: form 5
        banker.isBalanceSufficient(account, amount, acc -> account.getCreditRating() > 700);

    }
}


/*
the lambda conforms to the Exemptable interface its signature comprising of parameter declaration and return type is compatible with the Exemptabl's is exempt().  Notice that our lambda does not explicitly implement the Exemptable interface in order to declare to which interface it conforms.  Instead the java compiler can infer that the lambda is an Exemptable iimplementation because it conforms to that target interface.  Specificallly the compiler uses this logic to determine suitability: 

1- is Exemptable a functtional interface declaring exactly one method? this is known as the single abstract method which is implemented by lambda.  it is also called SAM (single abstract method)
2-  does the lambda have the same number of parameters  as the Exemptable.isExempt() method?
3-  Are the lambda's parameters compatible with isExempt()?
4-  is the lambda's return type compatiblee with isExempt()?
5-  if exeptions are thrown, are they allowed by isExempt()?

the answer to all this is yes.  Behavior is compatible.  Hence lambda is a substitute.  Fuctional programming is a behavior centric paradigm

the method calling the anonymous class i.e. isBalanceSufficient() does not care if the call originated from an anonymous class or a lambda
removing the excesses we have 
Exemptable ex = (Account acc) -> { return acc.getCreditRating() - 700; };

*/
