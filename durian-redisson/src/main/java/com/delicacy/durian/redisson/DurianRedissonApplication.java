package com.delicacy.durian.redisson;

import com.delicacy.durian.redisson.util.RedissLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@SpringBootApplication
@ComponentScan("com.delicacy")
public class DurianRedissonApplication {

    @Autowired
    private RedissonClient redissonClient;

    public static void main(String[] args) {
        SpringApplication.run(DurianRedissonApplication.class, args);
    }

    @GetMapping("sendMessage")
    public String sendMessage(String message, @RequestParam(required = false) Integer timeout) {
//		return doRedissonLock(message);
        return doUtilTryLock(message);
//		return doUtilLock(message);
    }

    private String doUtilTryLock(String message) {
        String e = "REDLOCK_KEY";
        boolean tryLock = RedissLockUtil.tryLock(e, 2, 5);
        if (tryLock)
            try {
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();
                countdown(5);
                stopWatch.stop();
                double totalTimeSeconds = stopWatch.getTotalTimeSeconds();
                log.info(Thread.currentThread().getName() + "----" + totalTimeSeconds);
                return "Acquiring lock successful is " + message;
            } catch (Exception ee) {
                ee.printStackTrace();
            } finally {
                RedissLockUtil.unlock(e);
            }
        return "No acquiring lock is " + message;
    }

    private String doUtilLock(String message) {
        String e = "REDLOCK_KEY";
        try {
            RedissLockUtil.lock(e, 2);
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            countdown(5);
            stopWatch.stop();
            double totalTimeSeconds = stopWatch.getTotalTimeSeconds();
            log.info(Thread.currentThread().getName() + "----" + totalTimeSeconds);
            return "Acquiring lock successful is " + message;
        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            RedissLockUtil.unlock(e);
        }
        return "No acquiring lock is " + message;
    }

    private String doRedissonTryLock(String message) {
        RLock redLock = redissonClient.getLock("REDLOCK_KEY");
        boolean tryLock;
        try {
            // waitTime 其他线程等待获取锁时间
            // leaseTime 当前线程释放锁时间
            tryLock = redLock.tryLock(2, 5, TimeUnit.SECONDS);
            if (tryLock) {
                redLock.lock(2, TimeUnit.SECONDS);
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();
                countdown(5);
                stopWatch.stop();
                double totalTimeSeconds = stopWatch.getTotalTimeSeconds();
                log.info(Thread.currentThread().getName() + "----" + totalTimeSeconds);
                return "Acquiring lock successful is " + message;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (redLock.isLocked())
                if (redLock.isHeldByCurrentThread())
                    redLock.unlock();
        }
        return "No acquiring lock is " + message;
    }

    private String doRedissonLock(String message) {
        RLock redLock = redissonClient.getLock("REDLOCK_KEY");
        try {
            // leaseTime 当前线程释放锁时间，超过并发进入
            redLock.lock(2, TimeUnit.SECONDS);
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            countdown(5);
            stopWatch.stop();
            double totalTimeSeconds = stopWatch.getTotalTimeSeconds();
            log.info(Thread.currentThread().getName() + "----" + totalTimeSeconds);
            return "Acquiring lock successful is " + message;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (redLock.isLocked())
                if (redLock.isHeldByCurrentThread())
                    redLock.unlock();
        }
        return "No acquiring lock is " + message;
    }

    private void countdown(Integer timeout) {
        Random random = new Random();
        int i = random.nextInt(timeout);
        //int i = timeout;
        while (i != 0) {
            i--;
            log.warn(String.valueOf(i));
            try {
                TimeUnit.SECONDS.sleep((long) 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
