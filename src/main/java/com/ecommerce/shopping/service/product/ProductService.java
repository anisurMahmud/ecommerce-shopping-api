package com.ecommerce.shopping.service.product;

import com.ecommerce.shopping.dto.ImageDTO;
import com.ecommerce.shopping.dto.ProductDTO;
import com.ecommerce.shopping.exception.ResourceNotFoundException;
import com.ecommerce.shopping.model.Category;
import com.ecommerce.shopping.model.Image;
import com.ecommerce.shopping.model.Product;
import com.ecommerce.shopping.repository.CategoryRepository;
import com.ecommerce.shopping.repository.ImageRepository;
import com.ecommerce.shopping.repository.ProductRepository;
import com.ecommerce.shopping.request.AddProductRequest;
import com.ecommerce.shopping.request.UpdateProductRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;

    public ProductService(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            ModelMapper modelMapper, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.imageRepository = imageRepository;
    }

    @Override
    public Product addProduct(AddProductRequest product) {
        //check of category is present in database
        //if yes, set new category for product
        //if no, create new category in category database

        Category category = Optional.ofNullable(categoryRepository.findByName(product.getCategory().getName()))
                .orElseGet(()->{
                    Category newCategory = new Category(product.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });

        product.setCategory(category);
        return productRepository.save(createProduct(product, category));
    }

    private Product createProduct(AddProductRequest product, Category category) {
        return new Product(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getBrand(),
                category
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                        ()->{throw new ResourceNotFoundException("Product not found");});

    }

    @Override
    public Product updateProductById(UpdateProductRequest product, Long id) {
        return productRepository.findById(id)
                .map(existingProduct -> updateExistingProduct(existingProduct, product))
                .map(productRepository :: save)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));

    }

    private Product updateExistingProduct(Product existingProduct, UpdateProductRequest updateRequest) {
        existingProduct.setName(updateRequest.getName());
        existingProduct.setBrand(updateRequest.getBrand());
        existingProduct.setPrice(updateRequest.getPrice());
        existingProduct.setQuantity(updateRequest.getQuantity());
        existingProduct.setDescription(updateRequest.getDescription());

        Category category = categoryRepository.findByName(updateRequest.getCategory().getName());
        existingProduct.setCategory(category);

        return existingProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

    @Override
    public List<ProductDTO> getConvertedProducts(List<Product> products){
        return products.stream().map(this::convertToDto).toList();
    }

    @Override
    public ProductDTO convertToDto(Product product){
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDTO> imageDTOS = images.stream()
                .map(image -> modelMapper.map(image, ImageDTO.class))
                .toList();
        productDTO.setImages(imageDTOS);
        return productDTO;
    }
}
