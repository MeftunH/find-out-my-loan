package com.findoutmyloan.application.facade.impl;

import com.findoutmyloan.application.collateral.enums.CollateralType;
import com.findoutmyloan.application.customer.dto.CustomerCreditScoreRequestDTO;
import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import com.findoutmyloan.application.person.enums.PersonType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BuilderFacadeImplTest {

    @InjectMocks
    private BuilderFacadeImpl builderFacade;

    @Test
    @DisplayName("Should return customercreditscorerequestdto")
    void getCustomerCreditScoreRequestDTOShouldReturnCustomerCreditScoreRequestDTO() {
        LoanApplicationRequestDTO loanApplicationRequestDTO=
                LoanApplicationRequestDTO.builder()
                        .customerName("name")
                        .customerSurname("surname")
                        .customerIdentityNo(123456789)
                        .customerBirthDate(new Date())
                        .customerPhoneNumber("123456789")
                        .customerPersonType(PersonType.CUSTOMER)
                        .customerMonthlyIncome(1000)
                        .suretyName("name")
                        .suretySurname("surname")
                        .suretyIdentityNo(123456789)
                        .suretyBirthDate(new Date())
                        .suretyPhoneNumber("123456789")
                        .suretyPersonType(PersonType.SURETY)
                        .collateralType(CollateralType.CAR)
                        .collateralWorth(1000)
                        .paybackGuaranteeType(PaybackGuaranteeType.COLLATERAL)
                        .build();

        builderFacade.invokeBuilder(loanApplicationRequestDTO);

        assertEquals(
                builderFacade.getCustomerCreditScoreRequestDTO(),
                CustomerCreditScoreRequestDTO.builder()
                        .name("name")
                        .surname("surname")
                        .birthDate(new Date())
                        .identityNo(123456789)
                        .personType(PersonType.CUSTOMER)
                        .phoneNumber("123456789")
                        .monthlyIncome(1000)
                        .build());
    }
}