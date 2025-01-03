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
                Message message = new Message(rs.getInt( "message_id"), rs.getInt( "posted_by"),
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
             String sql = "SELECT * FROM message WHERE message_id = ?";
             PreparedStatement preparedStatement = connection.prepareStatement(sql);

             preparedStatement.setInt(1,message_id);
             ResultSet rs = preparedStatement.executeQuery();

             while(rs.next()){
                Message message = new Message(rs.getInt( "message_id"), rs.getInt( "posted_by"),
                rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                return message;
             }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    
}

    // ####### Get by  message poster_id/ account_id #######
    public List <Message> getMessageByPosterId( int poster_id ) {
    Connection connection = ConnectionUtil.getConnection();
    List<Message> messages = new ArrayList<>();
    try{
         String sql = "SELECT * FROM message WHERE posted_by = ?";
         PreparedStatement preparedStatement = connection.prepareStatement(sql);

         preparedStatement.setInt(1,poster_id);
         ResultSet rs = preparedStatement.executeQuery();

         while(rs.next()){
            Message msg = new Message(rs.getInt( "message_id"), rs.getInt( "posted_by"),
            rs.getString("message_text"), rs.getLong("time_posted_epoch"));
             messages.add(msg);
         }

    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return messages;

}

    // ####### Post a new Message#######
    public Message createMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "INSERT INTO message(posted_by, message_text, time_posted_epoch) VALUES( ?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                int vx = (int) rs.getLong(1);
                return new Message(vx, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    // Update an existing Message 
    public void updateMessageById(Message message, int id) {
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, message.getMessage_text());
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        

    }

    // ####### Delete Message by ID####### 
    public Message deleteMessageById(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try{
            Message message = getMessageById(message_id);
            String sql = "DELETE FROM message WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message_id);

            preparedStatement.executeUpdate();
            return message;

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}


