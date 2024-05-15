package com.pprice.components.h2.dto;

import java.time.OffsetDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "PRICES")
public class ProductPriceH2DTO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private Integer productId;

  private Integer brandId;

  private OffsetDateTime startDate;

  private OffsetDateTime endDate;

  private Integer priority;

  private Integer priceList;

  private Double price;

  private String curr;

  public ProductPriceH2DTO() {
  }

  public ProductPriceH2DTO(Integer productId, Integer brandId, OffsetDateTime startDate, OffsetDateTime endDate, Integer priority,
      Integer priceList, Double price, String curr) {
    this.productId = productId;
    this.brandId = brandId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.priority = priority;
    this.priceList = priceList;
    this.price = price;
    this.curr = curr;
  }

}
