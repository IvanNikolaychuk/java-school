package com.school.contoller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.HttpStatus.OK;

@Controller
public class CodeSectionController {

    @RequestMapping("/code")
    public String index() {
        return "code";
    }

    @RequestMapping(value = "/runCode", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> test(@RequestParam String code) {
        System.out.println("Code: " + code);
        return new ResponseEntity<>("Have it!", OK);
    }
}
