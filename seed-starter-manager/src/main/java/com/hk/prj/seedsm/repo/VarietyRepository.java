package com.hk.prj.seedsm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hk.prj.seedsm.model.Variety;

public interface VarietyRepository extends JpaRepository<Variety, Long> {

	Variety findById(Integer id);

}
