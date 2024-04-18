package com.alec.spring.rest.rest_api.service;

import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class WriteXml {
    static void WriteXml(Document doc)
            throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domsource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult("ToDoMap.xml");
            transformer.transform(domsource, streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        System.out.println("ToDoMap.xml" + " объект создан");

    }
}

