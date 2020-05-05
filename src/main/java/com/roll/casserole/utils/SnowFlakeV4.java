package com.roll.casserole.utils;

import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author roll
 * created on 2020/5/5 10:10 上午
 */
public class SnowFlakeV4 {
    public int machineBit = 10;
    public int sequenceBit = 12;

    public long maxSequenceId = ~(-1 << sequenceBit);
    public long maxMachineId = ~(-1 << machineBit);

    public long lastTimeStamp = 0L;

    public long sequenceId;

    public int machineId;

    /**
     * 过期时间：毫秒级别:默认1分钟
     */
    private long expiredTime = 60 * 1000;

    public SnowFlakeV4(int machineId) {
        if (machineId != 0 || machineId >= maxMachineId) {
            this.machineId = machineId;
        } else {
            this.machineId = getMachineId();
        }
    }

    public long getUUID() {
        TimerDelayed timerDelayed = sequenceDelayQueue.poll();
        if (timerDelayed != null) {
            return timerDelayed.getData();
        }
        return getNextId();
    }

    public synchronized long getNextId() {
        long currentTimeStamp = getCurrentTime();
        // 是不是同一个时间段，毫秒级别
        if (currentTimeStamp == lastTimeStamp) {
            sequenceId = (sequenceId + 1) & maxSequenceId;
            // 超过了当前允许的最大量，就等待下一个毫秒
            if (sequenceId == 0) {
                currentTimeStamp = getNextTimeStamp();
            }
            // 大于当前日期，说明是下一个时间段了，从0开始
        } else {
            sequenceId = 0;
        }
        lastTimeStamp = currentTimeStamp;
        return (currentTimeStamp << (machineBit + sequenceBit)) | (machineId << sequenceBit) | sequenceId;
    }

    public long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public long getNextTimeStamp() {
        long currentTime = getCurrentTime();
        while (currentTime < lastTimeStamp) {
            getNextTimeStamp();
        }
        return currentTime;
    }

    private static final DelayQueue<TimerDelayed> sequenceDelayQueue = new DelayQueue<>();

    private void init() {
        generateUUID();
    }


    /**
     * 根据MAC地址来获取machineID
     *
     * @return machineID
     */
    private int getMachineId() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String ip = inetAddress.getHostAddress();
            return (int) (ip.hashCode() & maxMachineId);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        SecureRandom random = new SecureRandom();
        return (int) (random.nextInt() & maxMachineId);
    }

    /**
     * 一分钟的时间
     * 利用当前时间生成一批UUID
     */
    private void generateUUID() {
        new Thread(() -> {
            long currentTime = getCurrentTime();
            for (; getCurrentTime() - 1000 < currentTime; ) {
                sequenceDelayQueue.add(new TimerDelayed(getNextId(), getCurrentTime() + expiredTime));
            }
        }).start();
    }
}

class TimerDelayed implements Delayed {

    private long data;

    public TimerDelayed(long data, long delayTime) {
        this.data = data;
        this.delayTime = delayTime;
    }

    /**
     * 过期时间：毫秒级别:默认三分钟
     */
    private long delayTime;

    @Override
    public long getDelay(@NotNull TimeUnit unit) {
        return unit.convert(delayTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(@NotNull Delayed o) {
        return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }
}
