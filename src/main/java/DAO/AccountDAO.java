package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class AccountDAO {

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

    public Account getAccountById(int id) {
        Connection connection = ConnectionUtil.getConnection();

        try{

        }catch(SQLException e){
            System.out.println()
        }
   


    }



    }
    
