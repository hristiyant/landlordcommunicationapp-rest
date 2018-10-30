package com.spiderman.landlordcommunicationapp.controlers;

import com.spiderman.landlordcommunicationapp.models.Accommodation;
import com.spiderman.landlordcommunicationapp.models.Message;
import com.spiderman.landlordcommunicationapp.models.User;
import com.spiderman.landlordcommunicationapp.service.AccommodationService;
import com.spiderman.landlordcommunicationapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Accommodations")
@PreAuthorize("isAnonymous()")
public class AccommodationController {

    private final AccommodationService accommodationService;

    private final MessageService messageService;

    @Autowired
    public AccommodationController(AccommodationService accommodationService, MessageService messageService) {
        this.accommodationService = accommodationService;
        this.messageService = messageService;
    }

    @GetMapping
    public List<Accommodation> getAllAccommodations() {
        return accommodationService.getAllAccommodations();
    }

    @GetMapping("/{accommodationId}")
    public Accommodation getAccommodationByItsId(@PathVariable int accommodationId) {
        return accommodationService.getAccommodationByItsId(accommodationId);
    }

    @PostMapping
    public Accommodation save(@RequestBody Accommodation accommodation) {
        return accommodationService.save(accommodation);
    }

    @GetMapping("/{accommodationId}/Messages}")
    public List<Message> getMessagesByAccommodationIdAndIsDeletedFalse(@PathVariable int accommodationId) {
        return messageService.getMessagesByAccommodationIdAndIsDeletedFalse(accommodationId);
    }

    @PutMapping("/newTenant")
    public Accommodation addTenantToThisAccommodation(@RequestBody Accommodation accommodation, @RequestBody User user) {
        return accommodationService.addTenantToThisAccommodation(accommodation, user);
    }

    @PutMapping("freeThisAccommodation")
    public Accommodation removeTenantFromThisAccommodation(@RequestBody Accommodation accommodation) {
        return accommodationService.removeTenantFromThisAccommodation(accommodation);
    }
}
