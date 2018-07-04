package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/index")
    public String index() {
        return "demo/index";
    }

    @RequestMapping("/data")
    @ResponseBody
    public Map data() {
        Map map = new HashMap();
        map.put("key1", "value11");
        map.put("key2", "value22");
        return map;
    }
}
