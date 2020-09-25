package com.twuc.shopping.repository;

import com.twuc.shopping.po.ProductPO;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<ProductPO, Long> {

    ProductPO findById(long id);

}
