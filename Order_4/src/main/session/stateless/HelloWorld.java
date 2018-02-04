package main.session.stateless;

import javax.ejb.Remote;

@Remote
public interface HelloWorld {
	
	String sayHello(String name);
}
