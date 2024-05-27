package co.edu.uniquindio.service.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface IDocument {

    void createDocument(String dni) throws Exception;

    void updateDocument() throws Exception;
}
