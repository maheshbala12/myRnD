package com.mydemo;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC)
public class HelloService {
	public String sayHello(String name) {
		return "Hello " + name;
	}
}
