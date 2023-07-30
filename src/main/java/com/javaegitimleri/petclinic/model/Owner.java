package com.javaegitimleri.petclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="t_owner")
public class Owner{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "petClinicSeqGen")
	@SequenceGenerator(name ="petClinicSeqGen", sequenceName = "petclinic_sequence" )
	private Long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
//	@OneToMany(targetEntity=Pet.class, mappedBy="owner", fetch=FetchType.EAGER)
//	private Set<Pet> pets = new HashSet<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
//	public Set<Pet> getPets() {
//		return pets;
//	}
//	public void setPets(Set<Pet> pets) {
//		this.pets = pets;
//	}
	
	
	

}