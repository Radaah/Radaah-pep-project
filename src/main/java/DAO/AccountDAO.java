package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class AccountDAO {

// ####### Retrieve by ALL account ########
    public List<Account> getAllAccounts(){
        Connection connection = ConnectionUtil.getConnection();
        List <Account> accounts = new ArrayList<>();
        try{
            String sql = "SELECT * FROM account";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Account account = new Account(rs.getInt( "account_id"), rs.getString( "username"), 
                rs.getString("password"));
                accounts.add(account);
        }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return accounts;
    }
    
// ####### Retrieve by account-id ########
    public Account getAccountById(int account_id) {
        Connection connection = ConnectionUtil.getConnection();

        try{
             String sql = "SELECT * FROM account WHERE account_id = ?";
             PreparedStatement preparedStatement = connection.prepareStatement(sql);

             preparedStatement.setInt(1,account_id);
             ResultSet rs = preparedStatement.executeQuery();

             while(rs.next()){
                Account account = new Account(rs.getInt( "account_id"), rs.getString( "username"), 
                rs.getString("password"));
                return account;
             }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

 // ####### Create new account ########
    public Account insertAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "INSERT INTO account(username, password) VALUES(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();
            return account;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    }
    

