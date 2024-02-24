package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        if (product.getProductId() == null) {
            String productId = UUID.randomUUID().toString();
            product.setProductId(productId);
        }
        productData.add(product);
        return product;
    }

    public void edit(String targetId, Product editedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            Product product = productData.get(i);
            if (product.getProductId().equals(targetId)) {
                productData.remove(product);
                productData.add(i, editedProduct);
            }
        }
    }

    public void delete(String targetId) {
        productData.removeIf(product -> {
            String productId = product.getProductId();
            return productId.equals(targetId);
        });
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String targetId) {
        for (Product product : productData) {
            String productId = product.getProductId();
            if (productId.equals(targetId)) {
                return product;
            }
        }

        return null;
    }
}
