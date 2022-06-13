package nl.bioinf.recipespaces.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.HashMap;

@RestController
public class TestController {

    @PostMapping(value = "/test")
    public String test(@RequestBody String test){
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, String>>(){}.getType();
        HashMap<String, String> res = gson.fromJson(test, type);
        System.out.println(res);
        return test;
    }
}
