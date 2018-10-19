package com.spiderman.landlordcommunicationapp.controlers;

import com.spiderman.landlordcommunicationapp.models.Accommodation;
import com.spiderman.landlordcommunicationapp.models.User;
import com.spiderman.landlordcommunicationapp.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Accommodations")
@PreAuthorize("isAnonymous()")
public class AccommodationController {

    private final AccommodationService accommodationService;

    @Autowired
    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public List<Accommodation> getAllAccommodations() {
        return accommodationService.getAllAccommodations();
    }

    @GetMapping("/byUser")
    public List<Accommodation> getAllAccommodationsOfThisUser(@RequestBody User user) {
        return accommodationService.getAllAccommodationsOfThisUser(user);
    }

    @PostMapping("/new")
    public Accommodation save(@RequestBody Accommodation accommodation) {
        return accommodationService.save(accommodation);
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
