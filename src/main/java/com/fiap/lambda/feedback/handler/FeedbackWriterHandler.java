package com.fiap.lambda.feedback.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.lambda.feedback.dto.FeedbackDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FeedbackWriterHandler implements RequestHandler<SQSEvent, Void>{

    @Inject
    ObjectMapper objectMapper;

    @Override
    public Void handleRequest(SQSEvent event, Context context) {

        event.getRecords().forEach(sqsMessage -> {
            try {
                String body = sqsMessage.getBody();

                JsonNode snsEnvelope = objectMapper.readTree(body);

                String feedbackJson = snsEnvelope.get("Message").asText();

                FeedbackDTO feedback = objectMapper.readValue(feedbackJson, FeedbackDTO.class);

                String snsTimestamp = snsEnvelope.get("Timestamp").asText();

                System.out.printf(
                        "Feedback recebido: descricao='%s', nota=%d%n",
                        feedback.getDescricao(),
                        feedback.getNota()
                );
                System.out.println("Timestamp SNS: " + snsTimestamp);

            } catch (Exception e) {
                // Se der erro em algum registro, loga e segue para o próximo
                System.err.println("Erro ao processar mensagem SQS: " + e.getMessage());
                e.printStackTrace();
            }
        });

        return null; // Lambda com retorno Void sempre devolve null
    }
}
