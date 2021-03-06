package com.shifeng.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Twitter的 Snowflake　JAVA实现方案   
 * @author WinZhong
 *
 */
public class IdWorker {
	private final long workerId;
	private final static long twepoch = 1288834974657L;
	private long sequence = 0L;
	//机器标识位数  
	private final static long workerIdBits = 4L;
	private final static long maxWorkerId = -1L ^ -1L << workerIdBits;
	private final static long sequenceBits = 10L;

	private final static long workerIdShift = sequenceBits;
	private final static long timestampLeftShift = sequenceBits + workerIdBits;
	private final static long sequenceMask = -1L ^ -1L << sequenceBits;
	private long lastTimestamp = -1L;
	private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");

	public IdWorker(final long workerId) {
		super();
		if (workerId > this.maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format(
					"worker Id can't be greater than %d or less than 0",
					this.maxWorkerId));
		}
		this.workerId = workerId;
	}

	public synchronized long nextId() {
		long timestamp = this.timeGen();
		if (this.lastTimestamp == timestamp) {
			this.sequence = (this.sequence + 1) & this.sequenceMask;
			if (this.sequence == 0) {
				//System.out.println("###########" + sequenceMask);
				timestamp = this.tilNextMillis(this.lastTimestamp);
			}
		} else {
			this.sequence = 0;
		}
		if (timestamp < this.lastTimestamp) {
			try {
				throw new Exception(
						String.format(
								"Clock moved backwards.  Refusing to generate id for %d milliseconds",
								this.lastTimestamp - timestamp));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		this.lastTimestamp = timestamp;
		long nextId = ((timestamp - twepoch << timestampLeftShift))
				| (this.workerId << this.workerIdShift) | (this.sequence);
		/*System.out.println("timestamp:" + timestamp + ",timestampLeftShift:"
				+ timestampLeftShift + ",nextId:" + nextId + ",workerId:"
				+ workerId + ",sequence:" + sequence);*/
		return nextId;
	}

	private long tilNextMillis(final long lastTimestamp) {
		long timestamp = this.timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = this.timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}
	/**
	 * 获取不重复id
	 * @return
	 */
	public static long getId(){
		IdWorker worker2 = new IdWorker(2);
		return worker2.nextId();
	}
	/**
	 * 获取序列号 
	 * @return
	 */
	public static String getSerialNumber(){
		return sdfTimes.format(new Date())+System.nanoTime();
	}
	public static void main(String[] args){
		//SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");
		System.out.println(IdWorker.getId());
		System.out.println(IdWorker.getSerialNumber());
		//System.out.println(sdfTimes.format(new Date())+System.nanoTime());
	}

}