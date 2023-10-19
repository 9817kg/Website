package my.site.project.service;


import my.site.project.entity.Product;
import my.site.project.repository.ProductRepository;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Product_Service {

    private final ProductRepository repository;

    public List<Product> Category_item_All(String item) {

        return repository.findByCategory(item);

    }

    public Optional<Product> SelectONE(Long id){
        Optional<Product> belt = repository.findById(id);
        //나중에 형 변환
        return belt;

    }
}
