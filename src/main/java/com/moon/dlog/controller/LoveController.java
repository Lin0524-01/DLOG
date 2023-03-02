package com.moon.dlog.controller;

import com.moon.dlog.domain.LoverCard;
import com.moon.dlog.domain.R;
import com.moon.dlog.service.LoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-23 15:16
 **/
@RestController
@CrossOrigin
@RequestMapping("/love")
public class LoveController {
    @Autowired
    private LoveService loveService;

    @GetMapping("/days")
    public R loveDays(){
       int day = loveService.getLoveDays();
       return R.ok().data("day", day);
    }

    @PostMapping("/addCard")
    public R addCard(@RequestBody LoverCard loverCard){
        int result = loveService.addCard(loverCard);
        return R.ok();
    }

    @GetMapping("/getCardList")
    public R getCardList(){
        List<LoverCard> cardList = loveService.getCardList();
        return R.ok().data("cardList", cardList);
    }
}
