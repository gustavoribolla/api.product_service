package store.product;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product create(Product product) {
        return productRepository.save(new ProductModel(product)).to();
    }

    @Cacheable(value = "productById", key = "#id")
    public Product findById(String id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"))
            .to();
    }

    @Cacheable("allProducts")
    public List<Product> findAll() {
        return StreamSupport
            .stream(productRepository.findAll().spliterator(), false)
            .map(ProductModel::to)
            .toList();
    }

    public Product deleteById(String id) {
        ProductModel m = productRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));
        productRepository.delete(m);
        return m.to();
    }
}
