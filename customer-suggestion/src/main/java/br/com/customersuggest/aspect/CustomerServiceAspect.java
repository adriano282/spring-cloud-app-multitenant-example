package br.com.customersuggest.aspect;

import br.com.customersuggest.service.CustomerDataSuggestService;
import br.com.customersuggest.store.StoreContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomerServiceAspect {

    private Logger log = LoggerFactory.getLogger(CustomerServiceAspect.class);

    @Before("execution(* br.com.customersuggest.service.CustomerDataSuggestService.*(..)) " +
            "&& target(customerSuggestService)")
    public void aroundExecution(JoinPoint pjp, CustomerDataSuggestService customerSuggestService) throws Throwable {

        org.hibernate.Filter filter =
                customerSuggestService
                        .entityManager
                        .unwrap(Session.class)
                        .enableFilter("storeFilter")
                        .setParameter("storeId",
                                StoreContextHolder
                                        .getContext()
                                        .getStoreId());
        filter.validate();
        log.info("The {} filter is enabled. StoreId applied: {}", "storeFilter", StoreContextHolder.getContext().getStoreId());
    }
}
