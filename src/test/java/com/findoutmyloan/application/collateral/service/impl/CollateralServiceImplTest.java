package com.findoutmyloan.application.collateral.service.impl;

import com.findoutmyloan.application.collateral.dto.CollateralDTO;
import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.collateral.entity.Collateral;
import com.findoutmyloan.application.collateral.enums.CollateralType;
import com.findoutmyloan.application.collateral.repository.CollateralRepository;
import com.findoutmyloan.application.collateral.validation.service.CollateralValidationService;
import com.findoutmyloan.application.customer.enums.CustomerProfiler;
import com.findoutmyloan.application.customer.profiler.service.CustomerProfilerService;
import com.findoutmyloan.application.general.exception.IllegalFieldException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CollateralServiceImplTest {
    @Mock
    private CollateralValidationService collateralValidationService;
    @Mock
    private CustomerProfilerService customerProfilerService;
    @InjectMocks
    private CollateralServiceImpl collateralService;
    @Mock
    private CollateralRepository collateralRepository;

    private CollateralSaveRequestDTO requestDto;
    private Collateral mockCollateral;
    private CollateralServiceImpl instance;
    private CollateralServiceImpl spy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        requestDto=mock(CollateralSaveRequestDTO.class);
        mockCollateral=mock(Collateral.class);
        customerProfilerService=mock(CustomerProfilerService.class);
        instance=new CollateralServiceImpl(collateralRepository, customerProfilerService, collateralValidationService);
        spy=spy(instance);
    }

    @Test
    void shouldSaveCollateral() {
        CollateralSaveRequestDTO requestDto=mock(CollateralSaveRequestDTO.class);
        when(requestDto.getCollateralType()).thenReturn(CollateralType.CAR);
        when(requestDto.getWorth()).thenReturn(1000.0f);

        Collateral mockCollateral=new Collateral();
        mockCollateral.setId(1L);
        mockCollateral.setWorth(1000.0f);
        mockCollateral.setCollateralType(CollateralType.CAR);

        CollateralValidationService mockValidationService=mock(CollateralValidationService.class);


        CollateralDTO mockDto=new CollateralDTO();
        mockDto.setCollateralType(CollateralType.CAR);
        mockDto.setWorth(1000.0f);

        doReturn(1234L).when(spy).getCurrentCustomerId();
        doReturn(mockCollateral).when(collateralRepository).save(ArgumentMatchers.any());

        CollateralDTO result=spy.saveCollateral(requestDto);

        assertEquals(1000f, result.getWorth());
        assertEquals(CollateralType.CAR, result.getCollateralType());
        verify(spy).getCurrentCustomerId();
    }

    @Test
    void shouldNotSaveCollateralWhenFieldsAreNull() {


        CollateralValidationService mockValidationService=mock(CollateralValidationService.class);
        doThrow(IllegalFieldException.class).when(mockValidationService).validateCollateral(mockCollateral);


        doThrow(NullPointerException.class).when(mockValidationService).validateCollateral(mockCollateral);


        assertThrows(NullPointerException.class, ()->{
            instance.saveCollateral(requestDto);
        });
        verifyNoInteractions(collateralRepository);
    }

    @Test
    void ShouldAddCollateralWorthToLoanLimitWoodPath() {

        float collateralWorth=0.0f;
        int creditScore=480;
        float monthlyIncome=4000.0f;
        float amount=0.0f;
        float expectedAmount=0.0f;

        when(customerProfilerService.getCustomerProfile(creditScore, monthlyIncome))
                .thenReturn(CustomerProfiler.WOOD);

        float actualAmount=instance.addCollateralWorthToLoanLimit(collateralWorth, creditScore, monthlyIncome, amount);

        assertEquals(expectedAmount, actualAmount, 0.01f);
    }

    @Test
    void ShouldAddCollateralWorthToLoanLimitBronzePath() {

        float collateralWorth=2000.0f;
        int creditScore=700;
        float monthlyIncome=4000.0f;
        float amount=10000.0f;
        float expectedAmount=10200.0f;

        when(customerProfilerService.getCustomerProfile(creditScore, monthlyIncome))
                .thenReturn(CustomerProfiler.BRONZE);

        float actualAmount=instance.addCollateralWorthToLoanLimit(collateralWorth, creditScore, monthlyIncome, amount);

        assertEquals(expectedAmount, actualAmount, 0.01f);
    }

    @Test
    void ShouldAddCollateralWorthToLoanLimitSilverPath() {
        CustomerProfilerService customerProfilerService=mock(CustomerProfilerService.class);

        float collateralWorth=2000.0f;
        int creditScore=700;
        float monthlyIncome=6000.0f;
        float amount=20000.0f;
        float expectedAmount=20400.0f;

        when(customerProfilerService.getCustomerProfile(creditScore, monthlyIncome))
                .thenReturn(CustomerProfiler.SILVER);

        CollateralServiceImpl instance=new CollateralServiceImpl(collateralRepository, customerProfilerService, collateralValidationService);
        float actualAmount=instance.addCollateralWorthToLoanLimit(collateralWorth, creditScore, monthlyIncome, amount);

        assertEquals(expectedAmount, actualAmount, 0.01f);
    }


    @Test
    void ShouldAddCollateralWorthToLoanLimitGoldPath() {
        float collateralWorth=2000.0f;
        int creditScore=700;
        float monthlyIncome=12000.0f;
        float amount=24000.0f;
        float expectedAmount=24500.0f;

        when(customerProfilerService.getCustomerProfile(creditScore, monthlyIncome))
                .thenReturn(CustomerProfiler.GOLD);

        float actualAmount=instance.addCollateralWorthToLoanLimit(collateralWorth, creditScore, monthlyIncome, amount);

        assertEquals(expectedAmount, actualAmount, 0.01f);
    }

    @Test
    void ShouldAddCollateralWorthToLoanLimitPlatinumPath() {
        float collateralWorth=2000.0f;
        int creditScore=1100;
        float monthlyIncome=12000.0f;
        float amount=24000.0f;
        float expectedAmount=25000.0f;

        when(customerProfilerService.getCustomerProfile(creditScore, monthlyIncome))
                .thenReturn(CustomerProfiler.PLATINUM);

        float actualAmount=instance.addCollateralWorthToLoanLimit(collateralWorth, creditScore, monthlyIncome, amount);

        assertEquals(expectedAmount, actualAmount, 0.01f);
    }
}