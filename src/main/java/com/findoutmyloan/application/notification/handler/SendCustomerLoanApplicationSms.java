package com.findoutmyloan.application.notification.handler;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.notification.event.CustomerLoanApplicationEvent;
import com.findoutmyloan.application.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendCustomerLoanApplicationSms {
    private final NotificationService notificationService;

    @EventListener
    public void handleCustomerLoanApplicationEvent(CustomerLoanApplicationEvent event) {
        notificationService.notify(event.getCustomer(),"Your loan application has been received");
    }
}
