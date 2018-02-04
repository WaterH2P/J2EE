package main.session.stateless.impl;

import main.session.stateless.HelloWorld;

import javax.ejb.Stateless;

@Stateless
public class HelloWorldBean implements HelloWorld {
	
	@Override
	public String sayHello(String name){
		return "Hello " + name + ", welcome to EJB3!";
	}
}
