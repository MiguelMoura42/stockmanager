package com.miguel.stockmanager.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.miguel.stockmanager.models.EntryModel;
import com.miguel.stockmanager.models.ProductModel;
import com.miguel.stockmanager.repositories.EntryRepository;
import com.miguel.stockmanager.repositories.ProductRepository;
import com.miguel.stockmanager.requests.EntryRequest;
import com.miguel.stockmanager.requests.ProductRequest;
import com.miguel.stockmanager.responses.ProductResponse;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

  final ProductRepository productRepository;
  final EntryRepository entryRepository;

  public ProductService(ProductRepository productRepository, EntryRepository entryRepository) {
    this.productRepository = productRepository;
    this.entryRepository = entryRepository;
  }

  @Transactional
  public ProductResponse save(ProductRequest productRequest) {
    var productModel = new ProductModel();
    BeanUtils.copyProperties(productRequest, productModel);
    ProductModel savedProduct = productRepository.save(productModel);
    return new ProductResponse(savedProduct);
  }

  public List<ProductResponse> findAll() {
    List<ProductModel> products = productRepository.findAll();
    return products.stream()
        .map(product -> new ProductResponse(product))
        .collect(Collectors.toList());
  }

  public ProductResponse getProductByName(String name) {
    Optional<ProductModel> optionalProductModel = productRepository.findByName(name);
    if (optionalProductModel.isPresent()) {
      ProductModel productModel = optionalProductModel.get();
      return new ProductResponse(productModel);
    } else {
      throw new IllegalArgumentException("Product not found with this name!");
    }
  }

  @Transactional
  public void deleteProductById(Long id) {
    Optional<ProductModel> optionalProductModel = productRepository.findById(id);
    if (optionalProductModel.isPresent()) {
      productRepository.deleteById(id);
    } else {
      throw new IllegalArgumentException("Product not found with this id!");
    }
  }

  @Transactional
  public void addQuantityToProduct(EntryRequest entryRequest, Long productId) {
    ProductModel productModel = getProductById(productId);
    uptadeProductQuantity(entryRequest, productModel);
    saveEntryModel(entryRequest, productModel);
  }

  private ProductModel getProductById(Long productId) {
    return productRepository.findById(productId)
        .orElseThrow(() -> new IllegalArgumentException("Product not found!"));
  }

  public void uptadeProductQuantity(EntryRequest entryRequest, ProductModel productModel) {
    int currentQuantity = productModel.getQuantity();
    productModel.setQuantity((currentQuantity + entryRequest.getQuantity()));
    productRepository.save(productModel);
  }

  private void saveEntryModel(EntryRequest entryRequest, ProductModel productModel) {
    EntryModel entry = new EntryModel();
    entry.setProductModel(productModel);
    entry.setQuantity(entryRequest.getQuantity());
    entryRepository.save(entry);
  }

}
