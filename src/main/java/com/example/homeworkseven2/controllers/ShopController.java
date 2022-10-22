package com.example.homeworkseven2.controllers;

import com.example.homeworkseven2.converters.ShopConverter;
import com.example.homeworkseven2.dtos.ShopDto;
import com.example.homeworkseven2.services.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }


    @RequestMapping(value = "/add_shop", method = RequestMethod.GET)
    public String addShopView(Model model) {
        model.addAttribute("shop", new ShopDto());
        return "addShop";
    }

    @RequestMapping(value = "/add_shop", method = RequestMethod.POST)
    public String addShop(@ModelAttribute("shop") ShopDto shopDto) {
        shopService.addShop(shopDto.getName());
        return "addShopSuccess";
    }

    @RequestMapping(value = "/remove_shop", method = RequestMethod.GET)
    public String removeShopByIdView(Model model) {
        model.addAttribute("shop", new ShopDto());
        return "removeShop";
    }

    @RequestMapping(value = "/remove_shop", method = {RequestMethod.DELETE, RequestMethod.POST})
    @Transactional
    public String removeShopById(@ModelAttribute("shop") ShopDto shopDto) {
        shopService.removeShopById(shopDto.getId());
        return "removeShopSuccess";
    }

    @GetMapping("/all_shops")
    public String getAllShops(Model model) {
        model.addAttribute("all", shopService.getAllShops().stream()
                .map(ShopConverter::convertShopToShopDto).collect(Collectors.toList()));
        return "allShops";
    }

    @RequestMapping(value = "/update_name", method = RequestMethod.GET)
    public String updateShopNameByIdView(Model model) {
        model.addAttribute("shop", new ShopDto());
        return "updateShopName";
    }

    @RequestMapping(value = "/update_name", method = {RequestMethod.PUT, RequestMethod.POST})
    @Transactional
    public String updateShopNameById(@ModelAttribute("shop") ShopDto shopDto) {
        shopService.updateShopNameById(shopDto.getId(), shopDto.getName());
        return "updateShopNameSuccess";
    }
}
