package com.miguel.stockmanager.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.miguel.stockmanager.models.EntryModel;
import com.miguel.stockmanager.models.ExitModel;
import com.miguel.stockmanager.models.ProductModel;
import com.miguel.stockmanager.repositories.EntryRepository;
import com.miguel.stockmanager.repositories.ExitRepository;
import com.miguel.stockmanager.repositories.ProductRepository;
import com.miguel.stockmanager.requests.EntryRequest;
import com.miguel.stockmanager.requests.ExitRequest;
import com.miguel.stockmanager.requests.ProductRequest;
import com.miguel.stockmanager.responses.ProductResponse;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

  final ProductRepository productRepository;
  final EntryRepository entryRepository;
  final ExitRepository exitRepository;

  public ProductService(ProductRepository productRepository, EntryRepository entryRepository,
      ExitRepository exitRepository) {
    this.productRepository = productRepository;
    this.entryRepository = entryRepository;
    this.exitRepository = exitRepository;
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
        .map(ProductResponse::new)
        .collect(Collectors.toList());
  }

  public ProductResponse getProductByName(String name) {
    Optional<ProductModel> optionalProductModel = productRepository.findByName(name);
    return optionalProductModel.map(ProductResponse::new)
        .orElseThrow(() -> new IllegalArgumentException("Product not found with this name!"));
  }

  @Transactional
  public void deleteProductById(Long id) {
    Optional<ProductModel> optionalProductModel = productRepository.findById(id);
    optionalProductModel.ifPresentOrElse(
        product -> productRepository.deleteById(id), () -> {
          throw new IllegalArgumentException("Product not found with this id!");
        });
  }

  @Transactional
  public void addQuantityToProduct(EntryRequest entryRequest, Long productId) {
    ProductModel productModel = getProductById(productId);
    addQuantity(entryRequest, productModel);
    saveEntryModel(entryRequest, productModel);
  }

  @Transactional
  public void removeQuantityToProduct(ExitRequest exitRequest, Long productId) {
    ProductModel productModel = getProductById(productId);
    validatingExitRequest(exitRequest, productModel);
    removeQuantity(exitRequest, productModel);
    saveExitModel(exitRequest, productModel);
  }

  private ProductModel getProductById(Long productId) {
    return productRepository.findById(productId)
        .orElseThrow(() -> new IllegalArgumentException("Product not found!"));
  }

  private void validatingExitRequest(ExitRequest exitRequest, ProductModel productModel) {
    int currentQuantity = productModel.getQuantity();
    int quantityToRemove = exitRequest.getQuantity();
    if (quantityToRemove > currentQuantity) {
      throw new IllegalArgumentException("Quantity to remove exceeds available quantity in stock.");
    }
  }

  public void addQuantity(EntryRequest entryRequest, ProductModel productModel) {
    int currentQuantity = productModel.getQuantity();
    productModel.setQuantity((currentQuantity + entryRequest.getQuantity()));
    productRepository.save(productModel);
  }

  public void removeQuantity(ExitRequest exitRequest, ProductModel productModel) {
    int currentQuantity = productModel.getQuantity();
    productModel.setQuantity((currentQuantity - exitRequest.getQuantity()));
    productRepository.save(productModel);
  }

  private void saveEntryModel(EntryRequest entryRequest, ProductModel productModel) {
    EntryModel entry = new EntryModel();
    entry.setProductModel(productModel);
    entry.setQuantity(entryRequest.getQuantity());
    entryRepository.save(entry);
  }

  private void saveExitModel(ExitRequest exitRequest, ProductModel productModel) {
    ExitModel exit = new ExitModel();
    exit.setProductModel(productModel);
    exit.setQuantity(exitRequest.getQuantity());
    exitRepository.save(exit);
  }
}
