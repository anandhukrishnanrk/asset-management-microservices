package com.cis.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "resource_responsibility_setup", schema = "pts", catalog = "pts")
public class ResponsibilitySetup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = Rolesetup.class)
	@JoinColumn(name = "role_Id")
	public Rolesetup roleSetup;
	
	@Column(name = "responsibility")
	public String responsibility;
	
	@Column(name = "delete_Status")
	public String deleteStatus;
	
}