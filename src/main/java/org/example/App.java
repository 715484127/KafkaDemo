package org.example;

import com.alibaba.fastjson2.JSON;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.beans.Order;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.reader.StreamReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Kafka Demo
 *
 */
public class App 
{
    private final static String TOPIC_NAME = "my-topic-test-1";
    public static void main( String[] args )
    {
        InputStream input = null;
        input = App.class.getClassLoader().getResourceAsStream("application.yaml");
        Yaml yaml = new Yaml();
        Map<String, Object> object = yaml.load(input);
        object.get("kafka");

        //1.创建kafka生产者配置对象
        Properties props = new Properties();
        //2.给 kafka 配置对象添加配置信息：bootstrap.servers
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        //key,value 序列化（必须）
        //把发送的key从字符串序列化为字节数组
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //把发送消息value从字符串序列化为字节数组
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 3. 创建 kafka 生产者对象
        Producer<String, String> producer = new KafkaProducer<>(props);

        Order order = new Order((long) 1, 122);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_NAME, order.getId(), JSON.toJSONString(order));
        //4. 调用 send 方法,发送消息
        RecordMetadata metadata = null;
        try {
            metadata = producer.send(producerRecord).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        producer.close();
        //=====阻塞=======
        System.out.println("同步方式发送消息结果：" + "topic-" + metadata.topic() + "|partition-" + metadata.partition() + "|offset-" + metadata.offset());

    }
}
