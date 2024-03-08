package com.vachado.accounts.service;

import com.vachado.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto - CustomerDto object
     */
    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param email - Input Email
     * @return Account details based on a given mobileNumber
     */
    CustomerDto fetchAccount(String email);

    /**
     *
     * @param customerDto - CustomerDto Object
     * @return - boolean indicating if the update of Account Details is successful or not
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param email - Input Email
     * @return boolean indicating if the delete of Account details is successful or not
     */
    boolean deleteAccount(String email);
}
