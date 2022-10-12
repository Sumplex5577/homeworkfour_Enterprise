package com.example.homeworksix.service;

import com.example.homeworksix.dto.ProductDto;
import com.example.homeworksix.model.Product;
import com.example.homeworksix.repository.ProductRepository;

import com.example.homeworksix.utils.exception.NotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

import static com.example.homeworksix.converter.ProductConverter.convertProductDtoToProduct;
import static com.example.homeworksix.converter.ProductConverter.convertProductToProductDto;
import static com.example.homeworksix.converter.ShopConverter.convertShopDtoToShop;


@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ShopService shopService;

    public ProductServiceImpl(ProductRepository productRepository, ShopService shopService) {
        this.productRepository = productRepository;
        this.shopService = shopService;
    }

    @Override
    public Long addProduct(ProductDto dto) {
        Product product = new Product(dto.getName(), dto.getPrice());
        product.setShop(convertShopDtoToShop(shopService.getShopById(dto.getShopId())));
        shopService.getShopById(dto.getShopId()).getProducts().add(product);
        return productRepository.save(product).getId();
    }

    @Override
    public Long removeProductById(Long id) {
        if (productRepository.existsById(id)) {
            shopService.getShopById(id).getProducts().remove(convertProductDtoToProduct(getProductById(id)));
            productRepository.deleteById(id);
            return id;
        }
        try {
            throw new NotFoundException("Product with ID #" + id + " is not found");
        } catch (NotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public ProductDto getProductById(Long id) {
        if (productRepository.findById(id).isPresent()) {
            return convertProductToProductDto(productRepository.findById(id).get());
        }
        try {
            throw new NotFoundException("Product with ID #" + id + " is not found");
        } catch (NotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductDto> productDtoList = new ArrayList<>();
        productRepository.findAll().forEach(product -> productDtoList.add(convertProductToProductDto(product)));
        return productDtoList;
    }

    @Override
    public Long updateProductNameById(Long id, ProductDto dto) {
        if (productRepository.existsById(id)) {
            return Long.valueOf(productRepository.updateProductNameById(id, dto.getName()));
        }
        try {
            throw new NotFoundException("Product with ID #" + id + " is not found");
        } catch (NotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Long updateProductPriceById(Long id, ProductDto dto) {
        if (productRepository.existsById(id)) {
            return Long.valueOf(productRepository.updateProductSumById(id, dto.getPrice()));
        }
        try {
            throw new NotFoundException("Product with ID #" + id + " is not found");
        } catch (NotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
