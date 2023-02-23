package com.findoutmyloan.application.facade.service.impl;

import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.collateral.enums.CollateralType;
import com.findoutmyloan.application.collateral.service.CollateralService;
import com.findoutmyloan.application.person.enums.PersonType;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import com.findoutmyloan.application.surety.service.SuretyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntityFacadeImplTest {
    @Mock
    private SuretyService suretyService;
    @Mock
    private CollateralService collateralService;
    @InjectMocks
    private EntityFacadeImpl entityFacade;

    private SuretyService mockSuretyService;
    private CollateralService mockCollateralService;


    @BeforeEach
    void setUp() {
        mockSuretyService=mock(SuretyService.class);
        mockCollateralService=mock(CollateralService.class);
        entityFacade=new EntityFacadeImpl(mockSuretyService, mockCollateralService);
    }

    @Test
    public void shouldSaveEntityCallSuretyServiceAndCollateralService() {

        // create valid requests
        SuretySaveRequestDTO suretySaveRequestDTO=mock(SuretySaveRequestDTO.class);
        when(suretySaveRequestDTO.getPersonType()).thenReturn(PersonType.CUSTOMER);
        CollateralSaveRequestDTO collateralSaveRequestDTO=mock(CollateralSaveRequestDTO.class);
        when(collateralSaveRequestDTO.getCollateralType()).thenReturn(CollateralType.MONEY);

        // call the saveEntity method
        entityFacade.saveEntity(suretySaveRequestDTO, collateralSaveRequestDTO);

        // verify that the SuretyService and CollateralService were called once
        verify(mockSuretyService, times(1)).saveSurety(suretySaveRequestDTO);
        verify(mockCollateralService, times(1)).saveCollateral(collateralSaveRequestDTO);
    }

    @Test
    void shouldSaveEntityDoesNotCallAnyServiceWithNullValues() {
        // create requests with null values
        SuretySaveRequestDTO suretySaveRequestDTO=mock(SuretySaveRequestDTO.class);
        when(suretySaveRequestDTO.getPersonType()).thenReturn(null);
        CollateralSaveRequestDTO collateralSaveRequestDTO=mock(CollateralSaveRequestDTO.class);
        when(collateralSaveRequestDTO.getCollateralType()).thenReturn(null);

        // call the saveEntity method
        entityFacade.saveEntity(suretySaveRequestDTO, collateralSaveRequestDTO);

        // verify that neither the SuretyService nor CollateralService were called
        verify(mockSuretyService, times(0)).saveSurety(any());
        verify(mockCollateralService, times(0)).saveCollateral(any());
    }
}