package com.twuc.shopping.repository;

import com.twuc.shopping.po.ProductPO;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<ProductPO, Long> {

    List<ProductPO> findAll();

    ProductPO findById(long id);

}
