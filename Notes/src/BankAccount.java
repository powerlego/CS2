/**
 * CSCI-142 Computer Science 2 Recitation Exercise
 * 02-Classes
 * BankAccount
 *
 * This is the implementation of a simple bank account the is comprised
 * of an account number, a balance, and an interest rate that is the same
 * for all accounts.
 *
 * The supplied test program, TestBankAccount.java, is used to verify
 * this class is implemented correctly.
 *
 * @author RIT CS
 */
public class BankAccount {
    /** the interest rate */
    public final static double INTEREST_RATE = 2;

    /** the account number */
    private int account;
    /** the current balance of the account */
    private double balance;

    /**
     * Create the new BankAccount with a balance of 0.
     *
     * @param account the account number
     */
    public BankAccount(int account) {
        this(account, 0);
    }

    /**
     * Create the new BankAccount with a starting balance.
     *
     * @param account the account number
     * @param balance the starting balance
     */
    public BankAccount(int account, double balance) {
        this.account = account;
        this.balance = balance;
    }

    /**
     * Get the current balance.
     *
     * @return the balance
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Deposit an amount into the account thus increasing the balance.
     *
     * @param amount the amount to deposit.
     */
    public void deposit(double amount) {
        this.balance += amount;
    }

    /**
     * Withdraw an amount from the account.  If the amount does not exceed the
     * balance, the transaction is assumed to go through and the balance is
     * reduced by the amount and true is returned.  Otherwise if the amount
     * exceeds the balance the transaction is declined, the balance is not
     * adjusted, and false is retured.
     *
     * @param amount the amount to attempt to withdraw from the account
     * @return whether the transaction was successful and the amount was
     * withdrawn, or not.
     */
    public boolean withdraw(double amount) {
        boolean result = false;
        if (amount <= this.balance) {
            this.balance -= amount;
            result = true;
        }
        return result;
    }

    /**
     * Apple the interest rate to the account and increase the current
     * balance by the amount.
     */
    public void applyInterest() {
        this.balance *= INTEREST_RATE;
    }

    /**
     * Return a string representation of the BankAccount object.  The format
     * should be:
     *
     * "BankAccount{account={account}, balance={balance}}}
     *
     * @return the string described above
     */
    @Override
    public String toString() {
        return "BankAccount{" +
                "account=" + this.account +
                ", balance=" + this.balance +
                '}';
    }

    /**
     * Two BankAccount objects are equal if the have the same account
     * number.
     *
     * @param other the other object to compare against
     * @return whether they are equal or not
     */
    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof BankAccount) {
            BankAccount otherAccount = (BankAccount) other;
            result = this.account == otherAccount.account;
        }
        return result;
    }
}
