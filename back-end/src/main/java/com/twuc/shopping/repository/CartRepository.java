package com.twuc.shopping.repository;

import com.twuc.shopping.po.CartPO;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CartRepository extends PagingAndSortingRepository<CartPO, Long> {

    List<CartPO> findAll();

}
