package com.findoutmyloan.application.surety.validation.command;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.facade.validation.command.LoanApplicationValidationCommand;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import com.findoutmyloan.application.surety.mapper.SuretyMapper;
import com.findoutmyloan.application.surety.validation.service.SuretyValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
@Primary
public class SuretyValidationCommand implements LoanApplicationValidationCommand {
   private final SuretyValidationService suretyValidationService;

    @Override
    public void validateLoanApplicationInformationIsMatchGuaranteeType(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        if (loanApplicationRequestDTO.getPaybackGuaranteeType()==PaybackGuaranteeType.SURETY) {
            suretyValidationService.validateFieldsAreNotNull(SuretyMapper.INSTANCE.
                    convertLoanApplicationRequestToSurety(loanApplicationRequestDTO));
        }
    }
}
