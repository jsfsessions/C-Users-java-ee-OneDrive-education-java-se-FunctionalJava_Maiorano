package ch3c_lambda_functional_interface_pg48;

import book.Account;

@FunctionalInterface
public interface Exemptable {
    boolean isExempt(Account account);
}