package com.alec.spring.rest.rest_api.service;

import com.alec.spring.rest.rest_api.model.Status;
import com.alec.spring.rest.rest_api.model.Task;
import com.alec.spring.rest.rest_api.repository.IReadSaveFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;


public class XmlReader implements IReadSaveFile {
    public XmlReader() {
    }

    @Override
    public Map<Long, Task> readFile() {
        Map<Long, Task> map = new TreeMap<>();
        DocumentBuilderFactory bbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = bbf.newDocumentBuilder();
            Document doc = builder.parse(new File("ToDoMap.xml"));
            Element root = doc.getDocumentElement();
            NodeList linkmans = root.getElementsByTagName("Task");
            for (int i = 0; i < linkmans.getLength(); i++) {

                Element linkmanEl = (Element) linkmans.item(i);
                Element descEl = (Element) linkmanEl.getElementsByTagName("Description").item(0);
                Element deadlineEl = (Element) linkmanEl.getElementsByTagName("Deadline").item(0);
                Element statusEl = (Element) linkmanEl.getElementsByTagName("Status").item(0);
                Element priorityEl = (Element) linkmanEl.getElementsByTagName("Priority").item(0);
                Element completeEl = (Element) linkmanEl.getElementsByTagName("Complete").item(0);

                String desc = descEl.getTextContent();
                String deadLine = deadlineEl.getTextContent();
                String status = statusEl.getTextContent();
                String priority = priorityEl.getTextContent();
                LocalDate complete = LocalDate.parse(completeEl.getTextContent());

                String caption = linkmanEl.getAttribute("caption");
                int id = Integer.parseInt(linkmanEl.getAttribute("id"));
                Task task = new Task();
                task.setCaption(caption);
                task.setDeadline(LocalDate.parse(deadLine));
                task.setDescription(desc);
                task.setStatus(Status.valueOf(status));
                task.setPriority(Integer.parseInt(priority));

                map.put((long) id, task);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public Map saveFile(Map<Long, Task> map) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("ToDOList");
        doc.appendChild(rootElement);
        for (Map.Entry<Long, Task> entry : map.entrySet()) {
            Long key = entry.getKey();
            entry.getValue().getCaption();

            Element headElement = doc.createElement("Task");
            rootElement.appendChild(headElement);
            headElement.setAttribute("id", String.valueOf(entry.getKey()));
            headElement.setAttribute("caption", entry.getValue().getCaption());

            Element desc = doc.createElement("Description");
            desc.setTextContent(entry.getValue().getDescription());
            headElement.appendChild(desc);

            Element priority = doc.createElement("Priority");
            priority.setTextContent(String.valueOf(entry.getValue().getPriority()));
            headElement.appendChild(priority);

            Element deadline = doc.createElement("Deadline");
            deadline.setTextContent(entry.getValue().getDeadline().toString());
            headElement.appendChild(deadline);

            Element status = doc.createElement("Status");
            status.setTextContent(String.valueOf(entry.getValue().getStatus().name()));
            headElement.appendChild(status);

            Element dataComplete = doc.createElement("Complete");
            dataComplete.setTextContent(String.valueOf(entry.getValue().getComplete()));
            headElement.appendChild(dataComplete);

            WriteXml.WriteXml(doc);

        }

        return map; }
}
