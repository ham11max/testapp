package Controller;

import model.RequestFromForm;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.Service;

import java.io.IOException;
import java.sql.SQLException;

@Controller
public class MainController {

	@Autowired
	private Service services;



	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView printWelcome() {
		ModelAndView modelAndView  = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}


	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(value = "key") String key,
							  @RequestParam(value =  "echo") String echo) throws IOException, JSONException {

		ModelAndView model = new ModelAndView();
		RequestFromForm requestFromForm =new RequestFromForm();
		requestFromForm.setKey(key);
		requestFromForm.setMessage(echo);
		try{
			services.postJsonAndGetResponse(requestFromForm);
			model.setViewName("success");
		} catch(JSONException exception){
			model.setViewName("error");
			}
		catch(SQLException exception){
			model.setViewName("error");
		}
		return model;
	}

	@RequestMapping(value ="/send", method = RequestMethod.GET)
	public ModelAndView getLoginSuccessPage(){
		ModelAndView modelAndView =  new ModelAndView();
		modelAndView.setViewName("success");
		return modelAndView;

	}

}