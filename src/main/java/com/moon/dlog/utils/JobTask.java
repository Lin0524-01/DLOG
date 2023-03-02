package com.moon.dlog.utils;

import cn.hutool.http.HttpUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Description 定时任务执行py代码生成excel，再通过easy-excel 读取excel数据更新team_season表
 * @Author linyl
 * @Date 2023-01-15 23:21
 **/
@Component
public class JobTask {
    @Scheduled(cron = "0 0 16 * * ?")
    public void syncTeamSeasonData() {
        try {
            Process process = Runtime.getRuntime().exec("C:\\Python310\\python.exe D:\\MyWorkSpace\\Python\\teamData.py");
            process.waitFor();
            Map<String, Object> map = new HashMap<>();
            map.put("filePath", "D:\\MyWorkSpace\\Python\\result.xlsx");
            HttpUtil.post("http://localhost:8010/team/updateTeamSeasonData", map);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0 5 ? * 2")
    public void addWeekOnMonday(){
        HttpUtil.get("http://localhost:8010/week/addWeek");
    }
}
