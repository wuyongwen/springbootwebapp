package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.chanjar.weixin.mp.bean.WxMpXmlMessage;

@Controller
public class IndexController {
    @RequestMapping("/")
    String index(){
        return "index";
    }
    @RequestMapping("/login")
    String login(){
    	return "login";
    }
    @RequestMapping("/wxmsg")
    @ResponseBody
    WxMpXmlMessage wxmsg(){
    	return new WxMpXmlMessage();
    }
}
