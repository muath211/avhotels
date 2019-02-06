package com.avhotels.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

public class ClientErrorHandler extends DefaultResponseErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientErrorHandler.class);

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        LOGGER.error("failed to call [" + url.toString() + "], reason :[" + response.getStatusText() + "]");
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return super.hasError(response);
    }

    @Override
    protected boolean hasError(HttpStatus statusCode) {
        return super.hasError(statusCode);
    }

    @Override
    protected boolean hasError(int unknownStatusCode) {
        return super.hasError(unknownStatusCode);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        super.handleError(response);
    }

    @Override
    protected void handleError(ClientHttpResponse response, HttpStatus statusCode) throws IOException {
        super.handleError(response, statusCode);
    }
}