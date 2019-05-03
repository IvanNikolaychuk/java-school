package com.school.contoller;

import com.school.app.CodeRunner;
import com.school.domain.code.program.ExecutionResult;
import org.json.JSONObject;
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

    private final CodeRunner codeRunner;

    public CodeSectionController() {
        this.codeRunner = new CodeRunner();
    }

    @RequestMapping(value = "/runCode", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> run(@RequestParam String code,
                                                     @RequestParam(required = false, defaultValue = "") String programInput) {
        try {
            ExecutionResult result = codeRunner.run(code, programInput);
            return new ResponseEntity<>(new JSONObject(result).toString(), OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ooops, something is wrong :(", INTERNAL_SERVER_ERROR);
        }
    }
}
