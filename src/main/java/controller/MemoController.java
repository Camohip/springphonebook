package controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import service.PhonebookServiceInter;
import vo.PhonebookVO;

@Controller
public class MemoController {

	@Autowired
	PhonebookServiceInter service;

	@RequestMapping("/")
	public String index() {

		return "index";
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", service.getList());
		mv.setViewName("list");
		return mv;
	}

	@RequestMapping("/find")
	public ModelAndView list(String search) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", service.find(search));
		mv.setViewName("list");
		return mv;
	}
	// Form 처리시

	@RequestMapping("/insertPrco")
	public String insertProc(PhonebookVO pb) {
		System.out.println(pb.toString());
		// int result = service.insert(pb);
		int result = service.insert(pb);
		return "index";

	}

	// ajax처리시

	@RequestMapping("/insert")
	public ModelAndView insert(PhonebookVO pb) {
		ModelAndView mv = new ModelAndView();
		// 첫번째 작업 입력
		int result = service.insert(pb);
		// 두번째 작업 리스트 요청(페이지로 이동하는 것이 아닌 결과로 돌려주는것)
		mv.addObject("list", service.getList());
		mv.setViewName("list");
		return mv;

	}

	@RequestMapping("/findOne")
	public ModelAndView findOne(int idx) {
		System.out.println(service.findOne(idx).toString());
		System.out.println(idx);
		ModelAndView mv = new ModelAndView();
		mv.addObject("phonebook", service.findOne(idx));
		mv.setViewName("findOne");
		return mv;
	}
	@RequestMapping("/updateProc")
	public String updateProc(PhonebookVO pb) {
		service.update(pb);
		return "index";
		
	}

	@RequestMapping("/updateForm")
	public ModelAndView updateForm(int idx) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", service.findOne(idx));
		mv.setViewName("update");
		return mv;
	}
	@RequestMapping("/deletelist")
	public String delete(int idx) {
		service.delete(idx);
		return "index";
	}
	@RequestMapping("/form")
	public String form() {
		return "form";
	}
	@ResponseBody
	@PostMapping(value = "/formProc")
	public String home(@RequestBody String json) {
		System.out.println(json);
		return json;
	}
	/*
	 * @RequestMapping(value = "/", method = RequestMethod) public String index() {
	 * return "home"; }
	 */
}
