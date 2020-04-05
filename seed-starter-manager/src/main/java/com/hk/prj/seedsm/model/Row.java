package com.hk.prj.seedsm.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Row extends BaseEntity{

	@ManyToOne
	@JoinColumn(name="variety_id")
	private Variety variety;
	
	private Integer seedsPerCell;
	
}
