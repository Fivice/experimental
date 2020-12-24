package top.fivice.springbootsample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.fivice.springbootsample.vo.Entity;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wubin
 */
@Controller
public class Index {

    @GetMapping(value = "/testAlias")
    @ResponseBody
    public Entity testAlias(HttpServletRequest request, @RequestBody Entity entity){

        return entity;
    }
}
