package es.alvaroweb.paintfactory.comunication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import paintfactory.prototype.Factory;
import paintfactory.prototype.io.Input;
import paintfactory.prototype.io.Output;

/**
 *  This a singleton class represent a case, it contains the stream to be
 *  sent to the PaintFactory library
 */
public class Document {
    private static final Document INSTANCE = new Document();
    private String document;


    private Document() {
        document = "";
    }

    public Document getInstance(){
        return INSTANCE;
    }

    public String sendDataToFactory() throws IOException {
        Input input = new Input();
        boolean result = input.readInputStream(
                new ByteArrayInputStream(document.getBytes()));
        Factory factory = new Factory(input);
        factory.makePaints();

        Output output = factory.getDataResults();
        OutputStream os = new ByteArrayOutputStream();
        output.writeCasesToOutputStream(os);

        return os.toString();
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
