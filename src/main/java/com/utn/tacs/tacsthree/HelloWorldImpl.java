
package com.utn.tacs.tacsthree;

import javax.jws.WebService;

@WebService(endpointInterface = "com.utn.tacs.tacsthree.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        return "Hello " + text;
    }
}

