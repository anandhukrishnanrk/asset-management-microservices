package com.cis.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "pts", name = "pmo_region_setup", catalog = "pts")
public class ResourceRegionSetup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "region_type")
	public String regionType;
	
	@Column(name = "place")
	public String place;
	
	@Column(name = "remarks")
	public String remarks;
	
	@Column(name = "delete_Status")
	public String deleteStatus="N";
	
}
