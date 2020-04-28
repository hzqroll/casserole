package com.roll.casserole.utils;

/**
 * 1bit不用，41位日期位，10位机器，12位序列号
 * 标准雪花算法改进
 *
 * @author roll
 * created on 2020/4/26 5:14 下午
 */
public class SnowFlakeV2 {
    private final static int timeStampBitLength = 41;
    private final static int machineBitLength = 10;
    private final static int sequenceBitLength = 12;

    private long maxTimeStamp;
    private long maxMachine;
    private long maxSequence;
    private long maxTimeStampLeftShift;

    private static long lastTimeStamp = 1587955573000L;
    private final static long startTimeStamp = 1587955573000L;

    /**
     * 毫秒内的sequenceId
     */
    private long sequenceId = 0L;

    private int machineId = 1;

    public SnowFlakeV2() {
        maxTimeStamp = (1L << (timeStampBitLength - 1)) * 2 - 1;
        maxMachine = (1L << (machineBitLength - 1)) * 2 - 1;
        maxSequence = (1L << (sequenceBitLength - 1)) * 2 - 1;
        maxTimeStampLeftShift = (1L << (machineBitLength + sequenceBitLength - 1) * 2 - 1);
    }

    public synchronized long getNextId() {
        long currentTimeStamp = getCurrentTimeStamp();
        if (currentTimeStamp < lastTimeStamp) {
            lastTimeStamp = getCurrentTimeStamp();
            getNextId();
        }

        // 如果是当前时间，sequence+1，取数据
        if (currentTimeStamp == lastTimeStamp) {
            sequenceId = (sequenceId + 1) & maxSequence;
            //毫秒内序列溢出
            if (sequenceId == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                currentTimeStamp = getNextTimeStamp(lastTimeStamp);
            }
        } else {
            sequenceId = 0;
        }
        //上次生成ID的时间截
        lastTimeStamp = currentTimeStamp;
        //移位并通过或运算拼到一起组成64位的ID
        return ((lastTimeStamp - startTimeStamp) << maxTimeStampLeftShift) | (machineId << maxMachine) | sequenceId;
    }

    public long getCurrentTimeStamp() {
        return System.currentTimeMillis();
    }

    public long getNextTimeStamp(long lastTimeStamp) {
        long currentTimeStamp = getCurrentTimeStamp();
        while (currentTimeStamp <= lastTimeStamp) {
            currentTimeStamp = getCurrentTimeStamp();
        }
        return currentTimeStamp;
    }

    public static void main(String[] args) {
        System.out.println((1L << 40) * 2 - 1);
        System.out.println(~(1L << 42) << 1);
    }
}
