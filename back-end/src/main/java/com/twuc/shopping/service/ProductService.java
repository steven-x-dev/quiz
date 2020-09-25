package com.twuc.shopping.service;

import com.twuc.shopping.domain.Product;
import com.twuc.shopping.exception.ResourceNotExistsException;
import com.twuc.shopping.po.ProductPO;
import com.twuc.shopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> list(int pageSize, int pageIndex) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<ProductPO> page = productRepository.findAll(pageable);
        List<Product> products = new ArrayList<>();
        page.forEach(userPO -> products.add(new Product(userPO)));
        return products;
    }

    public Product findById(long id) {
        ProductPO productPO = productRepository.findById(id);
        if (productPO == null) {
            return null;
        } else {
            return new Product(productPO);
        }
    }

    public long create(Product product) {
        ProductPO productPO = ProductPO.builder()
                .name(product.getName())
                .unit(product.getUnit())
                .price(product.getPrice())
                .url(product.getUrl())
                .build();
        productRepository.save(productPO);
        return productPO.getId();
    }

    public void deleteById(long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotExistsException("product id");
        }
        productRepository.deleteById(id);
    }

}
