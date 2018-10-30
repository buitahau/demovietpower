package hau.kute.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

/**
 * Created by HauKute on 10/4/2018.
 */
@Controller
public class SortController  {

    @RequestMapping(value = "/")
    public String defaultPage(){
        return "redirect:/sort/test.html";
    }

    @RequestMapping(value = "/sort/test.html", method = RequestMethod.GET)
    public String testPage(){
        return "sort/test";
    }

    @RequestMapping(value = "/sort/bubble.html", method = RequestMethod.GET)
    public String sortBubble(){
        return "sort/bubble";
    }
}
