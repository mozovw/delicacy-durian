package com.delicacy.durian.idworker.idworker;


/**
 * id构成: 42位的时间前缀 + 10位的节点标识 + 12位的sequence避免并发的数字(12位不够用时强制得到新的时间前缀)
 */
public class IdWorker {

	private final long epoch = 1403854494756L; // 时间起始标记点，作为基准，一般取系统的最近时间
	private final long workerIdBits = 4L; // 机器标识位数
	private final long sequenceBits = 12L; // 毫秒内自增位

	public final long maxWorkerId = -1L ^ -1L << this.workerIdBits;// 机器ID最大值:16

	private final long workerIdShift = this.sequenceBits; // 12
	private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;
	private final long sequenceMask = -1L ^ -1L << this.sequenceBits;

	private final long workerId;
	private long sequence = 0L; // 0，并发控制
	private long lastTimestamp = -1L;

	public IdWorker(long workerId) {
		if (workerId > this.maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(
					String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
		}
		this.workerId = workerId;
	}

	public synchronized long nextId() {
		long timestamp = this.currentTimeMillis();
		if (this.lastTimestamp == timestamp) { // 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环);
												// 对新的timestamp，sequence从0开始
			this.sequence = this.sequence + 1 & this.sequenceMask;
			if (this.sequence == 0) {
				timestamp = this.tilNextMillis(this.lastTimestamp);// 重新生成timestamp
			}
		} else {
			this.sequence = 0;
		}

		if (timestamp < this.lastTimestamp) {
			System.err.println(String.format("clock moved backwards.Refusing to generate id for %d milliseconds",
					(this.lastTimestamp - timestamp)));
			throw new RuntimeException(
					String.format("clock moved backwards.Refusing to generate id for %d milliseconds",
							(this.lastTimestamp - timestamp)));
		}

		this.lastTimestamp = timestamp;
		return timestamp - this.epoch << this.timestampLeftShift | this.workerId << this.workerIdShift | this.sequence;
	}

	/**
	 * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
	 */
	private long tilNextMillis(long lastTimestamp) {
		long timestamp = this.currentTimeMillis();
		while (timestamp <= lastTimestamp) {
			timestamp = this.currentTimeMillis();
		}
		return timestamp;
	}

	/**
	 * 获得系统当前毫秒数
	 */
	private long currentTimeMillis() {
		return System.currentTimeMillis();
	}

//	public static void main(String[] args) {
//		IdWorker idWorker = new IdWorker(0);
//		System.out.println(idWorker.nextId());
//		System.out.println(idWorker.nextId());
//		System.out.println(idWorker.nextId());
//		System.out.println(idWorker.nextId());
//	}

}