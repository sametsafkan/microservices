package org.srvlab.chapter2.boothateoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
@RestController
class GreetingController {
	
	private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);
	
	@RequestMapping("/greet")
	Greet greet(){
		return new Greet("Hello World!");
	}
	
	@RequestMapping("/greeting")
	@ResponseBody
	public HttpEntity<Greet> sayGreeting(@RequestParam(value="name", required=false, defaultValue="World!") String value){
		Greet greet = new Greet(value);
		//add methodu RespourceSupport un bir methodudur.
		//linkTo ve methodOn methodları controller class larında link yaratmaya yarayan ControllerLinkBuilder ın static methodlarıdır.
		//methodOn dummy bir method çağırımı yapar. linkTo ise controller class ında link oluşturur.
		//withSelfRel kendini işaret etmesini sağlar.
		greet.add(linkTo(methodOn(GreetingController.class).sayGreeting(value)).withSelfRel());
		return new ResponseEntity<Greet>(greet, HttpStatus.OK);
	}
}
class Greet extends ResourceSupport{
	String message = "Hello ";
	public Greet() {
		 
	}
	public Greet(String message) {
		this.message += message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}