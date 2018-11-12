package com.mcb.javajuniortask.controller;

import com.mcb.javajuniortask.dto.ClientDTO;
import com.mcb.javajuniortask.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/show-all-clients")
    public Iterable<ClientDTO> showAllClients() {
        return clientService.showAllClients();
    }

    @PostMapping("/add-client")
    public ResponseEntity<?> addClient(@RequestParam(value = "name") String clientName) {
        if (clientName == null) {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok()
                .body(clientService.addClient(clientName));
    }

    @PostMapping(value = "/add-debt-to-client")
    public ResponseEntity<?> addDebt(@RequestParam(value = "clientId") UUID clientId,
                                     @RequestParam(value = "value") BigDecimal value) {
        if (clientId == null || value == null) {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok()
                .body(clientService.addDebtToClient(clientId, value));
    }
}
