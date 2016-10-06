package ch3c_lambda_functional_interface_pg48;

//assuming the client's account balance is non sufficient for a withdrawal we want to make an exemption and allow him to withdraw; onAccountExempted if true let him borrow; here we use void instead of boolean as a return 
import book.Account;

@FunctionalInterface
public interface AccountExemptionHandler {
    public void onAccountExempted(Account account);    
}
