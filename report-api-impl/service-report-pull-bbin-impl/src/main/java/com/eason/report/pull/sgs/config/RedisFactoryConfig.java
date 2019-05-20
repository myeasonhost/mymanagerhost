package com.eason.report.pull.sgs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
public class RedisFactoryConfig {

    @Bean(name = "stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(
            @Value("${spring.redis.host}") String hostName,
            @Value("${spring.redis.port}") int port,
            @Value("${spring.redis.password}") String password,
            @Value("${spring.redis.pool.max-idle}") int maxIdle,
            @Value("${spring.redis.pool.max-active}") int maxTotal,
            @Value("${spring.redis.database}") int index,
            @Value("${spring.redis.pool.max-wait}") long maxWaitMillis,
            @Value("${spring.redis.testOnBorrow}") boolean testOnBorrow) {
        StringRedisTemplate temple = new StringRedisTemplate();
        temple.setConnectionFactory(connectionFactory(hostName, port, password,
                maxIdle, maxTotal, index, maxWaitMillis, testOnBorrow));
        return temple;
    }

    @Bean(name = "stringRedisTemplate10")
    public StringRedisTemplate stringRedisTemplate10(
            @Value("${spring.redis.host}") String hostName,
            @Value("${spring.redis.port}") int port,
            @Value("${spring.redis.password}") String password,
            @Value("${spring.redis.pool.max-idle}") int maxIdle,
            @Value("${spring.redis.pool.max-active}") int maxTotal,
            @Value("${spring.redis.database10}") int index,
            @Value("${spring.redis.pool.max-wait}") long maxWaitMillis,
            @Value("${spring.redis.testOnBorrow}") boolean testOnBorrow) {
        StringRedisTemplate temple = new StringRedisTemplate();
        temple.setConnectionFactory(connectionFactory(hostName, port, password,
                maxIdle, maxTotal, index, maxWaitMillis, testOnBorrow));
        return temple;
    }

    @Bean(name = "redisTemplate10")
    public RedisTemplate redisTemplate(
            @Value("${spring.redis.host}") String hostName,
            @Value("${spring.redis.port}") int port,
            @Value("${spring.redis.password}") String password,
            @Value("${spring.redis.pool.max-idle}") int maxIdle,
            @Value("${spring.redis.pool.max-active}") int maxTotal,
            @Value("${spring.redis.database10}") int index,
            @Value("${spring.redis.pool.max-wait}") long maxWaitMillis,
            @Value("${spring.redis.testOnBorrow}") boolean testOnBorrow) {
        RedisTemplate temple = new RedisTemplate();
        temple.setConnectionFactory(connectionFactory(hostName, port, password,
                maxIdle, maxTotal, index, maxWaitMillis, testOnBorrow));
        return temple;
    }


    public RedisConnectionFactory connectionFactory(String hostName, int port,
                                                    String password, int maxIdle, int maxTotal, int index,
                                                    long maxWaitMillis, boolean testOnBorrow) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(hostName);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setDatabase(index);
        redisStandaloneConfiguration.setPassword(password);
        //获得默认的连接池构造
        //这里需要注意的是，edisConnectionFactoryJ对于Standalone模式的没有（RedisStandaloneConfiguration，JedisPoolConfig）的构造函数，对此
        //我们用JedisClientConfiguration接口的builder方法实例化一个构造器，还得类型转换
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcf = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        //修改我们的连接池配置
        JedisPoolConfig poolCofig = new JedisPoolConfig();
        poolCofig.setMaxIdle(maxIdle);
        poolCofig.setMaxTotal(maxTotal);
        poolCofig.setMaxWaitMillis(maxWaitMillis);
        poolCofig.setTestOnBorrow(testOnBorrow);
        jpcf.poolConfig(poolCofig);
        //通过构造器来构造jedis客户端配置
        JedisClientConfiguration jedisClientConfiguration = jpcf.build();

        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }

}