package Service;

import DAO.AccountDAO;
import Model.Account;

import java.util.*;

public class AccountService { 
    public AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    // ### Retreives all accounts ###
    public List<Account>getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    // ### Create new accounts ###
    public Account createAccount(Account account){

        Account existingAccount = accountDAO.getAccountById(account.getAccount_id());
        if (existingAccount != null){
            return null;
        }
        return accountDAO.createAccount(account);
    }

    

}
