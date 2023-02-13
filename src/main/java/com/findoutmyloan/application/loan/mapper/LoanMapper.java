package com.findoutmyloan.application.loan.mapper;

import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.entity.Loan;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface LoanMapper {
   LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);
   LoanDTO convertToCreditDto(Loan loan);
   Loan convertToCredit(LoanDTO loanDTO);

    Loan convertToCredit(LoanSaveRequestDTO creditDto);
}