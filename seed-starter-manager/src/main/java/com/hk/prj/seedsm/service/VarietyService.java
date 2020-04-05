package com.hk.prj.seedsm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.prj.seedsm.model.Variety;
import com.hk.prj.seedsm.repo.VarietyRepository;

@Service
public class VarietyService {

	@Autowired
	private VarietyRepository varietyRepository;
	
	public List<Variety> findAll() {
		return this.varietyRepository.findAll();
	}
	public Variety findById(final Integer id) {
		return this.varietyRepository.findById(id);
	}
	public List<Variety> save(List<Variety> entities) {
		return varietyRepository.saveAll(entities);
	}


}
