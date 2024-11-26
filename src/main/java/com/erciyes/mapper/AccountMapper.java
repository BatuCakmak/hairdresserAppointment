package com.erciyes.mapper;

import com.erciyes.dto.DtoAccount;
import com.erciyes.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public DtoAccount toDto(Account account) {
        if (account == null) {return null;}
        DtoAccount dtoAccount = new DtoAccount();
        dtoAccount.setAccountNo(account.getAccountNo());
        dtoAccount.setAmount(account.getAmount());
        return dtoAccount;
    }
}
