package main.controller;

public class Redirect {
	
	protected static String redirectToLogin(){
		return "redirect:/Login";
	}
	
	protected static String redirectToShowMyOrder(){
		return "redirect:/ShowMyOrder";
	}
}
