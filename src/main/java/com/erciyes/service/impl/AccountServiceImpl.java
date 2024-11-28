package com.erciyes.service.impl;

import com.erciyes.dto.DtoAccount;
import com.erciyes.dto.DtoAddress;
import com.erciyes.mapper.AccountMapper;
import com.erciyes.mapper.AddressMapper;
import com.erciyes.model.Account;
import com.erciyes.model.Address;
import com.erciyes.model.User;
import com.erciyes.repository.AccountRepository;
import com.erciyes.repository.AddressRepository;
import com.erciyes.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountMapper accountMapper;


    @Override
    public DtoAccount createAccount(Account account) {
        accountRepository.save(account);
        DtoAccount dtoAccount = accountMapper.toDto(account);
        return dtoAccount;
    }

    @Override
    public List<DtoAccount> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        List<DtoAccount> dtoAccounts = new ArrayList<>();
        for (Account account : accounts) {
            DtoAccount dtoAccount = accountMapper.toDto(account);
            dtoAccounts.add(dtoAccount);
        }
        return dtoAccounts;
    }


    @Override
    public DtoAccount getAccountById(Long id) {
        Optional<Account> optional = accountRepository.findById(id);
        if (optional.isPresent()){
            DtoAccount dtoAccount =accountMapper.toDto(optional.get());
            return dtoAccount;
        }
        return null;
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);

    }

    @Override
    public DtoAccount updateHairdresser(Long id, Account account) {
        Optional<Account> optional=accountRepository.findById(id);
        if (optional.isPresent()){
            account.setId(id);
            Account dbAccount = accountRepository.save(account);
            return accountMapper.toDto(dbAccount);
        }
        return null;
    }
}

