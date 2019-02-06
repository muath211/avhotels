package com.avhotels.registry;


import com.avhotels.clients.Client;

import java.util.List;

/**
 * A registry for third-party clients.
 */
public interface ClientsRegistry {

    void addClient(Client client);

    List<Client> getClients();

}
