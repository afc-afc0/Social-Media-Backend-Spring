package com.afc.springreact.error;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.function.ServerRequest;

@RestController
public class ErrorHandler implements ErrorController{
    
    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    ApiError handleError(ServerRequest serverRequest){
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(serverRequest, true);
        String message = (String)attributes.get("message");
    }


    public String getErrorPath(){
        return "/error";
    }
}
