package com.example.lab72.ProductService;

import com.example.lab72.POJO.Product;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "Product", allEntries = true)
    @RabbitListener(queues = "AddProductQueue")
    public boolean addProduct(Product p){
        try {
            repository.insert(p);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @CachePut("Product")
    @RabbitListener(queues = "UpdateProductQueue")
    public boolean updateProduct(Product p){
        try {
            repository.save(p);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @CacheEvict(value = "Product", allEntries = true)
    @RabbitListener(queues = "DeleteProductQueue")
    public boolean deleteProduct(Product product){
        try {
            repository.delete(product);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @RabbitListener(queues = "GetNameProductQueue")
    public Product getProductByName(String productName){
        return repository.findByName(productName);
    }


//    @Cacheable(value="Product")
    @RabbitListener(queues = "GetAllProductQueue")
    public List<Product> getAllProduct(){
        try {
            return repository.findAll();
        }catch (Exception e){
            return null;
        }
    }


}
