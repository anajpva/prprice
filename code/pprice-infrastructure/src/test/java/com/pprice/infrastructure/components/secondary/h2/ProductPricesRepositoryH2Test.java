package com.pprice.infrastructure.components.secondary.h2;

import static com.pprice.components.h2.dto.mother.ProductPriceH2DTOMother.oneProductPriceH2Dto;
import static com.pprice.domain.entity.mother.ProductMother.oneProduct;
import static com.pprice.domain.entity.mother.ProductPricesMother.oneProductPrices;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import com.pprice.components.h2.dao.ProductPriceH2JpaDao;
import com.pprice.components.h2.dto.ProductPriceH2DTO;
import com.pprice.domain.entity.Product;
import com.pprice.domain.entity.ProductPrices;
import com.pprice.infrastructure.components.mappers.h2.CurrencyH2MapperImpl;
import com.pprice.infrastructure.components.mappers.h2.InstantH2MapperImpl;
import com.pprice.infrastructure.components.mappers.h2.PriceH2MapperImpl;
import com.pprice.infrastructure.components.mappers.h2.ProductH2MapperImpl;
import com.pprice.infrastructure.components.mappers.h2.ProductPriceH2Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductPricesRepositoryH2Test {

  @Mock
  private ProductPriceH2JpaDao productPriceH2JpaDao;

  @Spy
  private PriceH2MapperImpl priceH2Mapper = new PriceH2MapperImpl(new InstantH2MapperImpl(), new CurrencyH2MapperImpl());

  @Spy
  private ProductH2MapperImpl productH2Mapper;

  private ProductPricesRepositoryH2 productPricesRepositoryH2;

  @BeforeEach
  void setUp() {
    ProductPriceH2Mapper productPriceH2Mapper = new ProductPriceH2Mapper(priceH2Mapper, productH2Mapper);

    productPricesRepositoryH2 = new ProductPricesRepositoryH2(productPriceH2JpaDao, productPriceH2Mapper);
  }

  @Test
  void shouldCallUseCaseToGetProductPrice() {
    Product product = oneProduct();
    ProductPriceH2DTO productPriceH2Dto = oneProductPriceH2Dto();

    when(productPriceH2JpaDao.findByProductIdAndBrandId(productPriceH2Dto.getProductId(), productPriceH2Dto.getBrandId()))
        .thenReturn(List.of(productPriceH2Dto));

    ProductPrices result = productPricesRepositoryH2.findByProduct(product);

    assertEquals(oneProductPrices(), result);
  }

}
