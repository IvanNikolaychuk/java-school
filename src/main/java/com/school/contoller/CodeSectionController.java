package com.school.contoller;

import com.school.domain.code.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Controller
public class CodeSectionController {
    private static final String ROOT_DIR_PATH = System.getProperty("java.io.tmpdir") + "academy";

    private final ProgramFactory programFactory;
    private final ProgramExecutor programExecutor;

    public CodeSectionController() {
        this.programExecutor = new ProgramExecutor(ROOT_DIR_PATH);
        this.programFactory = new ProgramFactory();
    }

    @RequestMapping("/code")
    public String index() {
        return "code";
    }

    @RequestMapping(value = "/runCode", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> test(@RequestParam String code,
                                                     @RequestParam(required = false) String input) {
        try {
            Program program = programFactory.create(code, input, ROOT_DIR_PATH);
            programExecutor.execute(program);
        } catch (Exception e) {
            return new ResponseEntity<>("Ooops, something is wrong :(", INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Have it!", OK);
    }
}
