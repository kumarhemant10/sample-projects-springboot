package com.hk.prj.seedsm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.prj.seedsm.model.SeedStarter;
import com.hk.prj.seedsm.repo.SeedStarterRepo;

@Service
public class SeedStarterService {

	@Autowired
	private SeedStarterRepo seedstarterRepository;

	public List<SeedStarter> findAll() {
		return this.seedstarterRepository.findAll();
	}
	public void add(final SeedStarter seedStarter) {
		this.seedstarterRepository.save(seedStarter);
	}

}
