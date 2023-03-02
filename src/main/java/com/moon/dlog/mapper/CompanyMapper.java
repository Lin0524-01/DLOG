package com.moon.dlog.mapper;

import com.moon.dlog.domain.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-25 21:33
 **/
@Repository
public interface CompanyMapper {
    int addCompany(Company company);

    List<Company> selectCompanyList(Company company);

    Company selectCompanyById(Long id);

    int updateCompany(Company company);

    int selectAllPostCount(Company company);

    int selectOfferCount(Company company);

    int selectPostStatusCount(Company company);
}
