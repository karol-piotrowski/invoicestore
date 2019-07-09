package com.kodilla.invoicestore.repository;

import com.kodilla.invoicestore.domain.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    @Override
    List<Company> findAll();

    @Override
    Company save(Company company);

    @Override
    Optional<Company> findById(Long companyId);

    @Override
    void deleteById(Long companyId);

}
