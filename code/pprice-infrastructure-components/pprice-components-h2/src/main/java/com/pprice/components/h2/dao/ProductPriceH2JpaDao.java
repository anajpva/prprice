package com.pprice.components.h2.dao;

import java.util.List;

import com.pprice.components.h2.dto.ProductPriceH2DTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceH2JpaDao extends JpaRepository<ProductPriceH2DTO, String> {

  List<ProductPriceH2DTO> findByProductIdAndBrandId(Integer productId, Integer brandId);

}
