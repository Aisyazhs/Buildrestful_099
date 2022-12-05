/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ws.a.contoh;

import Model2.Product;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AISYAH SUHERMAN
 */
@RestController
public class ProductServiceController {
    
    private static Map<String, Product> productRepo = new HashMap<>(); ///use a Hashmap to store Product
    static{
        Product honey = new Product();
        honey.setId("1");
        honey.setName ("Honey");
        productRepo.put(honey.getId(), honey);
        
        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
        
        Product milk = new Product();
        almond.setId("3");
        almond.setName("milk");
        productRepo.put(milk.getId(), milk);
        
        
        
        
    }
    
    //Delete data
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete (@PathVariable("id") String id){
        productRepo.remove(id);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }
    
    //edit data
    @RequestMapping(value = "/products/(id)", method =RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable ("Id") String id, @RequestBody Product product){
        
        if(productRepo.containsKey(id)){
            
            productRepo.remove(id);
            product.setId(id);
            productRepo.put(id, product);
            return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
            
        }
        else{
            return new ResponseEntity<>("data id doesn't exists", HttpStatus.OK);
        }
        
        
    }
        
    // create data
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        
        if (!productRepo.containsKey(product.getId())){
            
            productRepo.put(product.getId(), product);
            return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
    }
    else{
         return new ResponseEntity<>("id already exists, please enter another id", HttpStatus.OK);
            
    }
}
        
    //running programs
    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
    
    
}
