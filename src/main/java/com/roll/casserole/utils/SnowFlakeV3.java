package com.roll.casserole.utils;

/**
 * 41位日期
 * 10位机器ID
 * 12位sequenceID
 * ID =
 *
 * @author roll
 * created on 2020/4/29 8:56 上午
 */
public class SnowFlakeV3 {
    public int machineBit = 10;
    public int sequenceBit = 12;

    public long maxSequenceId = ~(-1 << sequenceBit);
    public long maxMachineId = ~(-1 << machineBit);

    public long lastTimeStamp = 0L;

    public long sequenceId;

    public int machineId;

    public SnowFlakeV3(int machineId) {
        this.machineId = machineId;
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

    public static void main(String[] args) {
        SnowFlakeV3 snowFlakeV3 = new SnowFlakeV3(102);
        System.out.println(snowFlakeV3.getNextId());
        System.out.println(Integer.toBinaryString(~(-1 << 12)));
        long currentTime = System.currentTimeMillis();
        int count = 0;
        for (; System.currentTimeMillis() - currentTime <= 1000; ) {
            snowFlakeV3.getNextId();
            count++;
        }
        System.out.println(count);
    }
}
