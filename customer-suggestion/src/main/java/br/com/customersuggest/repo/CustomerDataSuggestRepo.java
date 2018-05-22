package br.com.customersuggest.repo;

import br.com.customersuggest.model.CustomerSuggestData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDataSuggestRepo extends JpaRepository<CustomerSuggestData, Long> {}
