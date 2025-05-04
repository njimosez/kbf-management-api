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
@Table(name = "personnel")
public class Personnel {
    @Id @GeneratedValue
    private Long personnelId;
    
    /** Full name of the staff member */
    @Column(nullable = false)
    private String name;

    /** Role or position (e.g., Manager, Technician) */
    private String role;

    /** Contact information (email or phone) */
    private String contact;

    /** Work shift (e.g., Morning, Night) */
    private String shift;

    /** Personnel status (e.g., probation, permanent) */
    private String status;

    /** Flag indicating if the personnel is currently active */
    @Column(nullable = false)
    private boolean isActive;

    /** Basic salary for the personnel */
    @Column(nullable = false)
    private double salary;

    /** Savings deducted or accrued */
    @Column(nullable = false)
    private double savings;

    /** Net perceived pay after deductions/additions */
    @Column(nullable = false)
    private double netPerceived;
    
    /** All payroll records for this employee.  */
    @OneToMany( mappedBy = "personnel")
    private List<Payroll> payrolls;
    
}
