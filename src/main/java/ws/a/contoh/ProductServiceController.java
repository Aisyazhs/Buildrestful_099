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
        Product honey = new Product(); //membuat new product dan memanggil file Product.java
        honey.setId("1"); // menambahkan id
        honey.setName ("Honey");// menambahkan nama
        productRepo.put(honey.getId(), honey); //memasukkan product pada Hashmap
        
        Product almond = new Product(); //membuat new product dan memanggil file Product.java
        almond.setId("2"); //menambahkan id
        almond.setName("Almond"); //menambahkan nama
        productRepo.put(almond.getId(), almond); //memasukkan product pada Hashmap
        
        Product milk = new Product();//membuat new product dan memanggil file Product.java
        almond.setId("3");//menambahkan id
        almond.setName("milk"); //menambahkan nama
        productRepo.put(milk.getId(), milk); //memasukkan product pada Hashmap
        
        
        
        
    }
    
    //Delete data
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete (@PathVariable("id") String id){
        productRepo.remove(id);//hapus data
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);//Popup berhasil dihapus
    }
    
    //edit data
    @RequestMapping(value = "/products/(id)", method =RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable ("Id") String id, @RequestBody Product product){
        
        if(productRepo.containsKey(id)){
            
            productRepo.remove(id); //membuat variabel hapus 
            product.setId(id);//set new id
            productRepo.put(id, product);//menambahkan data ke Hashmap
            return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);//Popup berhasil diedit/ubah
            
        }
        else{
            return new ResponseEntity<>("data id doesn't exists", HttpStatus.OK);//Popup id tidak ditemukan
        }
        
        
    }
        
    // create data
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        
        if (!productRepo.containsKey(product.getId())){
            
            productRepo.put(product.getId(), product);
            return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);//Popup berhasil
    }
    else{
         return new ResponseEntity<>("id already exists, please enter another id", HttpStatus.OK);//menampilkan pesan id sudah digunakan didata lain
            
    }
}
        
    //running programs
    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
    
    
}
