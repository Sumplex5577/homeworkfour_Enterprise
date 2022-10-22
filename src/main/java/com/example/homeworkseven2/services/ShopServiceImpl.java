package com.example.homeworkseven2.services;
import com.example.homeworkseven2.models.Shop;
import com.example.homeworkseven2.repositories.ShopRepository;
import com.example.homeworkseven2.NotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public void addShop(String name) {
        shopRepository.save(new Shop(name));
    }

    @Override
    public void removeShopById(Long id) {
        if (shopRepository.existsById(id)) {
            shopRepository.deleteById(id);
        } else {
            try {
                throw new NotFoundException("Shop with ID #" + id + " is not found");
            } catch (NotFoundException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    @Override
    public Shop getShopById(Long id) {
        if (shopRepository.findById(id).isPresent()) {
            return shopRepository.findById(id).get();
        } else {
            try {
                throw new NotFoundException("Shop with ID #" + id + " is not found");
            } catch (NotFoundException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    @Override
    public List<Shop> getAllShops() {
        return (List<Shop>) shopRepository.findAll();
    }

    @Override
    public void updateShopNameById(Long id, String name) {
        if (shopRepository.existsById(id)) {
            shopRepository.updateNameById(id, name);
        } else {
            try {
                throw new NotFoundException("Shop with ID #" + id + " is not found");
            } catch (NotFoundException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
