package wwasik.mpm.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Wojtek
 */
@RestController
public class Welcome {

    @RequestMapping("/abc")
    public String index() {
        return "Hello World!";
    }
}
