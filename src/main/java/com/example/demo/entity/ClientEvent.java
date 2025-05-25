package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "client_event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public ClientEvent(Client client, Event event) {
        this.client = client;
        this.event = event;
    }
}
