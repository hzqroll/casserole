package com.roll.casserole.web;

import com.roll.casserole.nio.scalable.reactorcase.CaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author roll
 * created on 2019-11-06 21:10
 */
@RestController()
@RequestMapping(value = "/nio")
public class NioClientController {

    @Autowired
    private CaseClient caseClient;

    @RequestMapping(value = "/hello/{message}", method = RequestMethod.GET)
    public String hello(@PathVariable("message") String message) throws IOException {
        return caseClient.client(message);
    }
}
