package com.moon.dlog.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.moon.dlog.domain.Company;
import com.moon.dlog.mapper.CompanyMapper;
import com.moon.dlog.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-25 21:33
 **/
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public int saveCompany(Company company) {
        company.setCreateTime(new Date());
        return companyMapper.addCompany(company);
    }

    @Override
    public List<Company> getCompanyList(Company company) {
        return companyMapper.selectCompanyList(company);
    }

    @Override
    public int changeStatus(Company company) {
        company.setUpdateTime(new Date());
        return companyMapper.updateCompany(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyMapper.selectCompanyById(id);
    }

    @Override
    public int getAllPostCount(Company company) {
        return companyMapper.selectAllPostCount(company);
    }

    @Override
    public int getOfferCount(Company company) {
        return companyMapper.selectOfferCount(company);
    }

    @Override
    public List<Integer> getPostStatusMap() {
        List<Integer> list = new ArrayList<>();
        Company company = new Company();
        company.setStatus("0");
        list.add(companyMapper.selectPostStatusCount(company));
        company.setStatus("1");
        list.add(companyMapper.selectPostStatusCount(company));
        company.setStatus("2");
        list.add(companyMapper.selectPostStatusCount(company));
        company.setStatus("3");
        list.add(companyMapper.selectPostStatusCount(company));
        company.setStatus("4");
        list.add(companyMapper.selectPostStatusCount(company));
        company.setStatus("5");
        list.add(companyMapper.selectPostStatusCount(company));
        company.setStatus("6");
        list.add(companyMapper.selectPostStatusCount(company));
        return list;
    }
}
