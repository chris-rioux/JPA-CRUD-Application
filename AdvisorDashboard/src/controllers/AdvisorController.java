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
import entities.Location;
import entities.Position;

@Controller
@SessionAttributes("user")
public class AdvisorController {
	@Autowired
	private AdvisorDBDAO advisorDAO;
	
	@ModelAttribute("user")
	   public Advisor getUser()
	   {
	       Advisor user = new Advisor();
	       return user;
	   }
	
	
	@RequestMapping(path="Login.do")
	public ModelAndView login(Model model, @ModelAttribute ("user") Advisor user, String name, String password) {
		ModelAndView mv = new ModelAndView();

		
		if (advisorDAO.login(name, password) ) {
			mv.setViewName("GetAllAdvisors.do");
			model.addAttribute("user", advisorDAO.getAdvisor(name));
			return mv;
		}
		else {
			mv.setViewName("index.jsp");
			mv.addObject("false", false);
			return mv;
		}
	}
	
	@RequestMapping(path="Logout.do")
	public ModelAndView logout() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index.jsp");
		mv.addObject("user", new Advisor());
		return mv;
	}
	
	@RequestMapping(path="GoToAdvisor.do", method=RequestMethod.GET)
	public ModelAndView getAdvisor() {
		return new ModelAndView("advisor.jsp");
	}

	@RequestMapping(path="GetAdvisor.do", method=RequestMethod.GET)
	public ModelAndView getAdvisor(int id) {
		// get the advisor from the table by id and send to advisor.jsp
		Advisor a = advisorDAO.getAdvisor(id);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("advisor.jsp");
		mv.addObject("advisor", a);
		
		// get the sales data for the advisor
//		mv.addObject("fundSales", advisorDAO.getAdvisorSales(id));
	
		return mv;
	}
	
	@RequestMapping(path="GetAllAdvisors.do")
	public ModelAndView getAllAdvisors() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("advisorTable.jsp");
		mv.addObject("advisors", advisorDAO.getAllAdvisors());
		return mv;
	}
	
	@RequestMapping(path="GoToAddAdvisor.do", method=RequestMethod.GET)
	public ModelAndView goToAddAdvisor() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addAdvisor.jsp");
		
		// add fillmurray picture
		long hgt = Math.round((100 * (1 + (int)(Math.random() * ((4 - 1) + 1)))));
		long wth = Math.round((100 * (1 + (int)(Math.random() * ((4 - 1) + 1)))));
		String url = "http://fillmurray.com/" + hgt + "/" + wth;
		mv.addObject("url", url);
		
		// add position and location information to populate dropdown selections
		mv.addObject("positions", advisorDAO.getAllPositions());
		mv.addObject("locations", advisorDAO.getAllLocations());
		return mv;
	}
	
	@RequestMapping(path="AddAdvisor.do")
	public ModelAndView addAdvisor(String name, Integer salary, String password, int position, int location) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("advisorTable.jsp");		
		advisorDAO.addAdvisor(name, salary, password, position, location);
		mv.addObject("advisors", advisorDAO.getAllAdvisors());
		return mv;
	}

	@RequestMapping(path="GoToUpdateAdvisor.do", method=RequestMethod.GET)
	public ModelAndView updateAdvisor(int id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updateAdvisor.jsp");
		
		// add fillmurray picture
		long hgt = Math.round((100 * (1 + (int)(Math.random() * ((4 - 1) + 1)))));
		long wth = Math.round((100 * (1 + (int)(Math.random() * ((4 - 1) + 1)))));
		String url = "http://fillmurray.com/" + hgt + "/" + wth;
		mv.addObject("url", url);
		
		// send current advisor to update page
		Advisor a = advisorDAO.getAdvisor(id);
		mv.addObject("advisor", a);
		
		// add position and location information to populate dropdown selections
		mv.addObject("positions", advisorDAO.getAllPositions());
		mv.addObject("locations", advisorDAO.getAllLocations());
		return mv;
	}

	@RequestMapping(path="UpdateAdvisor.do", method=RequestMethod.GET)
	public ModelAndView updateAdvisor(Integer id, Advisor a) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("advisor.jsp");		
		advisorDAO.updateAdvisor(id, a);		
		mv.addObject("advisor", a);
		System.out.println("Controller: " + a.getId() + " " + a.getName() + " " + a.getSalary() + " " + a.getPassword());
		return mv;
	}
	
	@RequestMapping(path="DeleteAdvisor.do", method=RequestMethod.POST)
	public ModelAndView deleteAdvisor(int id) {
		//execute deletion
		advisorDAO.deleteAdvisor(id);
		
		// return user to advisorTable page with all advisor data
		ModelAndView mv = new ModelAndView();
		mv.addObject("advisors", advisorDAO.getAllAdvisors());
		mv.setViewName("advisorTable.jsp");
		return mv;
	}

}