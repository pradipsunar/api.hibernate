package api.hibernate;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class MyRestController {

	@Autowired
	CustomerRepository customerRespository;
	
	@GetMapping("/customers")  //Get,/api/customers   end point
	public List<Customer> getAllCustomers(){
		return customerRespository.findAll();
	}
	
	@PostMapping("/customers")
	public Customer createUser(@RequestBody Customer customer) {
		return this.customerRespository.save(customer);
	}
	
	@GetMapping("/customers/{id}")  //Get,/api/customers   end point
	public Customer fetchEmployee(@PathVariable long id ){
		Optional optional= customerRespository.findById(id);
		if(optional.isPresent()){
			Customer customer=(Customer) optional.get();
			return customer;
		}
		return null;
		
	}
	
	@DeleteMapping("/customers/{id}")  
	public ResponseDTO deleteCustomer(@PathVariable long id){
		customerRespository.deleteById(id);
		ResponseDTO responseDTO=new ResponseDTO();
		responseDTO.setMessage("deleted succeessfully");
		responseDTO.setStatus(200);
		return responseDTO;
	}
	@PutMapping("/customers/{id}")  
	public ResponseDTO updateCustomer(@PathVariable long id,@RequestBody Customer customer){
		
		customer.setId(id);
		customerRespository.save(customer);		
		
		ResponseDTO responseDTO=new ResponseDTO();
		responseDTO.setMessage("updated succeessfully");
		responseDTO.setStatus(200);
		return responseDTO;
	}
}
	
