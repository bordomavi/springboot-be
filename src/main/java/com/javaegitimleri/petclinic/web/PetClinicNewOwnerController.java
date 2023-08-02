package com.javaegitimleri.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaegitimleri.petclinic.model.Owner;
import com.javaegitimleri.petclinic.service.PetClinicService;

@Controller
public class PetClinicNewOwnerController {
	
	@Autowired
	private PetClinicService petClinicService;
	
	@RequestMapping(value="/owners/new", method = RequestMethod.GET)
	public String newOwner() {
		return "newOwner";
	}

	@ModelAttribute
	public Owner initModel() {
		return new Owner();
	}
	
	@RequestMapping(value="/owners/new", method = RequestMethod.POST)
	public String newOwner(@ModelAttribute @Valid Owner owner, BindingResult bindingResult, RedirectAttributes redirectAttiributes) {
		if(bindingResult.hasErrors()) {
			return "newOwner";
		}
		petClinicService.createOwner(owner);
		redirectAttiributes.addFlashAttribute("message", "Owner created with id : "+ owner.getId());
		return "redirect:/owners";
	}
}
