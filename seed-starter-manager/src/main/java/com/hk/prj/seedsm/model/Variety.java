package com.hk.prj.seedsm.model;

import javax.persistence.Entity;

import lombok.Data;


@Data
@Entity
public class Variety extends BaseEntity{

	private String name;
	
}
