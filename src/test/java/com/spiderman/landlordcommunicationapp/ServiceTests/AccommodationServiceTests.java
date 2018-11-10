package com.spiderman.landlordcommunicationapp.ServiceTests;

import com.spiderman.landlordcommunicationapp.models.Accommodation;
import com.spiderman.landlordcommunicationapp.models.User;
import com.spiderman.landlordcommunicationapp.repositories.AccommodationRepository;
import com.spiderman.landlordcommunicationapp.repositories.UserRepository;
import com.spiderman.landlordcommunicationapp.service.AccommodationServiceImpl;
import com.spiderman.landlordcommunicationapp.validation.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.spiderman.landlordcommunicationapp.ServiceTests.UserServiceTests.setDefaultTestUsers;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccommodationServiceTests {

    @Mock
    UserRepository userRepository;

    @Mock
    AccommodationRepository accommodationRepository;

    @InjectMocks
    AccommodationServiceImpl accommodationService;

    List<User> listOfAll3Users = new ArrayList<>();

    User firstLandlord = new User();

    User firstTenant = new User();

    User secondTenant = new User();

    {
        setDefaultTestUsers(firstLandlord, firstTenant, secondTenant, listOfAll3Users);
    }

    List<Accommodation> listAll2Accommodations = new ArrayList<>();

    Accommodation oneAccommodation = new Accommodation();

    Accommodation twoAccommodation = new Accommodation();

    {
        setDefaultTestAccommodation(oneAccommodation, twoAccommodation, listAll2Accommodations,
                firstLandlord, firstTenant, secondTenant);
    }

    static void setDefaultTestAccommodation(Accommodation oneAccommodation,
                                            Accommodation twoAccommodation,
                                            List<Accommodation> listAll2Accommodations,
                                            User firstLandlord,
                                            User firstTenant,
                                            User secondTenant) {

        oneAccommodation.setId(1);
        oneAccommodation.setLandlord(firstLandlord);
        oneAccommodation.setTenant(firstTenant);
        listAll2Accommodations.add(oneAccommodation);

        twoAccommodation.setId(2);
        twoAccommodation.setLandlord(firstLandlord);
        twoAccommodation.setTenant(secondTenant);
        listAll2Accommodations.add(twoAccommodation);
    }

    @Test
    public void getAllAccommodations_ShouldReturnListOfAll2Accommodations() {
        when(accommodationRepository.findAll())
                .thenReturn(listAll2Accommodations);

        List<Accommodation> result = accommodationService.getAllAccommodations();

        Assert.assertEquals(listAll2Accommodations.size(), result.size());
        Assert.assertEquals(listAll2Accommodations.get(0),
                result.get(0));
    }

    @Test
    public void getAllAccommodationsByItsUserId_ShouldReturnListWithOneAccommodation_WhenCalledWithId2()
            throws ValidationException {
        when(userRepository.findById(2))
                .thenReturn(firstTenant);
        when(accommodationRepository.findAll())
                .thenReturn(listAll2Accommodations);

        List<Accommodation> result = accommodationService.getAllAccommodationsByItsUserId(2);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(oneAccommodation, result.get(0));
    }

    @Test
    public void getAllAccommodationsByItsUserId_ShouldReturnListWithBothAccommodations_WhenCalledWithId1()
            throws ValidationException {
        when(userRepository.findById(1))
                .thenReturn(firstLandlord);
        when(accommodationRepository.findAll())
                .thenReturn(listAll2Accommodations);

        List<Accommodation> result = accommodationService.getAllAccommodationsByItsUserId(1);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals(oneAccommodation, result.get(0));
        Assert.assertEquals(twoAccommodation, result.get(1));
    }

    @Test(expected = ValidationException.class)
    public void getAllAccommodationsByItsUserId_ShouldThrowException_WhenCalledWithInvalidId()
        throws ValidationException {
        when(userRepository.findById(138))
                .thenReturn(null);

        accommodationService.getAllAccommodationsByItsUserId(138);

    }

    @Test
    public void save_ShouldReturnSameAccommodationIfSavedSuccessfully()
        throws ValidationException {
        Accommodation test = twoAccommodation;

        when(accommodationRepository.findById(test.getId()))
                .thenReturn(null);

        when(accommodationRepository.save(test))
                .thenReturn(test);

        Accommodation result = accommodationService.save(test);

        verify(accommodationRepository, times(1)).save(test);
        Assert.assertEquals(twoAccommodation, result);
    }

//    @Test(expected = ValidationException.class)
//    public void save_ShouldThrowValidationException_WhenThereIsAccommodationWithTheSameId()
//        throws ValidationException {
//        when(accommodationRepository.findById(1))
//                .thenReturn(oneAccommodation);
//        when(accommodationRepository.save(oneAccommodation))
//                .thenReturn(oneAccommodation);
//
//        accommodationService.save(oneAccommodation);
//    }

    @Test
    public void addTenantToThisAccommodation_ShouldReturnChangedAccommodationAccordingToTheUser()
        throws ValidationException {
        User newTenant = secondTenant;
        Accommodation test = oneAccommodation;
        when(userRepository.findById(newTenant.getId()))
                .thenReturn(newTenant);
        when(accommodationRepository.findById(test.getId()))
                .thenReturn(test);

        test = accommodationService.addTenantToThisAccommodation(test.getId(), newTenant);

        Assert.assertEquals(oneAccommodation.getTenant(), test.getTenant());
    }

    @Test(expected = ValidationException.class)
    public void addTenantToThisAccommodation_ShouldThrowValidationException_WhenCalledWithNullNewTenant()
        throws ValidationException {
        when(userRepository.findById(firstTenant.getId()))
                .thenReturn(null);
        when(accommodationRepository.findById(1))
                .thenReturn(oneAccommodation);

        accommodationService.addTenantToThisAccommodation(oneAccommodation.getId(), firstTenant);
    }

//    @Test(expected = ValidationException.class)
//    public void addTenantToThisAccommodation_ShouldThrowValidationException_WhenCalledWithNullAccommodation()
//        throws ValidationException {
//        when(userRepository.findById(firstTenant.getId()))
//                .thenReturn(firstLandlord);
//        when(accommodationRepository.findById(oneAccommodation.getId()))
//                .thenReturn(null);
//
//        accommodationService.addTenantToThisAccommodation(oneAccommodation, firstTenant);
//    }

    @Test
    public void removeTenantFromThisAccommodation_ShouldReturnSameAccommodationWithTenantSetToNull()
        throws ValidationException {
        when(accommodationRepository.findById(oneAccommodation.getId()))
                .thenReturn(oneAccommodation);

        Accommodation result = accommodationService.removeTenantFromThisAccommodation(oneAccommodation);

        Accommodation expected = oneAccommodation;
        expected.setTenant(null);
        Assert.assertEquals(expected, result);
    }

    @Test(expected = ValidationException.class)
    public void removeTenantFromThisAccommodation_ShouldThrowValidationExceptotion_WhenCalledOnNullAccommodation()
        throws ValidationException {
        when(accommodationRepository.findById(oneAccommodation.getId()))
                .thenReturn(null);

        accommodationService.removeTenantFromThisAccommodation(oneAccommodation);
    }

    @Test
    public void getAccommodationByItsId_ShouldCallCorrectRepositoryMethod()
        throws ValidationException {
        when(accommodationRepository.findById(oneAccommodation.getId()))
                .thenReturn(oneAccommodation);

        accommodationService.getAccommodationByItsId(oneAccommodation.getId());
        verify(accommodationRepository, times(1)).findById(1);
    }

    @Test(expected = ValidationException.class)
    public void getAccommodationByItsId_ShouldThrowValidationException_WhenCalledOnNullaccommodation()
        throws ValidationException {
        when(accommodationRepository.findById(oneAccommodation.getId()))
                .thenReturn(null);
        accommodationService.getAccommodationByItsId(oneAccommodation.getId());
    }

    @Test
    public void editAccommodationById_ShouldCallRepositorySave_WhenCalled()
        throws ValidationException {
        when(accommodationRepository.findById(oneAccommodation.getId()))
                .thenReturn(oneAccommodation);

        accommodationService.editAccommodationById(oneAccommodation.getId(), oneAccommodation);
        verify(accommodationRepository, times(1)).save(oneAccommodation);
    }

    @Test(expected = ValidationException.class)
    public void editAccommodationById_ShouldThrowValidationException_WhenCalledOnNullAccommodation()
        throws ValidationException {
        when(accommodationRepository.findById(oneAccommodation.getId()))
                .thenReturn(null);
        accommodationService.editAccommodationById(oneAccommodation.getId(), oneAccommodation);
    }

    @Test
    public void payRentForAccommodation_ShouldReturnChangedDueDate_WhenCalledWithDueDateToday()
        throws ValidationException {
        Accommodation test = oneAccommodation;
        LocalDate now = LocalDate.now();
        LocalDate dateAfterOneMonth = LocalDate.now().plusMonths(1);
        test.setDueDate(toTimestamp(now));

        when(accommodationRepository.findById(test.getId()))
                .thenReturn(test);

        Accommodation result = accommodationService.payRentForAccommodation(test.getId(), test);

        test.setDueDate(toTimestamp(dateAfterOneMonth));

        Assert.assertEquals(test.getDueDate(), result.getDueDate());
    }

    private static Timestamp toTimestamp(LocalDate localDate) {
        Date date = Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault()).toInstant());
        return new Timestamp(date.getTime());
    }

    @Test
    public void payRentForAccommodation_ShouldReturnSameDueDate_WhenCalledWithDateOneMonthFromNow()
        throws ValidationException {

        Accommodation test = oneAccommodation;
        LocalDate dateAfterOneMonth = LocalDate.now().plusMonths(1);
        test.setDueDate(toTimestamp(dateAfterOneMonth));
        when(accommodationRepository.findById(test.getId()))
                .thenReturn(test);

        Accommodation result = accommodationService.payRentForAccommodation(test.getId(), test);

        Assert.assertEquals(test.getDueDate(), result.getDueDate());
    }

    @Test(expected = ValidationException.class)
    public void payRentForAccommodation_ShouldThrowValidationException_WhenCalledWithInvalidId()
        throws ValidationException {

        when(accommodationRepository.findById(oneAccommodation.getId()))
                .thenReturn(null);
        accommodationService.payRentForAccommodation(oneAccommodation.getId(), oneAccommodation);
    }
}
