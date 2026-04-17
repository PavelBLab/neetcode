package onlineassessment;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

interface AccountManager {

    long createAccount(Currency ccy);

    void transferFunds(long from, long to, BigDecimal amount, Currency ccy);

    BigDecimal getBalance(long accountId);

}

public class CodingInterviewTask1_AccountManager implements AccountManager {

    private static final ConcurrentHashMap<Long, Account> db = new ConcurrentHashMap<>();
    private final AtomicLong accountId = new AtomicLong(0);

    @Override
    public long createAccount(final Currency ccy) {
        if (ccy == null) {
            throw new IllegalArgumentException("ccy is not provided");
        }
        var newAccountId = accountId.incrementAndGet();
        var newAccount = new Account(newAccountId, ccy);

        db.put(newAccountId, newAccount);

        return newAccountId;
    }

    @Override
    public void transferFunds(final long from, final long to, final BigDecimal amount, final Currency ccy) {
        var fromAccount = db.get(from);
        var toAccount = db.get(to);

        if (fromAccount == null || toAccount == null) {
            // replace with throw new AccountNotFoundException("accountId: " + accountId + " not found");
            throw new RuntimeException("accountId: " + accountId + " not found");
        }

        if (!fromAccount.getCcy().equals(ccy)) {
            // should be a specific exception;
            throw new RuntimeException("accountId: " + fromAccount + " has another currency");
        }

        if (!toAccount.getCcy().equals(ccy)) {
            // should be a specific exception;
            throw new RuntimeException("accountId: " + toAccount + " has another currency");
        }

        var firstLock = (fromAccount.getAccountId() < toAccount.getAccountId()) ? fromAccount : toAccount;
        var secondLock = (firstLock == fromAccount) ? toAccount : fromAccount;

        synchronized (firstLock) {
            synchronized (secondLock) {
                // Both accounts are locked here
                // Check balance, then transfer — all atomic
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
            }
        }
    }

    @Override
    public BigDecimal getBalance(final long accountId) {
        var account = db.get(accountId);

        if (account == null) {
            // replace with throw new AccountNotFoundException("accountId: " + accountId + " not found");
            throw new RuntimeException("accountId: " + accountId + " not found");
        }
        return account.getAmount();
    }


    public static class Account {

        private final long accountId;
        private volatile BigDecimal amount;
        private final Currency ccy;

        public Account(final long account, final Currency ccy) {
            this.accountId = account;
            this.amount = BigDecimal.ZERO;
            this.ccy = ccy;
        }

        public long getAccountId() {
            return accountId;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public synchronized void deposit(final BigDecimal amount) {
            this.amount  = this.amount.add(amount);
        }

        public synchronized void withdraw(final BigDecimal amount) {
            if (this.amount.compareTo(amount) < 0) {
                // throw new InsufficientFundsException();
                throw new RuntimeException();
            }

            this.amount  = this.amount.subtract(amount);
        }

        public Currency getCcy() {
            return ccy;
        }
    }


}
