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
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);
        productData.add(product);
        return product;
    }

    public void edit(String targetId, Product editValues) {
        Product product = findById(targetId);
        product.setProductName(editValues.getProductName());
        product.setProductQuantity(editValues.getProductQuantity());
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
