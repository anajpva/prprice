package com.pprice.domain.repositories;

import com.pprice.domain.entity.Product;
import com.pprice.domain.entity.ProductPrices;

public interface ProductPricesRepository {

  ProductPrices findByProduct(Product product);

}
