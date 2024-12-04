package Service;

import DAO.AccountDAO;
import Model.Account;

import java.util.*;

public class AccountService { 
    public AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    // // ### Retreives all accounts ###
    public List<Account>getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    // ### Login to accounts ###
    public Account loginAccountbyId(Account account){
        Account existingusername = accountDAO.getAccountByCredentials(account);
        if(existingusername != null){
            return existingusername;
        }
        return null;
    }

    // ### Create new accounts ###
    public Account createAccount(Account account){
        if (account.getUsername().isBlank() || account.getPassword().length() < 4){
            return null;
        }

        List<Account> acc = getAllAccounts();
        for(Account a:acc) {
            if(a.getAccount_id() == account.getAccount_id()){
                return null;
            }
        }
        return accountDAO.createAccount(account);
    }

    

}
