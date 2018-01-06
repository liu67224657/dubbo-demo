package com.ericliu.dubbo.webapi.configure;

import io.codis.jodis.JedisResourcePool;
import io.codis.jodis.RoundRobinJedisPool;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import redis.clients.jedis.Jedis;

import java.io.IOException;

//import org.springframework.lang.Nullable;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2018/1/5
 */
public class CoidsConnectionFactory implements InitializingBean, DisposableBean, RedisConnectionFactory {

    private String zkServer;
    private int timeout = 30000;
    private String zkProxyDir;

    private boolean convertPipelineAndTxResults = true;
    private JedisResourcePool jedisResourcePool;
//    private RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration("localhost",
//            Protocol.DEFAULT_PORT);





//    public CoidsConnectionFactory(String zkServer, int timeout, String zkProxyDir) {
//        this.zkServer = zkServer;
//        this.timeout = timeout;
//        this.zkProxyDir = zkProxyDir;
//    }
//
//    public CoidsConnectionFactory(String zkServer, String zkProxyDir) {
//        this(zkServer,30000,zkProxyDir);
//    }

    @Override
    public RedisConnection getConnection() {
        Jedis jedis = jedisResourcePool.getResource();
        JedisConnection connection = new JedisConnection(jedis);
        connection.setConvertPipelineAndTxResults(convertPipelineAndTxResults);
        return connection;
    }

    public int getDatabase() {
        return 0;
    }

    @Override
    public RedisClusterConnection getClusterConnection() {
        return null;
    }

    @Override
    public boolean getConvertPipelineAndTxResults() {
        return false;
    }

    public void setConvertPipelineAndTxResults(boolean convertPipelineAndTxResults) {
        this.convertPipelineAndTxResults = convertPipelineAndTxResults;
    }

    @Override
    public RedisSentinelConnection getSentinelConnection() {
        return null;
    }

//    @Nullable
    @Override
    public DataAccessException translateExceptionIfPossible(RuntimeException e) {
        return null;
    }

    @Override
    public void destroy() throws Exception {
        try {
            jedisResourcePool.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jedisResourcePool = null;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        jedisResourcePool = RoundRobinJedisPool.create().curatorClient(zkServer, 30000).zkProxyDir(zkProxyDir).build();
    }

    public void setZkServer(String zkServer) {
        this.zkServer = zkServer;
    }

    public String getZkServer() {
        return zkServer;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getZkProxyDir() {
        return zkProxyDir;
    }

    public void setZkProxyDir(String zkProxyDir) {
        this.zkProxyDir = zkProxyDir;
    }
}
