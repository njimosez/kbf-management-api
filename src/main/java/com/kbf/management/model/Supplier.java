package com.kbf.management.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "supplier")
public class Supplier {
	@Id
	@GeneratedValue
	private Long supplierId;
	
	/** Supplier name */
    @Column(nullable = false)
    private String name;

    /** Contact person or department */
    private String contact;

    /** Physical address */
    private String address;

    /** Email address */
    private String email;

    /** Phone number */
    private String phone;



	@OneToMany(mappedBy = "supplier")
	private List<Provender> provenders;

	@OneToMany(mappedBy = "supplier")
	private List<Probiotic> probiotics;

}
