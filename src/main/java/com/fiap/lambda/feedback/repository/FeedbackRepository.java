package com.fiap.lambda.feedback.repository;


import com.fiap.lambda.feedback.model.FeedbackEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@ApplicationScoped
public class FeedbackRepository {

    @Inject
    DynamoDbEnhancedClient enhancedClient;

    private static final String TABLE_NAME = "feedback";

    public void salvar(FeedbackEntity entity) {
        try {
            DynamoDbTable<FeedbackEntity> table =
                    enhancedClient.table(TABLE_NAME, TableSchema.fromBean(FeedbackEntity.class));

            table.putItem(entity);

            System.out.println("Feedback salvo com sucesso!");
        }
        catch (DynamoDbException e) {
            System.err.println("Erro ao salvar no DynamoDB: " + e.getMessage());
            throw e;
        }
    }
}
