/**
 * Test
 */
package com.kbf.management.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kbf.management.dto.ProvenderDto;
import com.kbf.management.dto.TransactionDto;
import com.kbf.management.model.Customer;
import com.kbf.management.model.FarmEquipment;
import com.kbf.management.model.FishStock;
import com.kbf.management.model.Investment;
import com.kbf.management.model.Payroll;
import com.kbf.management.model.Probiotic;
import com.kbf.management.model.Provender;
import com.kbf.management.model.Transaction;
import com.kbf.management.repository.CustomerRepository;
import com.kbf.management.repository.FarmEquipmentRepository;
import com.kbf.management.repository.FishStockRepository;
import com.kbf.management.repository.InvestmentRepository;
import com.kbf.management.repository.PayrollRepository;
import com.kbf.management.repository.ProbioticRepository;
import com.kbf.management.repository.ProvenderRepository;
import com.kbf.management.repository.SupplierRepository;
import com.kbf.management.repository.TransactionRepository;
import com.kbf.management.utils.Constants;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

	private final TransactionRepository transactionRepo;
	private final FishStockRepository fishStockRepo;
	private final ProvenderRepository provenderRepo;
	private final PayrollRepository payrollRepo;
	private final ProbioticRepository probioticRepo;
	private final FarmEquipmentRepository equipmentRepo;
	private final CustomerRepository customerRepo;
	private final InvestmentRepository investmentRepo;
	private final InvestmentService investmentService;
	private final ProvenderService provenderService;
	private final PayrollService payrollService;
	private final FarmEquipmentService farmEquipmentService;
	private final ProbioticService probioticService;
	/**
	 * Fetch all transactions
	 */
	public List<Transaction> getAll() {
		return transactionRepo.findAll();
	}

	/**
	 * Fetch all transactions by range for display
	 * 
	 * @param months
	 * @return
	 */
	public List<Transaction> getTransactionsFromLastMonths(int months) {
		String interval = months + " months"; // ✅ create valid interval string
		return transactionRepo.findTransactionsFromInterval(interval);
	}

	/**
	 * Fetch a single transaction by ID
	 */
	public Transaction getById(Long id) {
		return transactionRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + id));
	}

	/**
	 * Create a new transaction and apply its business logic
	 */
	@Transactional
	public Transaction createTransaction(TransactionDto dto) {
		Transaction tx = mapDtoToEntity(new Transaction(), dto);
		Transaction saved = transactionRepo.save(tx);
		applyUpdate(saved);
		return saved;
	}

	/**
	 * Update an existing transaction and reapply business logic
	 */
	@Transactional
	public Transaction updateTransaction(Long id, TransactionDto dto) {
		Transaction existing = getById(id);
		// txRevert(existing);
		Transaction mapped = mapDtoToEntity(existing, dto);
		Transaction saved = transactionRepo.save(mapped);
		applyUpdate(saved);
		return saved;
	}

	/**
	 * Delete a transaction
	 */
	@Transactional
	public void deleteTransaction(Long id) {
		Transaction tx = getById(id);
		txRevert(tx);
		transactionRepo.delete(tx);
	}

	// --- Internal Helpers ---

	/**
	 * Map DTO fields & associations onto a transaction entity
	 */
	private Transaction mapDtoToEntity(Transaction tx, TransactionDto dto) {

		tx.setAmount(dto.getAmount());
		tx.setDescription(dto.getDescription());
		tx.setDate(dto.getDate() != null ? dto.getDate() : LocalDate.now());
		

		// Clear previous associations
		// tx.setCatType(null);
		tx.setFishStock(null);
		tx.setProvender(null);
		tx.setPayroll(null);
		tx.setProbiotic(null);
		tx.setFarmEquipment(null);
		tx.setCustomer(null);

		// Wire correct association
		switch (dto.getOperationType()) {
		case FISH_STOCK -> {
			// check if fish stock and customer id was passed first . Use for sales 
			Customer customer = customerRepo.findById(dto.getCustomerId())
					.orElseThrow(() -> new IllegalArgumentException("Customer not found: " + dto.getCustomerId()));
			tx.setCustomer(customer);
            	 
			FishStock stock = fishStockRepo.findById(dto.getFishStockId())
					.orElseThrow(() -> new IllegalArgumentException("FishStock not found: " + dto.getFishStockId()));
			stock.setQtySold(stock.getQtySold() + dto.getSoldQty());
			stock.setStockRemaining(stock.getStockRemaining() - (dto.getSoldQty()));
			stock.setUnitPriceSold(dto.getUnitPrice());
			stock.setSoldOut(stock.getStockRemaining()<= Constants.ZERO ?true:false);
			tx.setFishStock(stock);
			tx.setTransType(Constants.INCOME);
			tx.setCategoryType(Constants.SALES);
			tx.setReference(dto.getOperationType().toString().toLowerCase().concat(" ").concat(stock.getBatch()));
			// tx.setDescription(dto.getOperationType().toString());
			break;
		}
		case PROVENDER -> {
			// create provender here
			Provender prov = provenderService.createFromTransaction(dto.getProvenderDto());
			// check to ensure provender was created and saved. may not be needed
			Provender p = provenderRepo.findById(prov.getProvenderId())
					.orElseThrow(() -> new IllegalArgumentException("Provender not found: " + dto.getProvenderId()));
			tx.setProvender(p);
			tx.setTransType(Constants.EXPENSE);
			tx.setCategoryType(Constants.PRODUCTION);
			break;
		}
		case PAYROLL -> {
			// Create payroll on transaction 
			Payroll payTransaction = payrollService.createFromTransaction(dto.getPayrollDto());
			
			Payroll payroll = payrollRepo.findById(payTransaction.getPayId())
					.orElseThrow(() -> new IllegalArgumentException("Payroll not found: " + dto.getPayrollId()));
			tx.setPayroll(payroll);
			tx.setTransType(Constants.EXPENSE);
			tx.setCategoryType(Constants.LABOR);
			break;
		}
		case PROBIOTIC -> {
			
			Probiotic pbTransaction = probioticService.createFromTransaction(dto.getProbioticDto());
			
			Probiotic pa = probioticRepo.findById(pbTransaction.getProbioticId()).orElseThrow(
					() -> new IllegalArgumentException("ProbioticApplication not found: " + dto.getProbioticId()));
			tx.setProbiotic(pa);
			tx.setTransType(Constants.EXPENSE);
			tx.setCategoryType(Constants.PRODUCTION);
			break;
		}
		case FARM_EQUIPMENT -> {
			
			FarmEquipment eqTransaction = farmEquipmentService.createfromTransaction(dto.getFarmEquipmentDto());
			tx.setFarmEquipment(eqTransaction);
			
			/**
			 *  if equipment should be amortized over time or cost more than operating
				 ceiling then set expense as capital
				 if transaction is income from investor then set as capital to offset the
			 */
			String transactionType = eqTransaction.getCost() > 25000 ? Constants.CAPITAL : Constants.EXPENSE;
			tx.setTransType(transactionType);
			String category = eqTransaction.getCost() > 25000 ? Constants.CAPITAL : Constants.OPERATING;
			tx.setCategoryType(category);
			
			//
			// do not include in monthly expense. Should create as CAPITAL
			break;
		}
		/**
		 * "Costs associated with purchasing equipment, machinery, or buildings that are expected to last for more than one year.
		 */
		case INVESTMENT -> {
			//should be used to inject cash flow for operation ?
			Investment invTransaction = investmentService.createFromTransaction(dto.getInvestmentDto());
//			Investment inv = investmentRepo.findById(dto.getInvestmentId())
//					.orElseThrow(() -> new IllegalArgumentException("Investment not found: " + dto.getInvestmentId()));
			tx.setInvestment(invTransaction);
			tx.setTransType(Constants.CAPITAL);
			tx.setCategoryType(Constants.CAPITAL);
			break;

		}
        /**
         * Costs related to running the farm business, such as utilities, repairs, insurance, and taxes.
         */
		case OTHER -> {
			// case other/misc should be all expenses not tracked specifically . e.g
			// transport, fuel , choco etc..
			//create a ui page for crud of other expenses
		    tx.setDescription("[Misc: " + dto.getOperationType() + "] " + tx.getDescription());
		    tx.setTransType(Constants.OPERATING);
			tx.setCategoryType(Constants.OTHER);
			tx.setCategoryType(dto.getOperationType().toString());
			//
			// tx.setReference(dto.getOperationType().toString().toLowerCase().concat("
			// ").concat(stock.getBatch()));

			// if equipment should be amortized over time or cost more than operating
			// ceiling then set expense as capital
			// if transaction is income from investor then set as capital to offset the
			// expense
			// do not include in monthly expense. Should create as CAPITAL
		}
		default -> throw new IllegalArgumentException("Unexpected value: " + tx.getOperationType());
		}

		return tx;
	}

	/**
	 * TODO Revert any business logic effects before updating or deleting
	 */
	private void txRevert(Transaction tx) {
		switch (tx.getOperationType()) {
		case FISH_STOCK -> {
			FishStock stock = tx.getFishStock();
			// stock.setTotalStock(stock.getTotalStock() + stock.getQtyReturned());
			fishStockRepo.save(stock);
		}
		case PROVENDER -> {
			Provender p = tx.getProvender();
			// p.
			// setQuantity(p.getQuantity() - tx.getAmount().intValue());
			provenderRepo.save(p);
		}
		case PAYROLL -> {
			// perhaps nullify payment date or handle reversal logic
		}
		case FARM_EQUIPMENT -> {
			FarmEquipment eq = tx.getFarmEquipment();
			eq.setStatus("Available");
			equipmentRepo.save(eq);
		}
		default -> {
			throw new IllegalArgumentException("Unexpected value: " + tx.getOperationType());
		}
		}
	}

	/**
	 * TODO Apply domain updates based on transaction type
	 */
	private void applyUpdate(Transaction tx) {
		switch (tx.getOperationType()) {
		case FISH_STOCK -> {
			FishStock stock = tx.getFishStock();
			// stock.setQuantity(stock.getQuantity() + tx.getAmount().intValue());
			fishStockRepo.save(stock);
		}
		case PROVENDER -> {
			Provender p = tx.getProvender();
			// p.setQuantity(p.getQuantity() + tx.getAmount().intValue());
			provenderRepo.save(p);
		}
		case PAYROLL -> {
			Payroll payroll = tx.getPayroll();
			payroll.setPaymentDate(LocalDate.now());
			payrollRepo.save(payroll);
		}
		case PROBIOTIC -> {
			// no additional state change
		}
		case FARM_EQUIPMENT -> {
			FarmEquipment eq = tx.getFarmEquipment();
			eq.setStatus("In Use");
			equipmentRepo.save(eq);
		}
		case INVESTMENT -> throw new UnsupportedOperationException("Unimplemented case: " + tx.getOperationType());
		case OTHER -> throw new UnsupportedOperationException("Unimplemented case: " + tx.getOperationType());
		default -> throw new IllegalArgumentException("Unexpected value: " + tx.getOperationType());
		}
	}
}
