package com.example.whatsappDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@SpringBootApplication
public class WhatsappDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhatsappDemoApplication.class, args);
	}

}

class IncoimgMessage {
    private String sender;
    private String text;

    public IncoimgMessage() {}

    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

}

class Response{
    private String reply;

    public Response(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return reply;
    }
    public void setReply(String reply) {
        this.reply = reply;
    }
}

@RestController
@RequestMapping("/webhook")
class WebhookController {
    private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("WhatsApp Chatbot Backend is running live!");
    }

    @PostMapping
    public ResponseEntity<Response> handleMessage(@RequestBody IncoimgMessage message) {
        logger.info("IncoimgMessage received: Sender:[{}], Text:[{}]", message.getSender(), message.getText());

        String reply;
        String text;
        if (message.getText() !=null){
            text = message.getText().trim().toLowerCase();
        } else{
            text = "";
        }

        switch (text){
            case "hi":
                text ="Hello";
                break;
            case "bye":
                text ="Goodbye";
                break;
            default:
                text = "I only know how to reply to hi and bye. But you can still chat with me and i will give the same response";
                break;
        }
        Response response = new Response(text);
        return ResponseEntity.ok(response);
    }
}
