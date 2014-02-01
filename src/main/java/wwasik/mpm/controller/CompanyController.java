package wwasik.mpm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wwasik.mpm.model.Company;

/**
 * @author Wojtek
 */
@RestController
public class CompanyController {

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public void create(@RequestParam(required = true) Company company) {
        
    }
}
