package onlineassessment;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


interface AccountManager2 {

    long createAccount(Currency ccy);

    void transferFunds(long from, long to, BigDecimal amount, Currency ccy);

    BigDecimal getBalance(long accountId);
}

public class CodingInterviewTask2_AccountManager implements AccountManager2 {

    private final ConcurrentHashMap<Long, Account> accountDb = new ConcurrentHashMap<>();
    private final AtomicLong accountIdGenerator = new AtomicLong(0);

    @Override
    public long createAccount(Currency ccy) {
        if (ccy == null) {
            throw new IllegalArgumentException("ccy is not provided");
        }

        var accountId = accountIdGenerator.incrementAndGet();

        accountDb.computeIfAbsent(accountId, a -> new Account(accountId, ccy));

        return accountId;
    }

    @Override
    public void transferFunds(long from, long to, BigDecimal amount, Currency ccy) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount should be positive");
        }

        if (ccy == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }

        var fromAccount = accountDb.get(from);
        var toAccount = accountDb.get(to);

        if (fromAccount == null || toAccount == null) {
            // replace with throw new AccountNotFoundException("accountId: " + accountId + " not found");
            throw new RuntimeException("account not found");
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
    public BigDecimal getBalance(long accountId) {
        var account = accountDb.get(accountId);

        if (account == null) {
            // Should be more custom exception
            throw new RuntimeException("Account not found");
        }

        return account.getAmount();
    }

    private class Account {

        private final long accountId;
        private BigDecimal amount;
        private final Currency ccy;

        public Account(long accountId, Currency ccy) {
            this.accountId = accountId;
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
            this.amount = this.amount.add(amount);
        }

        public synchronized void withdraw(final BigDecimal amount) {
            var currentBalance = this.amount;

            if (currentBalance.compareTo(amount) < 0) {
                // Should be more custom exception
                throw new RuntimeException("Not enough funds");
            }

            this.amount = this.amount.subtract(amount);
        }

        public Currency getCcy() {
            return ccy;
        }
    }
}


