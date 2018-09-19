package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	@RequestMapping("/viral")
	public String index() {
		return "viral";
	}
	
	@RequestMapping("/challenge")
	public String challenge(@RequestParam(value="name")String name, Model model) {
		model.addAttribute("name",name);
		return "challenge";
	}
	
	@RequestMapping(value = {"/challenge/{name}"})
	public String challengePath(@PathVariable Optional<String> name, Model model) {
		model.addAttribute("name", name);
		return "challenge";
	}
	
	@RequestMapping("/generator")
	public String generator(@RequestParam(value="a",defaultValue="0") int valA, @RequestParam( value="b",defaultValue="0") int valB, Model model) {
		model.addAttribute("valA", valA);
		model.addAttribute("valB", valB);
		String resGen ="hm";
		if(valA < 2) {// 'm' hanya 1
			if(valB < 2) {// 'hm' spasi ga lebih dari 1
				model.addAttribute("resGen", resGen);
			}else { // 'hm' spasinya banyak
				model.addAttribute("resGen", spasi(resGen, valB));
			}
				
		} else { // 'm' banyak
			if(valB < 2) // 'm' banyak spasi 0
				model.addAttribute("resGen", m(resGen,valA));
			else {// 'm' banyak spasi banyak
				model.addAttribute("resGen", spasi(m(resGen, valA), valB));
			}
		}
		return "generator";
	}
	
	//spasi generator
	public String spasi(String resGen, int valB) {
		String hm = resGen; 
		for(int i = 1; i < valB; i++) {
			resGen += " "+ hm;
		}
		return resGen;
	}
	
	// m generator
	public String m(String resGen, int valA) {
		for(int i = 1; i<valA; i++) {
			resGen +="m";
		}
		return resGen;
	}
}
