package com.pprice.infrastructure.components.secondary.h2;

import java.util.List;

import com.pprice.components.h2.dao.ProductPriceH2JpaDao;
import com.pprice.components.h2.dto.ProductPriceH2DTO;
import com.pprice.domain.entity.Product;
import com.pprice.domain.entity.ProductPrices;
import com.pprice.domain.repository.ProductPricesRepository;
import com.pprice.infrastructure.components.mappers.h2.ProductPriceH2Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ProductPricesRepositoryH2 implements ProductPricesRepository {

  private final ProductPriceH2JpaDao productPriceH2JpaDao;

  private final ProductPriceH2Mapper productPriceH2Mapper;

  @Override
  public ProductPrices findByProduct(Product product) {

    Integer productId = product.productId();
    Integer brandId = product.brandId();

    List<ProductPriceH2DTO> productPriceDtos = productPriceH2JpaDao.findByProductIdAndBrandId(productId, brandId);

    return productPriceH2Mapper.toEntity(productPriceDtos, productId, brandId);
  }

}
