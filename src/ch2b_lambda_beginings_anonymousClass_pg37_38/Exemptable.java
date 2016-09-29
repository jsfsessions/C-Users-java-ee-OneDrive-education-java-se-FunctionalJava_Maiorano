package ch2b_lambda_beginings_anonymousClass_pg37_38;

import book.Account;

@FunctionalInterface
public interface Exemptable {
    boolean isExempt(Account account);
}