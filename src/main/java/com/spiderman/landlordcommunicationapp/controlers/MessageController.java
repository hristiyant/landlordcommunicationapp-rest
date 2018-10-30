package com.spiderman.landlordcommunicationapp.controlers;

import com.spiderman.landlordcommunicationapp.models.Accommodation;
import com.spiderman.landlordcommunicationapp.models.Message;
import com.spiderman.landlordcommunicationapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Messages")
@PreAuthorize("isAnonymous()")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    //todo for testing only - delete upon production
    @GetMapping("/getAllMessages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @PostMapping
    public Message saveMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

    @DeleteMapping
    public Message deleteMessage(@RequestBody Message messageToDelete) {
        return messageService.deleteMessage(messageToDelete);
    }

    @DeleteMapping("/old")
    public void deleteAllMessagesThatAreOlderThan3Months() {
        messageService.markAllMessagesOlderThan3MonthsAsDeleted();
    }
}
