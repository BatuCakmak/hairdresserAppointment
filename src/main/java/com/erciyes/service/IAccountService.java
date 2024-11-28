package com.erciyes.service;

import com.erciyes.dto.DtoAccount;
import com.erciyes.dto.DtoAddress;
import com.erciyes.dto.DtoHairdresser;
import com.erciyes.model.Account;
import com.erciyes.model.Hairdresser;

import java.util.List;

public interface IAccountService {
    public DtoAccount createAccount(Account account);
    public List<DtoAccount> getAllAccount();
    public DtoAccount getAccountById(Long id);
    public void deleteAccount(Long id);
    public DtoAccount updateHairdresser(Long id , Account account);
}
