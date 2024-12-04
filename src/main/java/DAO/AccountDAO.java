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
    public Account getAccountByCredentials(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try{
             String sql = "SELECT * FROM account WHERE username= ? AND password = ?";
             PreparedStatement preparedStatement = connection.prepareStatement(sql);

             preparedStatement.setString(1,account.getUsername());
             preparedStatement.setString(1,account.getPassword());
             ResultSet rs = preparedStatement.executeQuery();

             while(rs.next()){
                Account account1 = new Account(rs.getInt( "account_id"), rs.getString( "username"), 
                rs.getString("password"));
                return account1;
             }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

 // ####### Create new account ########
    public Account createAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "INSERT INTO account(username, password) VALUES(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                int vx = (int) rs.getLong(1);
                return new Account(vx, account.getUsername(), account.getPassword());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    }
    

