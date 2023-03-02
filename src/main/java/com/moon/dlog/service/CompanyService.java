package com.moon.dlog.service;

import com.moon.dlog.domain.Company;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-25 21:33
 **/
public interface CompanyService {
    int saveCompany(Company company);

    List<Company> getCompanyList(Company company);

    int changeStatus(Company company);

    Company getCompanyById(Long id);

    int getAllPostCount(Company company);

    int getOfferCount(Company company);

    List<Integer> getPostStatusMap();
}
