
package com.spring.mvc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
	@Autowired
	SessionFactory sessionfactory;
	
	@RequestMapping(value = "/")
	public String getIndex() {
		return "index";
	}
	@RequestMapping(value="/addworker")
	public String showAddWorker()
	{
		return "AddWorker";
	}
	@RequestMapping(value = "/add")
	@Transactional
	public ModelAndView add(@ModelAttribute Worker w,BindingResult error) {
		if(!error.hasErrors())
		{
			Session session = sessionfactory.openSession();
			session.save(w);
		ModelAndView m=new ModelAndView();
		m.setViewName("AddWorker");
		m.addObject("status",w.getName());
		return m;
		}
		else
		{
			ModelAndView m=new ModelAndView();
			m.setViewName("AddWorker");
			m.addObject("status","Un-Success");
			return m;
		}
			
	}
}
