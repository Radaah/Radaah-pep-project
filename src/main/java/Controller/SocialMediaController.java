package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    MessageService messageService;
    AccountService accountService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */


    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postAccountHandler);
        app.post("/login", this:: postLoginHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageById);
        // app.delete("/messages/{message_id}", this::deleteMessageById);
        // app.patch("/messages/{message_id}", this::exampleHandler);
        // app.get("/accounts/{account_id}/messages}", this::exampleHandler);
        // app.start(8080);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    // private void exampleHandler(Context context) {
    //     context.json("sample text");
    // }

    // Register account//
    private void postAccountHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.createAccount(account);
        if(addedAccount != null){
            ctx.json(mapper.writeValueAsString(addedAccount));
            ctx.status(200);
        }else{
            ctx.status(400);
        }
    }

    //Login into account//
    private void postLoginHandler(Context ctx) throws JsonProcessingException{
      ObjectMapper mapper = new ObjectMapper();
      Account account = mapper.readValue(ctx.body(), Account.class);
      Account addedAccount = accountService.loginAccountbyId(account);
        if(addedAccount != null){
            ctx.json(mapper.writeValueAsString(addedAccount));
            ctx.status(200);
        }else{
            ctx.status(401);
        }
    }

     //Post new message//
    private void postMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message addedMessage = messageService.createMessage(message);
        if(addedMessage != null){
            ctx.json(mapper.writeValueAsString(addedMessage));
            ctx.status(200);
        }else{
            ctx.status(400);
        }
    }

    // get all message//
    private void getAllMessagesHandler(Context ctx) {
        List<Message> messages = messageService.getAllMessages();
        ctx.status(200);
        ctx.json(messages); 
    }

    // get message by id//
    private void getMessageById(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        
        Message existingMessage = messageService.getMessageById(message, message_id);
        
        if (existingMessage != null) {
            ctx.json(existingMessage);
        } else {
            ctx.json("");
        }
        
        ctx.status(200);
    }

    // private void deleteMessageById(Context ctx) throws JsonProcessingException{
    //     ObjectMapper mapper = new ObjectMapper();
    //     Message message = mapper.readValue(ctx.body(), Message.class);
    
    //     Message existingMessage = messageService.getMessageById(message.getMessage_id());
    
    //     if (existingMessage != null) {
    //         ctx.json(messageService.deleteMessageByMessageId(message.getMessage_id()));
    //         // ctx.status(200);
    //     } else {
    //         ctx.json("");
    //     }
    
    //     ctx.status(200);
    // }

    

}