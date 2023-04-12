package github.ittranslationclub.askme.chatgpt.infrastructure.utils;/*
 * ClassName: IpCount
 * Description:
 * @Author: zjh
 * @Create: 2023/4/12
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ip 相关的统计
 */
@Component
public class IpCount {

//    @Resource
    private RedisTemplate redisTemplate;

    public IpCount(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * ip 是否可以继续访问
     *
     * @param url      访问的url
     * @param ip       访问的ip
     * @param maxValue 最大的访问次数
     * @param time     时间范围 单位为秒
     * @return true 可以继续访问  false 超出限制，不可以继续访问
     */
    public boolean ipIsOk(String url, String ip, int maxValue, long time) {
        boolean ipIsOk = true;

        String key = "ip:" + ip + "url:" + url; // 根据指定规则拼接生成此次访问的key
        List<Long> timeList = getCacheList(key); // 查询redis中已有的数据


        if (timeList.size() == 0 || timeList.size() < maxValue) { // 没有记录或者没有达到最大访问次数不做超时验证
            addNewTime(key, time); // 添加当前的时间到list中
            return ipIsOk;
        } // 若不满足此条件，则证明list中的值达到了最大数量（即访问的次数）

        // 判断达到规定的访问次数的用时是否小于规定的时间
        // （当前时间戳-最旧记录的时间戳）< 限定的时间转毫秒
        if ((DateUtils.getNowTimeLong() - timeList.get(0)) < (time * 1000)) {
            // 未达到，证明指定范围时间内访问数量超过的定义数量
            ipIsOk = false;
        }

        // 删除第一个值（就是时间最旧的那个值，我这边是下标为0的，手动在redis客户端测试的为row最大的值。这个根据自己的具体情况）
        redisTemplate.opsForList().remove(key, 1, timeList.get(0));
        addNewTime(key, time); // 添加当前的时间到list中

        return ipIsOk;
    }

    /**
     * 往redis中添加新的数据，注：新增的值row在后
     *
     * @param key  key
     * @param time 有效时间，单位为秒
     */
    public void addNewTime(String key, long time) {
        List<Long> nowTime = new ArrayList<>();
        nowTime.add(DateUtils.getNowTimeLong()); // 当前时间戳
        setCacheList(key, nowTime); // 追加值或新缓存值
        expire(key, time + 1); // 设置有效时间
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间  注：此处时间单位为秒
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout) {
        return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }


    /**
     * 缓存List数据
     *
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> long setCacheList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

}
