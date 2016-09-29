package ch2c_lambda_evolution_pg38_43;

import book.Account;

@FunctionalInterface
public interface Exemptable {
    boolean isExempt(Account account);
}