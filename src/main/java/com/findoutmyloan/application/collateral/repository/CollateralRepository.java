package com.findoutmyloan.application.collateral.repository;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.entity.Collateral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollateralRepository extends JpaRepository<Collateral, Long> {
}
