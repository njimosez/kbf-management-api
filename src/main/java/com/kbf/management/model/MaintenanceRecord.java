package com.kbf.management.model;

import java.time.LocalDate;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A single maintenance or repair event for a piece of FarmEquipment.
 */

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "maintenance_record")
public class MaintenanceRecord {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long maintenanceId;

	 /** Date when maintenance was performed. */
	    private LocalDate maintenanceDate;

	    /** Description of work done (repairs, parts replaced, etc.). */
	    @Column(columnDefinition = "TEXT")
	    private String description;

	    /** Cost of the maintenance event. */
	    private double cost;
	    
	    /** Person who performed the maintenance */
	    private String performedBy;

	    /** Additional notes or observations */
	    @Column(columnDefinition = "TEXT")
	    private String notes;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "equipmentId", nullable = false)
	    private FarmEquipment farmEquipment;

}
