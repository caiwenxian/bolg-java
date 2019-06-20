package utils.timeTask;


import exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import service.task.IReptileMusicTaskService;
import service.task.impl.ReptileMusicTaskServiceImpl;
import utils.SpringContextUtil;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

/**
 * 在 TimerManager 这个类里面，大家一定要注意 时间点的问题。如果你设定在凌晨2点执行任务。但你是在2点以后
 * 发布的程序或是重启过服务，那这样的情况下，任务会立即执行，而不是等到第二天的凌晨2点执行。为了，避免这种情况
 * 发生，只能判断一下，如果发布或重启服务的时间晚于定时执行任务的时间，就在此基础上加一天。
 *
 * @author wls
 */
public class NFDFlightDataTimerTask extends TimerTask {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public void run() {
        System.out.println("开始执行:" + formatter.format(Calendar.getInstance().getTime()));

        ApplicationContext appCtx = SpringContextUtil.getApplicationContext();
        IReptileMusicTaskService reptileMusicTaskService = (IReptileMusicTaskService) appCtx.getBean(IReptileMusicTaskService.class);
        try {
            reptileMusicTaskService.reptileAllTopList();
        } catch (SerException e) {
            e.printStackTrace();
        }

        System.out.println("执行当前时间:" + formatter.format(Calendar.getInstance().getTime()));
    }

}
