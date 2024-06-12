// Syahir Amri bin Mohd Azha, 22007728

import java.util.ArrayList;

public class Accounts{
    private ArrayList<Account> accounts;

    public Accounts() {
        this.accounts = new ArrayList<>();
    }

    public Accounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public void setAccounts(ArrayList<Account> accounts){
        this.accounts = accounts;
    }

    public ArrayList<Account> getAccounts(){
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public void printAccount() {
        for (int i = 0; i < accounts.size(); i++){
            Account account = accounts.get(i);
            System.out.println(account.getAccountNumber());
        }
    }

    public Account findAccount(String accountNumber) {
        for (int i = 0; i < accounts.size(); i++){
            Account account = accounts.get(i);
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}
