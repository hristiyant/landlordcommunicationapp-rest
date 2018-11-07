package com.spiderman.landlordcommunicationapp;

import com.spiderman.landlordcommunicationapp.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TaskScheduler {

    private final MessageService messageService;

    @Autowired
    public TaskScheduler(MessageService messageService) {
        this.messageService = messageService;
    }


    private static final Logger logger = LoggerFactory.getLogger(TaskScheduler.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(cron = "0 * * * * ?") // "0 0 0 * * ?" for every day at 00:00
    public void scheduleTaskWithCronExpression() {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        messageService.markAllMessagesOlderThan3MonthsAsDeleted();
    }
}
