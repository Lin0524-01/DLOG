package com.moon.dlog.controller;

import com.alibaba.fastjson.JSONObject;
import com.moon.dlog.domain.Company;
import com.moon.dlog.domain.R;
import com.moon.dlog.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-25 21:32
 **/
@RestController
@CrossOrigin
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/addCompany")
    public R addCompany(@RequestBody Company company){
        int result = companyService.saveCompany(company);
        if (result == 1){
            return R.ok();
        }
        return R.error().message("添加失败！请重试");
    }

    @PostMapping("/list")
    public R list(@RequestBody Company company){
        
        List<Company> companyList = companyService.getCompanyList(company);
        return R.ok().data("list", companyList);
    }

    @PostMapping("/changeStatus")
    public R changeStatus(@RequestBody Company company){
        int result = companyService.changeStatus(company);
        return R.ok();
    }

    @GetMapping("/getCompanyById/{id}")
    public R getCompanyById(@PathVariable("id") Long id){
        Company company =  companyService.getCompanyById(id);
        return R.ok().data("company", company);
    }

    /**
     * 首页统计数据展示
     * @return 投递简历数——postCount  收到offer数——offerCount
     */
    @PostMapping("/getIndexData")
    public R getIndexData(@RequestBody Company company){
        int allPostCount = companyService.getAllPostCount(company);
        int offerCount = companyService.getOfferCount(company);

        return R.ok().data("postCount", allPostCount).data("offerCount", offerCount);
    }

    @GetMapping("/getPostStatusMapToPieShow")
    public R getPostStatusMapToPieShow(){
        List<Integer> list = companyService.getPostStatusMap();
        return R.ok().data("pieData", list);
    }

}
