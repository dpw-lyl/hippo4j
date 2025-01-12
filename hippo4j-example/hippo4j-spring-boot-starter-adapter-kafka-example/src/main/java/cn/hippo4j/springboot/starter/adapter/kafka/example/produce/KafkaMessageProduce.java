package cn.hippo4j.springboot.starter.adapter.kafka.example.produce;

import cn.hippo4j.common.toolkit.JSONUtil;
import cn.hippo4j.example.core.dto.SendMessageDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Kafka message produce.
 */
@Slf4j
@Component
@RestController
@AllArgsConstructor
public class KafkaMessageProduce {

    private final KafkaTemplate kafkaTemplate;

    private final String TOPIC = "kafka_message_hippo4j";

    @GetMapping("/message/send")
    public String sendMessage(Integer count) {
        for (int i = 0; i < count; i++) {
            String keys = UUID.randomUUID().toString();
            SendMessageDTO payload = SendMessageDTO.builder()
                    .receiver("156011xxx91")
                    .uid(keys)
                    .build();
            kafkaTemplate.send(TOPIC, JSONUtil.toJSONString(payload));
        }
        return "success";
    }
}
