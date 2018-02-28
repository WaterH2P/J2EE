package edu.nju.session.client;

import edu.nju.session.factory.EJBFactory;
import edu.nju.session.stateless.HelloWorld;

public class HelloWorldClient {
	public static void main(String[] args) {

		HelloWorld hello = (HelloWorld) EJBFactory.getEJB("ejb:/HelloWorldEJB/HelloWorldBean!edu.nju.session.stateless.HelloWorld");
		System.out.println(hello.SayHello("Mary"));
	}
}
