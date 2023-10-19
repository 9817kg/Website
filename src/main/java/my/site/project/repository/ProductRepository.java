package my.site.project.repository;


import my.site.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findAllByCategory(String category);

    List<Product> findByCategory(String item);
}
