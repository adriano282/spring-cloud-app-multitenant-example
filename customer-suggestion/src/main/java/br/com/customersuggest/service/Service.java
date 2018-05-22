package br.com.customersuggest.service;

import br.com.customersuggest.model.CustomerSuggestData;
import br.com.customersuggest.vo.CustomerSuggestDataVO;

import java.util.List;

public interface Service {
    void save(CustomerSuggestDataVO customerVO);
    CustomerSuggestDataVO findById(Long nit);
    List<CustomerSuggestData> findAll();

}
