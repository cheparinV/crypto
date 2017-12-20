package com.univer.repo.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 * @version $Id$
 * @since 1.0
 */
@RestController
@RequestMapping("/crypto/auth")
public class AuthResource {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String startLearn() {
        return "OK";
    }

}
