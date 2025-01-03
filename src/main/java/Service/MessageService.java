package Service;

import DAO.MessageDAO;
import Model.Message;

import java.util.*;

public class MessageService {
    public MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

      // ### Retreives all messages ### 
      public List<Message>getAllMessages() {
        return messageDAO.getAllMessages();
    }

         // ## Add new message ##
         public Message createMessage(Message message){

            if (message.getMessage_text().isBlank() || message.getMessage_text().length() > 255){
                return null;
            }
    
            List<Message> msgs = getAllMessages();
            for(Message msg:msgs) {
                if(msg.getMessage_id() == message.getMessage_id() || msg.getPosted_by() != message.getPosted_by()){
                    return null;
                }
            }
            return messageDAO.createMessage(message);
        }
        

   

    // ## Retrieve new message by ID ##
    public Message getMessageById( int message_id){
      return messageDAO.getMessageById(message_id);
    }

    // ## Retrieve all message created by a particular user ##

    public List <Message> getMessageByPosterId( int poster_id){
        return messageDAO.getMessageByPosterId( poster_id);
    }

    // ## Update a message by a particular ID ##
    public Message updatMessageById( Message message, int message_id){
        Message existMessage = messageDAO.getMessageById(message_id);
        if(existMessage != null && !message.getMessage_text().isBlank() && message.getMessage_text().length() < 255){
             messageDAO.updateMessageById(message, message_id);
             return  messageDAO.getMessageById(message_id);
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

