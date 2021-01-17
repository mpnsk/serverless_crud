package com.github.mpnsk.serverless_crud.funqy;

import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LocalDbCreationRule implements BeforeEachCallback, AfterEachCallback {
    private DynamoDBProxyServer server;

    protected void stopUnchecked(DynamoDBProxyServer dynamoDbServer) {
        try {
            dynamoDbServer.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }    
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        System.setProperty("sqlite4java.library.path", "native-libs");

        String port = "8000";
        server = ServerRunner.createServerFromCommandLineArgs(
                new String[]{"-inMemory", "-port", port});
        server.start();
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        this.stopUnchecked(server);
    }
}