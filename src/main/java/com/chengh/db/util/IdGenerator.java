package com.chengh.db.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import io.shardingsphere.core.keygen.KeyGenerator;
import io.shardingsphere.core.keygen.TimeService;

/**
 * @program: ShardingJdbcDemo
 * @description: 分布式ID生成器
 * @author: chengh
 * @create: 2019-06-28 17:36
 */
@Component
public class IdGenerator implements KeyGenerator {

    /**
     * 时间偏移量，从2016年11月1日零点开始
     */
    public static final long EPOCH = 1540000000000L;

    /**
     * 自增量占用比特
     */
    private static final long SEQUENCE_BITS = 12L;
    /**
     * 工作进程ID比特
     */
    private static final long WORKER_ID_BITS = 10L;
    /**
     * 自增量掩码（最大值）
     */
    private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;
    /**
     * 工作进程ID左移比特数（位数）
     */
    private static final long WORKER_ID_LEFT_SHIFT_BITS = SEQUENCE_BITS;
    /**
     * 时间戳左移比特数（位数）
     */
    private static final long TIMESTAMP_LEFT_SHIFT_BITS = WORKER_ID_LEFT_SHIFT_BITS + WORKER_ID_BITS;

    /**
     * 上一次的序列号，解决并发量小总是偶数的问题
     */
    private long lastSequence = 0L;

    private static TimeService timeService = new TimeService();

    /**
     * 工作进程ID
     */
    private static long workerId;

    /**
     * 最后自增量
     */
    private long sequence;

    /**
     * 最后生成编号时间戳，单位：毫秒
     */
    private long lastTime;

    static {
        /**
         * 浏览 IPKeyGenerator 工作进程编号生成的规则后，感觉对服务器IP后10位（特别是IPV6）数值比较约束。
         * 有以下优化思路：
         * 因为工作进程编号最大限制是 2^10，我们生成的工程进程编号只要满足小于 1024 即可。
         * 1.针对IPV4:
         * ....IP最大 255.255.255.255。而（255+255+255+255) < 1024。
         * ....因此采用IP段数值相加即可生成唯一的workerId，不受IP位限制
         *
         * @Author DogFc
         */
        InetAddress address;
        try {
            address = InetAddress.getLocalHost();
        } catch (final UnknownHostException e) {
            throw new IllegalStateException("Cannot get LocalHost InetAddress, please check your network!");
        }
        byte[] ipAddressByteArray = address.getAddress();
        long workerId = 0L;
        // IPV4
        if (ipAddressByteArray.length == 4) {
            for (byte byteNum : ipAddressByteArray) {
                workerId += byteNum & 0xFF;
            }
            // IPV6
        } else if (ipAddressByteArray.length == 16) {
            for (byte byteNum : ipAddressByteArray) {
                workerId += byteNum & 0B111111;
            }
        } else {
            throw new IllegalStateException("Bad LocalHost InetAddress, please check your network!");
        }
        IdGenerator.workerId = workerId;
    }

    @Override
    public Number generateKey() {
        // 保证当前时间大于最后时间。时间回退会导致产生重复id
        long currentMillis = timeService.getCurrentMillis();
        Preconditions.checkState(lastTime <= currentMillis,
                "Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", lastTime,
                currentMillis);
        // 获取序列号
        if (lastTime == currentMillis) {
            if (0L == (sequence = ++sequence & SEQUENCE_MASK)) { // 当获得序号超过最大值时，归0，并去获得新的时间
                currentMillis = waitUntilNextTime(currentMillis);
            }
        } else {
            // 根据上一次sequence决定本次序列从0还是1开始，保证低并发时奇偶交替
            if (lastSequence == 0) {
                sequence = 1L;
            } else {
                sequence = 0L;
            }
        }
        lastSequence = sequence;
        // 设置最后时间戳
        lastTime = currentMillis;

        // 生成编号
        return ((currentMillis - EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (workerId << WORKER_ID_LEFT_SHIFT_BITS)
                | sequence;
    }

    /**
     * 不停获得时间，直到大于最后时间
     *
     * @param lastTime 最后时间
     * @return 时间
     */
    private long waitUntilNextTime(final long lastTime) {
        long time = timeService.getCurrentMillis();
        while (time <= lastTime) {
            time = timeService.getCurrentMillis();
        }
        return time;
    }
}
