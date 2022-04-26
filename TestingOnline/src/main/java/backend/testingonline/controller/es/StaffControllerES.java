package backend.testingonline.controller.es;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.testingonline.elasticsearch.indices.IndexStaff;
import backend.testingonline.elasticsearch.service.StaffService;

//@RestController
//@RequestMapping(path = "/es/staff")
public class StaffControllerES {
	
//	@Autowired
//	private StaffService service;
	
//	@GetMapping("/{id}")
//	public IndexStaff findById(@PathVariable("id") Integer id) {
//		return service.findById(id);
//	}

}
