package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import dao.AdvisorDBDAO;
import entities.Advisor;

@Controller
@SessionAttributes("name")
public class AdvisorController {
	@Autowired
	private AdvisorDBDAO advisorDAO;
	
	@ModelAttribute("name")
	   public String name()
	   {
	       String name = "name";
	       return name;
	   }
	
	
	@RequestMapping(path="Login.do")
	public ModelAndView login(Model model, @ModelAttribute ("name") String userName, String name, String password) {
		ModelAndView mv = new ModelAndView();

		if (advisorDAO.login(name, password) ) {
			mv.setViewName("GetAllAdvisors.do");	
			mv.addObject("userName", name);
			
			model.addAttribute(name);
			
			
			return mv;
		}
		else {
			mv.setViewName("index.jsp");
			mv.addObject("false", false);
			return mv;
		}
	}

	@RequestMapping(path="GoToAdvisor.do", method=RequestMethod.GET)
	public ModelAndView getAdvisor() {
		return new ModelAndView("advisor.jsp");
	}

	@RequestMapping(path="GetAdvisor.do", method=RequestMethod.POST)
	public ModelAndView getAdvisor(int id) {
		Advisor a = advisorDAO.getAdvisor(id);
		return new ModelAndView("advisor.jsp", "advisor", a);
	}
	
	@RequestMapping(path="GetAllAdvisors.do")
	public ModelAndView getAllAdvisors() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("advisorTable.jsp");
		mv.addObject("advisors", advisorDAO.getAllAdvisors());
		return mv;
	}

	@RequestMapping(path="GoToUpdateAdvisor.do", method=RequestMethod.GET)
	public ModelAndView editAdvisor(int id) {
		Advisor a = advisorDAO.getAdvisor(id);
		return new ModelAndView("updateAdvisor.jsp", "advisor", a);
	}

	@RequestMapping(path="UpdateAdvisor.do", method=RequestMethod.POST)
	public ModelAndView editAdvisor(int id, Advisor a) {
		advisorDAO.updateAdvisor(id, a);
		return new ModelAndView("advisor.jsp", "advisor", advisorDAO.getAdvisor(id));
	}

}
