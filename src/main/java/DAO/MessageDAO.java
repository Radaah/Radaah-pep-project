package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    // #######Get all message #######
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();

        try{
            String sql = "SELECT * FROM message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
 
            
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt( "message_id"), rs.getInt( " posted_by"),
                rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                messages.add(message);
        }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }

         // #######Get by  message_Id #######
    public Message getMessageById(int message_id) {
        Connection connection = ConnectionUtil.getConnection();

        try{
             String sql = "SELECT * FROM account WHERE message_id = ?";
             PreparedStatement preparedStatement = connection.prepareStatement(sql);

             preparedStatement.setInt(1,message_id);
             ResultSet rs = preparedStatement.executeQuery();

             while(rs.next()){
                Message message = new Message(rs.getInt( "message_id"), rs.getInt( " posted_by"),
                rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                return message;
             }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    
}

    // ####### Get by  message poster_id/ account_id #######
    public Message getMessageByPosterId(int poster_id) {
    Connection connection = ConnectionUtil.getConnection();

    try{
         String sql = "SELECT * FROM account WHERE posted_by = ?";
         PreparedStatement preparedStatement = connection.prepareStatement(sql);

         preparedStatement.setInt(1,poster_id);
         ResultSet rs = preparedStatement.executeQuery();

         while(rs.next()){
            Message message = new Message(rs.getInt( "message_id"), rs.getInt( " posted_by"),
            rs.getString("message_text"), rs.getLong("time_posted_epoch"));
            return message;
         }

    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;

}

    // ####### Post a new Message#######
    public Message insertNewMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "INSERT INTO message(posted_by, message_text, time_posted_epoch) VALUES( ?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate();
            return message;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    // Update an existing Message 
    public Message updateMessageById(Message message) {
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "UPDATE message SET message_text = ?, time_posted_epoch = ? WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, message.getMessage_text());
            preparedStatement.setLong(2, message.getTime_posted_epoch());
            preparedStatement.setInt(3, message.getMessage_id());

            preparedStatement.executeUpdate();
            return message;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
        

    }

    // ####### Delete Message by ID####### 
    public Message deleteMessageById(int message_id){
        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql = "DELETE FROM message WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message_id);

            preparedStatement.executeUpdate();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}

