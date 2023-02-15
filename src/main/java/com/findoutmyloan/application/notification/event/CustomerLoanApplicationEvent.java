package com.findoutmyloan.application.notification.event;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.entity.Customer;
import org.springframework.context.ApplicationEvent;

public class CustomerLoanApplicationEvent extends ApplicationEvent {
    private Customer customer;

    public CustomerLoanApplicationEvent(Object source, Customer customer) {
        super(source);
        this.customer=customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
