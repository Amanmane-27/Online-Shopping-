package com.example.demo.service.IMP;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImp implements ProductService {
	
	@Autowired
	private ProductRepo productRepo; 
	
	@Override
	public boolean saveProduct(Product product) {
	
		try {
			productRepo.save(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepo.findAll() ;
	}

	@Override
	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepo.findById(id).orElse(null);
	}

	@Override
	public boolean deleteProduct(Long id) {
	    if(productRepo.existsById(id)) {
	        productRepo.deleteById(id);
	        return true;
	    }
	    return false;
	}

	
	
}
