package com.github.robi42.boot.dao;

import com.github.robi42.boot.domain.Message;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
public class GreetingRepository implements MessageRepository {
    private final String nameToGreet;
    private final List<Message> greetings;

    @Inject
    public GreetingRepository(final @Value("${name:world}") String nameToGreet) {
        this.nameToGreet = nameToGreet;
        greetings = ImmutableList.of(greeting(), greeting());
    }

    @Override
    public List<Message> findAll() {
        if (log.isDebugEnabled()) {
            greetings.forEach(greeting -> log.debug("Yippie! {}", greeting.getBody()));
        }
        return greetings;
    }

    private Message greeting() {
        final DateTime now = DateTime.now();
        return Message.builder()
                .id(UUID.randomUUID())
                .lastModifiedAt(now.toDate())
                .body(String.format("Hello, %s! The time is: %s",
                        nameToGreet, now.toString("yyyy-MM-dd HH:mm:ss.SSS")))
                .build();
    }
}
