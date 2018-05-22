package br.com.customersuggest.service;

import br.com.customersuggest.model.CustomerSuggestData;
import br.com.customersuggest.vo.CustomerSuggestDataVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.customersuggest.repo.CustomerDataSuggestRepo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerDataSuggestService {

    @PersistenceContext
    public EntityManager entityManager;

    private final CustomerDataSuggestRepo repo;

    @Autowired
    public CustomerDataSuggestService(CustomerDataSuggestRepo repo) {
        this.repo = repo;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(CustomerSuggestDataVO customerVO) {

        CustomerSuggestData customer = new CustomerSuggestData();

        BeanUtils.copyProperties(customerVO, customer);

        repo.save(customer);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerSuggestDataVO findById(Long nit) {

        CustomerSuggestDataVO customerVO = new CustomerSuggestDataVO();

        CustomerSuggestData customerModel = repo.findById(nit).orElse(null);

        if (customerModel != null)
            BeanUtils.copyProperties(customerModel, customerVO);

        return customerVO;
    }

    @Transactional
    public List<CustomerSuggestDataVO> findAllSuggests() {

        List<CustomerSuggestDataVO> customersVO = new ArrayList<CustomerSuggestDataVO>();

        List<CustomerSuggestData> customerModel = repo.findAll();

        for (CustomerSuggestData s : customerModel) {
            CustomerSuggestDataVO vo = new CustomerSuggestDataVO();
            BeanUtils.copyProperties(s, vo);

            customersVO.add(vo);
        }

        return customersVO;
    }
}
