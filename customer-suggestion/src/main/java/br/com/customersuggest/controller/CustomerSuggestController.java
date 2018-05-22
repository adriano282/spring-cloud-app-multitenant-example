package br.com.customersuggest.controller;

import br.com.customersuggest.service.CustomerDataSuggestService;
import br.com.customersuggest.vo.CustomerSuggestDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/suggests")
public class CustomerSuggestController {


    private final CustomerDataSuggestService customerDataSuggestService;

    @Autowired
    public CustomerSuggestController(CustomerDataSuggestService customerDataSuggestService) {
        this.customerDataSuggestService = customerDataSuggestService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public CustomerSuggestDataVO create(@RequestBody CustomerSuggestDataVO customerSuggestDataVO) {

        customerDataSuggestService.save(customerSuggestDataVO);

        return customerDataSuggestService.findById(customerSuggestDataVO.getDocument());
    }

    @RequestMapping("/{document}")
    public CustomerSuggestDataVO search(@PathVariable("document") Long nit) {
        return customerDataSuggestService.findById(nit);
    }

    @GetMapping("")
    public List<CustomerSuggestDataVO> getAll() {
        return customerDataSuggestService.findAllSuggests();
    }
}
