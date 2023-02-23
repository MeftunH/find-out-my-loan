package com.findoutmyloan.application.loan.service.impl;

import com.findoutmyloan.application.creditscore.enums.CreditScoreType;
import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.enums.CustomerProfiler;
import com.findoutmyloan.application.customer.profiler.service.CustomerProfilerService;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;
import com.findoutmyloan.application.loan.entity.Loan;
import com.findoutmyloan.application.loan.repository.LoanRepository;
import com.findoutmyloan.application.loan.validation.LoanValidationService;
import com.findoutmyloan.application.notification.service.NotificationService;
import com.findoutmyloan.application.security.service.AuthenticationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceImplTest {

    @Mock
    NotificationService notificationService;
    @Mock
    private LoanRepository loanRepository;
    @Mock
    private CustomerProfilerService customerProfilerService;
    @Mock
    private ApplicationEventPublisher applicationEventPublisher;
    @Mock
    private EntityManager entityManager;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private LoanValidationService loanValidationService;
    @Mock
    private AuthenticationService authenticationService;
    @InjectMocks
    @Spy
    private LoanServiceImpl loanService;

    @Test
    void shouldIsSuitableForCalculateReturnTrueWithCreditScoreGreaterThanLow() {
        // Arrange
        int creditScore=CreditScoreType.LOW_CREDIT_SCORE.getMaximumCreditScore()+1;

        // Act
        boolean result=loanService.isSuitableForCalculate(creditScore);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldIsSuitableForCalculateReturnTrueWithCreditScoreEqualToLow() {
        // Arrange
        int creditScore=CreditScoreType.LOW_CREDIT_SCORE.getMaximumCreditScore();

        // Act
        boolean result=loanService.isSuitableForCalculate(creditScore);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldIsSuitableForCalculateReturnFalseWithCreditScoreLessThanLow() {
        // Arrange
        int creditScore=CreditScoreType.LOW_CREDIT_SCORE.getMaximumCreditScore()-1;

        // Act
        boolean result=loanService.isSuitableForCalculate(creditScore);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldCalculateLimitOfLoanWithBronzeCustomerReturnBronzeLimit() {
        // Arrange
        int creditScore=CreditScoreType.LOW_CREDIT_SCORE.getMaximumCreditScore();
        float monthlyIncome=5000.0f;
        CustomerProfiler bronzeProfile=CustomerProfiler.BRONZE;
        when(customerProfilerService.getCustomerProfile(creditScore, monthlyIncome)).thenReturn(bronzeProfile);

        // Act
        float limit=loanService.calculateLimitOfLoan(creditScore, monthlyIncome);

        // Assert
        assertEquals(10000.0f, limit);
    }

    @Test
    void shouldCalculateLimitOfLoanWithSilverCustomerReturnSilverLimit() {
        // Arrange
        int creditScore=CreditScoreType.MEDIUM_CREDIT_SCORE.getMaximumCreditScore();
        float monthlyIncome=10000.0f;
        CustomerProfiler silverProfile=CustomerProfiler.SILVER;
        when(customerProfilerService.getCustomerProfile(creditScore, monthlyIncome)).thenReturn(silverProfile);

        // Act
        float limit=loanService.calculateLimitOfLoan(creditScore, monthlyIncome);

        // Assert
        assertEquals(20000.0f, limit);
    }

    @Test
    void shouldCalculateLimitOfLoanWithGoldCustomerReturnGoldLimit() {
        // Arrange
        int creditScore=CreditScoreType.HIGH_CREDIT_SCORE.getMaximumCreditScore();
        float monthlyIncome=15000.0f;
        CustomerProfiler goldProfile=CustomerProfiler.GOLD;
        float expectedLimit=15000.0f*4/2;
        when(customerProfilerService.getCustomerProfile(creditScore, monthlyIncome)).thenReturn(goldProfile);

        // Act
        float limit=loanService.calculateLimitOfLoan(creditScore, monthlyIncome);

        // Assert
        assertEquals(expectedLimit, limit);
    }

    @Test
    void shouldCalculateLimitOfLoanWithPlatinumCustomerReturnPlatinumLimit() {
        // Arrange
        int creditScore=CreditScoreType.HIGH_CREDIT_SCORE.getMaximumCreditScore();
        float monthlyIncome=20000.0f;
        CustomerProfiler platinumProfile=CustomerProfiler.PLATINUM;
        float expectedLimit=20000.0f*4;
        when(customerProfilerService.getCustomerProfile(creditScore, monthlyIncome)).thenReturn(platinumProfile);

        // Act
        float limit=loanService.calculateLimitOfLoan(creditScore, monthlyIncome);

        // Assert
        assertEquals(expectedLimit, limit);
    }

    @Test
    void shouldCalculateLimitOfLoanThrowIllegalArgumentExceptionWithInvalidCreditScore() {
        // Arrange
        int creditScore=-500;
        float monthlyIncome=2000.0f;
        when(customerProfilerService.getCustomerProfile(creditScore, monthlyIncome)).thenThrow(IllegalArgumentException.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, ()->loanService.calculateLimitOfLoan(creditScore, monthlyIncome));
    }

    @Test
    void shouldCalculateLimitOfLoanThrowIllegalArgumentExceptionWithInvalidMonthlyIncome() {
        // Arrange
        int creditScore=500;
        float monthlyIncome=-2000.0f;
        when(customerProfilerService.getCustomerProfile(creditScore, monthlyIncome)).thenThrow(IllegalArgumentException.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, ()->loanService.calculateLimitOfLoan(creditScore, monthlyIncome));
    }

    @Test
    void shouldThrowExceptionCalculateLimitOfLoanWithValidCreditScoreAndMonthlyIncomeAndNullCustomerProfile() {
        // Arrange
        int creditScore=500;
        float monthlyIncome=2000.0f;
        when(customerProfilerService.getCustomerProfile(creditScore, monthlyIncome)).thenReturn(null);

        // Act & Assert
        assertThrows(NullPointerException.class, ()->loanService.calculateLimitOfLoan(creditScore, monthlyIncome));
    }

    @Test
    void shouldSaveLoanWithValidLoanReturnsLoanDTO() {
        // Arrange
        LoanSaveRequestDTO loanSaveRequestDTO=mock(LoanSaveRequestDTO.class);
        when(loanSaveRequestDTO.getAmount()).thenReturn(1000.0f);

        Loan loan=new Loan();
        loan.setAmount(loanSaveRequestDTO.getAmount());

        Customer customer=new Customer();
        customer.setId(1L);

        when(loanRepository.save(any(Loan.class))).thenReturn(loan);
        doReturn(customer).when(customerRepository).getReferenceById(anyLong());

        AuthenticationService authenticationService=mock(AuthenticationService.class);
        long customerId=1L;
        doReturn(customerId).when(loanService).getCurrentCustomerId();

        // Act
        LoanDTO result=loanService.saveLoan(loanSaveRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(result.getAmount(), loan.getAmount());
    }


    @Test
    void shouldFindLoansByCustomerIdentityNoAndCustomerBirthDateGoToRepository() {
        // Create a sample loan
        long identityNo=1234567890L;
        Date birthDate=new Date(2000, 0, 1);

        Loan loan=mock(Loan.class);

        // Mock the query to return the sample loan
        List<Loan> loans=new ArrayList<>();
        loans.add(loan);
        Query query=mock(Query.class);

        // Call the service function
        List<Loan> foundLoans=loanRepository.findLoansByCustomerIdentityNoAndCustomerBirthDate(identityNo, birthDate);

        // Verify that the function returns the sample loan
        verify(loanRepository, times(1)).findLoansByCustomerIdentityNoAndCustomerBirthDate(identityNo, birthDate);
    }

    @Test
    void shouldFindLoansByCustomerIdentityNoAndCustomerBirthDate() {
        // Create a sample loan
        long identityNo=1234567890L;
        Date birthDate=new Date(2000, 0, 1);
        Customer customer=mock(Customer.class);

        Loan loan=mock(Loan.class);

        // Mock the query to return the sample loan
        List<Loan> loans=new ArrayList<>();
        loans.add(loan);
        when(loanRepository.findLoansByCustomerIdentityNoAndCustomerBirthDate(identityNo, birthDate)).thenReturn(loans);
        // Call the service function
        List<Loan> foundLoans=loanService.findLoansByCustomerIdentityNoAndCustomerBirthDate(identityNo, birthDate);

        // Verify that the function returns the sample loan
        assertEquals(1, foundLoans.size());
        assertEquals(loan, foundLoans.get(0));
    }

    @Test
    void shouldNotFindLoansByCustomerIdentityNoAndCustomerBirthDate() {
        // Create a sample loan
        long identityNo=1234567890L;
        Date birthDate=new Date(2000, 0, 1);
        Customer customer=mock(Customer.class);


        // Call the service function
        List<Loan> foundLoans=loanRepository.findLoansByCustomerIdentityNoAndCustomerBirthDate(identityNo, birthDate);

        // Verify that the function returns the sample loan
        assertEquals(0, foundLoans.size());

    }
}