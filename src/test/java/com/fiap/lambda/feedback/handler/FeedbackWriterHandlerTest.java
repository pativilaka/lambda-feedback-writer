package com.fiap.lambda.feedback.handler;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FeedbackWriterHandlerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final FeedbackWriterHandler handler = new FeedbackWriterHandler();

    @BeforeEach
    void injectObjectMapper() throws Exception {
        Field field = FeedbackWriterHandler.class.getDeclaredField("objectMapper");
        field.setAccessible(true);
        field.set(handler, objectMapper);
    }

    @Test
    void deveProcessarEventoSqsComSucesso() throws Exception {
        String json = Files.readString(
                Path.of("src/test/resources/event.json"),
                StandardCharsets.UTF_8
        );

        SQSEvent event = objectMapper.readValue(json, SQSEvent.class);

        handler.handleRequest(event, null);
    }
}
