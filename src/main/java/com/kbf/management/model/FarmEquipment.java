package com.kbf.management.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a piece of equipment used on the farm (e.g., pumps, feeders, nets, vehicles).
 */

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "equipments")
public class FarmEquipment {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long equipmentId;

	 /** Name of the equipment */
	    @Column(nullable = false)
	    private String name;

	    /** Type or category (e.g., Pump, Aerator) */
	    private String equipmentType;

	    /** Manufacturer of the equipment */    /** Manufacturer of the equipment */
	    private String manufacturer;

	    /** Quantity of this equipment available */
	    @Column(nullable = false)
	    private int qty;

	    /** Date when equipment was purchased */
	    @Column(nullable = false)
	    private LocalDate purchaseDate;

	    /** Cost of purchase */
	    @Column(nullable = false)
	    private double cost;

	    /** Maintenance schedule details */
	    private String maintenanceSchedule;

	    /** Physical location on farm */
	    private String location;

	    /** Current status (e.g., Operational, Under Maintenance) */
	    private String status;

	    /** Purchase transaction record */
	    @OneToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "transactionId")
	    private Transaction purchaseTransaction;
	    
	   

	    @OneToMany(mappedBy = "farmEquipment")
	        private List<MaintenanceRecord> maintenanceRecords;

}
