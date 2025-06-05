package com.heiyuk6.bilihub.infrastructure.config;

import com.baidu.fsg.uid.UidGenerator;
import com.baidu.fsg.uid.impl.CachedUidGenerator;
import com.baidu.fsg.uid.impl.DefaultUidGenerator;
import com.baidu.fsg.uid.worker.WorkerIdAssigner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这个配置会把：
 *   1) 从数据库获取 WorkerID 的 DisposableWorkerIdAssigner
 *   2) 用它去初始化一个 CachedUidGenerator（或 DefaultUidGenerator）
 * 一并注入到 Spring 容器里。
 */
@Configuration
public class UidGeneratorConfig {

    /**
     * 1. 把我们刚才自己写的 WorkerIdAssigner（会连接 WORKER_NODE 表）注入 Spring。
     *    注意，DisposableWorkerIdAssigner 本身已经有 @Component，因此可以省略这个 @Bean:
     *
     * @Bean
     * public WorkerIdAssigner workerIdAssigner() {
     *     return new DisposableWorkerIdAssigner();
     * }
     *
     * 但如果你想去掉 @Component 注解，可以把上面这段代码放开。
     */

    /**
     * 2. 用刚才的 WorkerIdAssigner 去初始化一个 CachedUidGenerator。性能更好，
     *    “雪花算法 + 环形缓冲” 的模式，适合高并发场景。
     */
    @Bean
    public UidGenerator cachedUidGenerator(WorkerIdAssigner workerIdAssigner) {
        CachedUidGenerator generator = new CachedUidGenerator();
        // 注入分配器, 内部会在 afterPropertiesSet() 里拿到 workerId，并启动 buffer 填充线程
        generator.setWorkerIdAssigner(workerIdAssigner);

        // ===== 可选：下面这些配置都有默认值，如果你不想改就可以注释掉 =====
        // generator.setTimeBits((byte) 41);
        // generator.setWorkerBits((byte) 10);
        // generator.setSeqBits((byte) 12);
        // generator.setEpochStr("2020-01-01");
        // generator.setBoostPower((byte) 3);
        // generator.setPaddingFactor((byte) 50);
        // generator.setScheduleInterval(60L);
        // =================================================================================

        return generator;
    }

    /**
     * 如果你并不需要缓存环形缓冲，只想用“最原始的” Snowflake，也是完全可以的：
     *
     @Bean
     public UidGenerator defaultUidGenerator(WorkerIdAssigner workerIdAssigner) {
     DefaultUidGenerator generator = new DefaultUidGenerator();
     generator.setWorkerIdAssigner(workerIdAssigner);
     // 默认位宽 41/10/12，内部会做校验，不要再手动 setTimeBits / setWorkerBits / setSeqBits
     return generator;
     }
     */
}
