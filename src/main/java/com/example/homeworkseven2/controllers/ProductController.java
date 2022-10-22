package com.example.homeworkseven2.controllers;

import com.example.homeworkseven2.converters.ProductConverter;
import com.example.homeworkseven2.dtos.ProductDto;
import com.example.homeworkseven2.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @RequestMapping(value = "/add_product", method = RequestMethod.GET)
    public String addProductView(Model model) {
        model.addAttribute("product", new ProductDto());
        return "addProduct";
    }

    @RequestMapping(value = "/add_product", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("product") ProductDto productDto) {
        productService.addProduct(productDto.getName(), productDto.getPrice(), productDto.getShopId());
        return "addProductSuccess";
    }

    @RequestMapping(value = "/remove_product", method = RequestMethod.GET)
    public String removeProductByIdView(Model model) {
        model.addAttribute("product", new ProductDto());
        return "removeProduct";
    }

    @RequestMapping(value = "/remove_product", method = {RequestMethod.DELETE, RequestMethod.POST})
    @Transactional
    public String removeProductById(@ModelAttribute("person") ProductDto productDto) {
        productService.removeProductById(productDto.getId());
        return "removeProductSuccess";
    }

    @GetMapping("/all_products")
    public String getAllProducts(Model model) {
        model.addAttribute("all", productService.getAllProducts()
                .stream().map(ProductConverter::convertProductToProductDto).collect(Collectors.toList()));
        return "allProducts";
    }

    @RequestMapping(value = "/update_product_name", method = RequestMethod.GET)
    public String updateProductNameByIdView(Model model) {
        model.addAttribute("product", new ProductDto());
        return "updateProductName";
    }

    @RequestMapping(value = "/update_product_name", method = {RequestMethod.PUT, RequestMethod.POST})
    @Transactional
    public String updateProductNameById(@ModelAttribute("product") ProductDto productDto) {
        productService.updateProductNameById(productDto.getId(), productDto.getName());
        return "updateProductNameSuccess";
    }

    @RequestMapping(value = "/update_price", method = RequestMethod.GET)
    public String updateProductPriceByIdView(Model model) {
        model.addAttribute("product", new ProductDto());
        return "updateProductPrice";
    }

    @RequestMapping(value = "/update_price", method = {RequestMethod.PUT, RequestMethod.POST})
    @Transactional
    public String updateProductPriceById(@ModelAttribute("product") ProductDto productDto) {
        productService.updateProductPriceById(productDto.getId(), productDto.getPrice());
        return "updateProductPriceSuccess";
    }
}
