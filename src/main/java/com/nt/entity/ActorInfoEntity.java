package com.nt.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Table(name="REST_ACTORINFO")
@Data
public class ActorInfoEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer aid;
	@Column(length=30)
	private String aname;
	@Column(length=30)
	private String addrs;
	private Double remuneration;
	@Column(length=10)
	private String active_SW;
	
	//meta data
	@Column(length=30,insertable=true,updatable=false)
	private String createdBy;
	@Column(length=30,insertable=false,updatable=true)
	private String updatedBy;
	@CreationTimestamp
	@Column(updatable=false,insertable=true)
	private LocalDate cretionDate;
	@UpdateTimestamp
	@Column(insertable=false,updatable=true)
	private LocalDate updateDate;
	@Version
	private Integer updationCount;

}
