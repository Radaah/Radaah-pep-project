package Service;

import DAO.MessageDAO;
import Model.Message;

import java.util.*;

public class MessageService {
    public MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    // public MessageService(MessageDAO messageDAO){
    //     this.messageDAO = messageDAO;
    // }

    // ## Add new message ##
    public Message addMessage(Message message) {
        Message existMessage = messageDAO.getMessageById(message.getMessage_id());
        if(existMessage != null)
         {
            return null;
         }
          return messageDAO.insertNewMessage(message);
    }

     // ### Retreives all messages ### 
     public List<Message>getAllMessages() {
        return messageDAO.getAllMessages();
    }

    // ## Retrieve new message by ID ##
    public Message getMessageById(int message_id){
        Message existMessage = messageDAO.getMessageById(message_id);
        if(existMessage != null){
            return existMessage;
        }
        return null;
    }

    // ## Retrieve all message created by a particular user ##

    public Message getMessageByPosterId(int poster_id){
        Message existMessage = messageDAO.getMessageByPosterId(poster_id);
        if(existMessage != null){
            return existMessage;
        }
        return null;
    }

    // ## Update a message by a particular ID ##
    public Message updatMessageById( Message message, int message_id){
        Message existMessage = messageDAO.getMessageById(message_id);
        if(existMessage != null){
            return messageDAO.updateMessageById(message);
        }
        return null;
    }
    
    // ## Delete a message by a particular ID ##
     public Message deleteMessageByMessageId(int message_id){
        Message existMessage = messageDAO.getMessageById(message_id);
        if(existMessage != null){
            return messageDAO.deleteMessageById(message_id);
        }
        return null;
     }
   

}

