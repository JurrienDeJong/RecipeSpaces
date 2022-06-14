package nl.bioinf.recipespaces.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nl.bioinf.recipespaces.MDS.UnitConverter;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @PostMapping(value = "/test")
    public String test(@RequestBody String test){
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, String>>(){}.getType();
        HashMap<String, String> res = gson.fromJson(test, type);
        System.out.println(res);
        for (Map.Entry<String,String> entry : res.entrySet()){
            Double unitValue = Double.parseDouble(entry.getValue().split(" ")[0]);
            String unitString = entry.getValue().split(" ")[1];
            System.out.println(UnitConverter.convertUnit(unitString, unitValue));
        }
        return test;
    }
}
