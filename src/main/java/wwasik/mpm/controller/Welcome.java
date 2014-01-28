package wwasik.mpm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wojtek
 */
@RestController
public class Welcome {

    @RequestMapping("/")
    public String index() {
        return "Hello World!";
    }
}
