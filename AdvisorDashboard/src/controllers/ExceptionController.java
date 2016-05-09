package controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllExceptions(Exception ex) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("error.jsp");
		return mv;
	}
	
}