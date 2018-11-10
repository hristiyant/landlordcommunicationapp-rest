package com.spiderman.landlordcommunicationapp.controlers;

import com.spiderman.landlordcommunicationapp.models.Accommodation;
import com.spiderman.landlordcommunicationapp.models.Message;
import com.spiderman.landlordcommunicationapp.models.User;
import com.spiderman.landlordcommunicationapp.service.AccommodationService;
import com.spiderman.landlordcommunicationapp.service.MessageService;
import com.spiderman.landlordcommunicationapp.validation.ValidationException;
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
    public Accommodation getAccommodationByItsId(@PathVariable int accommodationId) throws ValidationException {
        return accommodationService.getAccommodationByItsId(accommodationId);
    }

    @PostMapping
    public Accommodation save(@RequestBody Accommodation accommodation) throws ValidationException{
        return accommodationService.save(accommodation);
    }

    @PutMapping("/{id}")
    public Accommodation editAccommodationById(@PathVariable int id, @RequestBody Accommodation accommodation)
            throws ValidationException{
        return accommodationService.editAccommodationById(id, accommodation);
    }

    @PutMapping("/pay/{id}")
    public Accommodation payRent(@PathVariable int id, @RequestBody Accommodation accommodation)
            throws ValidationException {
        return accommodationService.payRentForAccommodation(id, accommodation);
    }

    @GetMapping("/{accommodationId}/Messages}")
    public List<Message> getMessagesByAccommodationIdAndIsDeletedFalse(@PathVariable int accommodationId) {
        return messageService.getMessagesByAccommodationIdAndIsDeletedFalse(accommodationId);
    }

    @PutMapping("/{accommodationId}/newTenant")
    public Accommodation addTenantToThisAccommodation(@PathVariable int accommodationId, @RequestBody User user)
            throws ValidationException {
        return accommodationService.addTenantToThisAccommodation(accommodationId, user);
    }

    @PutMapping("freeThisAccommodation")
    public Accommodation removeTenantFromThisAccommodation(@RequestBody Accommodation accommodation)
            throws ValidationException {
        return accommodationService.removeTenantFromThisAccommodation(accommodation);
    }
}
