package com.bezkoder.spring.jpa.h2.cqrs;

import java.util.*;

// ===== DOMAIN =====
class Product {
    private final UUID id;
    private String name;
    private double price;

    public Product(UUID id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + "}";
    }
}

// ===== COMMANDS =====
interface Command {}
class CreateProductCommand implements Command {
    public final UUID id;
    public final String name;
    public final double price;

    public CreateProductCommand(UUID id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

class UpdateProductPriceCommand implements Command {
    public final UUID id;
    public final double newPrice;

    public UpdateProductPriceCommand(UUID id, double newPrice) {
        this.id = id;
        this.newPrice = newPrice;
    }
}

// ===== QUERIES =====
interface Query<R> {}
class GetProductByIdQuery implements Query<Product> {
    public final UUID id;
    public GetProductByIdQuery(UUID id) { this.id = id; }
}

class GetAllProductsQuery implements Query<List<Product>> {}

// ===== REPOSITORY (In-memory for demo) =====
class ProductRepository {
    private final Map<UUID, Product> store = new HashMap<>();

    public void save(Product product) {
        store.put(product.getId(), product);
    }

    public Product findById(UUID id) {
        return store.get(id);
    }

    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }
}

// ===== COMMAND HANDLER =====
class ProductCommandHandler {
    private final ProductRepository repository;

    public ProductCommandHandler(ProductRepository repository) {
        this.repository = repository;
    }

    public void handle(CreateProductCommand cmd) {
        if (cmd.price < 0) throw new IllegalArgumentException("Price cannot be negative");
        Product product = new Product(cmd.id, cmd.name, cmd.price);
        repository.save(product);
    }

    public void handle(UpdateProductPriceCommand cmd) {
        Product product = repository.findById(cmd.id);
        if (product == null) throw new NoSuchElementException("Product not found");
        if (cmd.newPrice < 0) throw new IllegalArgumentException("Price cannot be negative");
        product.setPrice(cmd.newPrice);
        repository.save(product);
    }
}

// ===== QUERY HANDLER =====
class ProductQueryHandler {
    private final ProductRepository repository;

    public ProductQueryHandler(ProductRepository repository) {
        this.repository = repository;
    }

    public Product handle(GetProductByIdQuery query) {
        return repository.findById(query.id);
    }

    public List<Product> handle(GetAllProductsQuery query) {
        return repository.findAll();
    }
}

// ===== MAIN DEMO =====
public class CqrsExample {
    public static void main(String[] args) {
        ProductRepository repository = new ProductRepository();
        ProductCommandHandler commandHandler = new ProductCommandHandler(repository);
        ProductQueryHandler queryHandler = new ProductQueryHandler(repository);

        // Create products
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        commandHandler.handle(new CreateProductCommand(id1, "Laptop", 1200.0));
        commandHandler.handle(new CreateProductCommand(id2, "Phone", 800.0));

        // Update product price
        commandHandler.handle(new UpdateProductPriceCommand(id1, 1100.0));

        // Query single product
        Product p = queryHandler.handle(new GetProductByIdQuery(id1));
        System.out.println("Queried Product: " + p);

        // Query all products
        List<Product> all = queryHandler.handle(new GetAllProductsQuery());
        System.out.println("All Products: " + all);
    }
}
