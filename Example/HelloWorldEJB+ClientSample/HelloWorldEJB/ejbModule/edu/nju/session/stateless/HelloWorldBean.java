package edu.nju.session.stateless;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class HelloWorldBean
 */
@Stateless
public class HelloWorldBean implements HelloWorld {

    /**
     * Default constructor. 
     */
    public HelloWorldBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String SayHello(String name) {
		// TODO Auto-generated method stub
		return name+ ", Hello World.  Welcome to EJB3!";

	}

}
