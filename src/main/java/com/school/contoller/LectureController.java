package com.school.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LectureController {
    @RequestMapping("/education")
    public String index() {
        return "levels/level0/lecture";
    }

}
