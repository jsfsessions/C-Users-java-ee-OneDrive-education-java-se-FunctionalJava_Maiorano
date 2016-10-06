package ch3a_lambda_pg45;

import book.Account;

@FunctionalInterface
public interface Exemptable {
    boolean isExempt(Account account);
}