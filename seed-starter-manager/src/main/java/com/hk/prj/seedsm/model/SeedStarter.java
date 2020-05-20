package com.hk.prj.seedsm.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class SeedStarter extends BaseEntity{

	
	private LocalDateTime datePlanted;
	private Boolean covered;
	private String type;
	private String[] features;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Row> rows= new ArrayList<>();
	
}
