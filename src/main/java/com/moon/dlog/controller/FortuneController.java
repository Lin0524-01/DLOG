package com.moon.dlog.controller;

import com.moon.dlog.domain.Fortune;
import com.moon.dlog.domain.R;
import com.moon.dlog.service.FortuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-27 22:33
 **/
@RestController
@CrossOrigin
@RequestMapping("/fortune")
public class FortuneController {
    @Autowired
    private FortuneService fortuneService;

    @PostMapping("/addFortune")
    public R addFortune(@RequestBody Fortune fortune){
        fortuneService.addFortune(fortune);
        return R.ok();
    }

    @PostMapping("/list")
    public R list(@RequestBody Fortune fortune){
        List<Fortune> fortuneList = fortuneService.getFortuneList(fortune);
        return R.ok().data("fortuneList", fortuneList);
    }
}
