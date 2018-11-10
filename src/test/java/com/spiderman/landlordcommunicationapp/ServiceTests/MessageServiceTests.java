package com.spiderman.landlordcommunicationapp.ServiceTests;

import com.spiderman.landlordcommunicationapp.models.Message;
import com.spiderman.landlordcommunicationapp.repositories.MessageRepository;
import com.spiderman.landlordcommunicationapp.service.MessageServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTests {

    @Mock
    MessageRepository messageRepository;

    @InjectMocks
    MessageServiceImpl messageService;

    List<Message> listAll2MessagesOneAccommodation = new ArrayList<>();

    List<Message> listAll3Messages = new ArrayList<>();

    Message firstMessageOneAccommodation = new Message();

    Message secondMessageOneaccommodation = new Message();

    Message thirdMessageTwoAccommodation = new Message();

    @Test
    public void getMessagesByAccommodationIdAndIsDeletedFalse_ShouldCallFindAllByContextAccommodationIdAndIsDeletedFalse() {
        when(messageRepository.findAllByContextAccommodationIdAndIsDeletedFalse(1))
                .thenReturn(listAll2MessagesOneAccommodation);

        messageService.getMessagesByAccommodationIdAndIsDeletedFalse(1);

        verify(messageRepository, times(1))
                .findAllByContextAccommodationIdAndIsDeletedFalse(1);
    }

    @Test
    public void getAllMessages_ShouldCallFindAll() {
        when(messageRepository.findAll())
                .thenReturn(listAll3Messages);

        messageService.getAllMessages();

        verify(messageRepository, times(1)).findAll();
    }

    @Test
    public void deleteMessage_ShouldReturnMessageWithIsDeletedTrue() {
        firstMessageOneAccommodation.setDeleted(false);
        when(messageRepository.save(firstMessageOneAccommodation))
                .thenReturn(firstMessageOneAccommodation);

        Message result = messageService.deleteMessage(firstMessageOneAccommodation);

        Assert.assertTrue(result.isDeleted());
    }

    @Test
    public void markAllMessagesOlderThan3MonthsAsDeleted_ShouldReturnAllOlderThan90DaysMessagesAsDeleted() {

        LocalDate date100DaysFromNow = LocalDate.now().minusDays(100);

        firstMessageOneAccommodation.setDeleted(false);
        firstMessageOneAccommodation.setTimeSent(toTimestamp(date100DaysFromNow));
        listAll3Messages.add(firstMessageOneAccommodation);

        secondMessageOneaccommodation.setDeleted(false);
        secondMessageOneaccommodation.setTimeSent(toTimestamp(date100DaysFromNow));
        listAll3Messages.add(secondMessageOneaccommodation);

        thirdMessageTwoAccommodation.setDeleted(false);
        thirdMessageTwoAccommodation.setTimeSent(toTimestamp(date100DaysFromNow));
        listAll3Messages.add(thirdMessageTwoAccommodation);

        when(messageRepository.findAll())
                .thenReturn(listAll3Messages);

        messageService.markAllMessagesOlderThan3MonthsAsDeleted();

        Assert.assertTrue(listAll3Messages.get(0).isDeleted());
        Assert.assertTrue(listAll3Messages.get(1).isDeleted());
        Assert.assertTrue(listAll3Messages.get(2).isDeleted());
    }

    private static Timestamp toTimestamp(LocalDate localDate) {
        Date date = Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault()).toInstant());
        return new Timestamp(date.getTime());
    }

    @Test
    public void saveMessage_ShouldCallSave() {

//        when(messageRepository.save(firstMessageOneAccommodation))
//                .thenReturn(firstMessageOneAccommodation);

        messageService.saveMessage(firstMessageOneAccommodation);

        verify(messageRepository, times(1)).save(firstMessageOneAccommodation);
    }

}
