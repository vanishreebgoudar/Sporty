package com.adminon.sporty.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.adminon.sporty.entity.Products;
import com.adminon.sporty.service.ProductService;
@Controller
public class ProductController {
	private ProductService ps;
	public ProductController(ProductService ps) {
		super();
		this.ps = ps;
	}
@GetMapping("/products")
public String getallproducts(Model model) {
	
	model.addAttribute("products", ps.getAllProducts());
	return"product";
}
@GetMapping("/products/new")
public String createProductForm(Model model) {
	
	Products products = new Products();
	model.addAttribute("products", products);
	return "createproduct";
}
@PostMapping("/productsadd")
public String savePrd(@ModelAttribute("products") Products products) {
	ps.saveProducts(products);
	return "redirect:/products";
}
@GetMapping("/products/edit/{id}")
public String editproductForm(@PathVariable Long id, Model model) {
	model.addAttribute("products", ps.getProductsById(id));
	return "editproduct";
}
@PostMapping("/products/{id}")
public String updateProduct(@PathVariable Long id,
		@ModelAttribute("products") Products products,
		Model model) {
	
	// get student from database by id
	Products existingproduct = ps.getProductsById(id);
	existingproduct.setId(id);
	existingproduct.setProduct_name(products.getProduct_name());
	existingproduct.setProduct_size(products.getProduct_size());
	existingproduct.setProduct_price(products.getProduct_price());
	existingproduct.setBrand_name(products.getBrand_name());
	existingproduct.setCategory(products.getCategory());
	existingproduct.setAvailable_qty(products.getAvailable_qty());
	// save updated student object
	ps.updateProducts(products);
	return "redirect:/products";		
}
@GetMapping("/products/{id}")
public String deleteProduct(@PathVariable Long id) {
    ps.deleteProductsById(id);
	return "redirect:/products";
}

}
