package com.findoutmyloan.application.facade.impl;

import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.collateral.enums.CollateralType;
import com.findoutmyloan.application.collateral.service.CollateralService;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import com.findoutmyloan.application.surety.enums.SuretyType;
import com.findoutmyloan.application.surety.service.SuretyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EntityFacadeImplTest {
    @Mock
    private SuretyService suretyService;
    @Mock
    private CollateralService collateralService;

    @InjectMocks
    private EntityFacadeImpl entityFacade;

    @Test
    @DisplayName("Should save the collateral when the collateral type is not null")
    void saveEntityWhenCollateralTypeIsNotNullThenSaveCollateral() {
        CollateralSaveRequestDTO collateralSaveRequestDTO=
                CollateralSaveRequestDTO.builder().collateralType(CollateralType.CAR).build();

        entityFacade.saveEntity(null, collateralSaveRequestDTO);

        verify(collateralService, times(1)).saveCollateral(collateralSaveRequestDTO);
    }

    @Test
    @DisplayName("Should save the surety when the surety type is not null")
    void saveEntityWhenSuretyTypeIsNotNullThenSaveSurety() {
        SuretySaveRequestDTO suretySaveRequestDTO=
                SuretySaveRequestDTO.builder().suretyType(SuretyType.ORDINARY).build();
        CollateralSaveRequestDTO collateralSaveRequestDTO=
                CollateralSaveRequestDTO.builder().collateralType(CollateralType.CAR).build();

        entityFacade.saveEntity(suretySaveRequestDTO, collateralSaveRequestDTO);

        verify(suretyService, times(1)).saveSurety(suretySaveRequestDTO);
    }
}