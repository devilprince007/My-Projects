package com.looser.loans.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.looser.loans.constants.LoansConstants;
import com.looser.loans.dto.LoansDto;
import com.looser.loans.entity.Loans;
import com.looser.loans.exception.LoanAlreadyExistsException;
import com.looser.loans.exception.ResourceNotFoundException;
import com.looser.loans.mapper.LoansMapper;
import com.looser.loans.repository.LoansRepository;
import com.looser.loans.service.ILoanService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoanService{
	
	private LoansRepository repository;

	@Override
	public void createLoan(String mobileNumber) {
		
		Optional<Loans> optionalLoans = repository.findByMobileNumber(mobileNumber);
		if(optionalLoans.isPresent()) {
			throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber: "+mobileNumber);
		}
		repository.save(createNewLoan(mobileNumber));
	}

	@Override
	public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = repository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        return LoansMapper.mapToLoansDto(loans, new LoansDto());
	}

	@Override
	public boolean updateLoan(LoansDto loansDto) {
        Loans loans = repository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loansDto.getLoanNumber()));
        LoansMapper.mapToLoans(loansDto, loans);
        repository.save(loans);
        return  true;
	}

	@Override
	public boolean deleteLoan(String mobileNumber) {
        Loans loans = repository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        repository.deleteById(loans.getLoanId());
        return true;
	}
	
	private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutStandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
	}
}
