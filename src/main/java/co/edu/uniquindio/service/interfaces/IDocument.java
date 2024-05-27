package co.edu.uniquindio.service.interfaces;

import co.edu.uniquindio.dto.TicketDTO;
import org.springframework.stereotype.Service;

@Service
public interface IDocument {

    void createDocument(TicketDTO ticketDTO) throws Exception;

    void updateDocument() throws Exception;
}
