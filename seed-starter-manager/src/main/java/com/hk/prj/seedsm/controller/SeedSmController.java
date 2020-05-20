package com.hk.prj.seedsm.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.prj.seedsm.model.Row;
import com.hk.prj.seedsm.model.SeedStarter;
import com.hk.prj.seedsm.model.Variety;
import com.hk.prj.seedsm.service.SeedStarterService;
import com.hk.prj.seedsm.service.VarietyService;


@Controller
@PropertySource(ignoreResourceNotFound = true, value = "classpath:messages.properties")
public class SeedSmController {

	@Autowired private VarietyService varietyService;
	@Autowired private SeedStarterService seedStarterService;

	@Value("${seedstarter.feature.SEEDSTARTER_SPECIFIC_SUBSTRATE}")
	private String feature1;
	@Value("${seedstarter.feature.FERTILIZER}")
	private String feature2;
	@Value("${seedstarter.feature.PH_CORRECTOR}")
	private String feature3;		
	
	@ModelAttribute("allTypes")
	public List<String> populateTypes() {
		return Arrays.asList("type1","type2","type3");
	}
	
	@ModelAttribute("allFeatures")
	public List<String> populateFeatures() {
		return Arrays.asList(feature1, feature2, feature3);
	}
	@ModelAttribute("allVarieties")
	public List<Variety> populateVarieties() {
		return this.varietyService.findAll();
	}
	@ModelAttribute("allSeedStarters")
	public List<SeedStarter> populateSeedStarters() {
		return this.seedStarterService.findAll();
	}

	@RequestMapping(path={"/","/seedstartermng"}, method = RequestMethod.GET)
	public String showSeedstarters(final SeedStarter seedStarter) {
	    seedStarter.setDatePlanted(LocalDateTime.now());
	    return "seedstartermng";
	}
	
	@RequestMapping(value="/seedstartermng", params={"save"})
	public String saveSeedstarter(final SeedStarter seedStarter, final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "seedstartermng";
		}
		this.seedStarterService.add(seedStarter);
		model.clear();
		return "redirect:/seedstartermng";
	}
	
	@RequestMapping(value="/seedstartermng", params={"addRow"})
	public String addRow(final SeedStarter seedStarter, final BindingResult bindingResult) {
		seedStarter.getRows().add(new Row());
		return "seedstartermng";
	}
	
	@RequestMapping(value="/seedstartermng", params={"removeRow"})
	public String removeRow(
			final SeedStarter seedStarter, final BindingResult bindingResult,
			final HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
		seedStarter.getRows().remove(rowId.intValue());
		return "seedstartermng";
	}

}
