package top.fivice.springbootsample.controller.socket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.ServerEndpoint;

/**
 * @author wubin
 */
@ServerEndpoint("/websocket")
public class WebSocket {

    @GetMapping("/index")
    public String index(){
        return "socket/index";
    }
}
