package com.avhotels.registry;

import com.avhotels.clients.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ClassPathClientsLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassPathClientsLoader.class);
    private ClientsRegistry clientsRegistry;

    public ClassPathClientsLoader(ClientsRegistry clientsRegistry) {
        this.clientsRegistry = clientsRegistry;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOGGER.info("loading clients onto registry ...");

        event.getApplicationContext().getBeansOfType(Client.class).forEach(this::addClient);
    }

    private void addClient(String name, Client client) {
        clientsRegistry.addClient(client);

        LOGGER.info("client [" + name + "] loaded successfully.");
    }


}
