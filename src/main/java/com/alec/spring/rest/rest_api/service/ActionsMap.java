package com.alec.spring.rest.rest_api.service;

import com.alec.spring.rest.rest_api.model.Status;
import com.alec.spring.rest.rest_api.model.StatusCheck;
import com.alec.spring.rest.rest_api.model.Task;
import com.alec.spring.rest.rest_api.repository.IReadSaveFile;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class ActionsMap {
    Map<String, Actions> allActions = new TreeMap<>();

    public Map<String, Actions> ActionsMap() {
        allActions.put("Show/AddNew", new Actions(new XmlReader()));
        allActions.put("Delete", new ActionsDeleteTask(new XmlReader()));
        allActions.put("Update", new ActionsUpdateTask(new XmlReader()));
        allActions.put("Complete", new ActionsTaskComplete(new XmlReader()));
        return allActions;
    }

    public Map<String, Actions> getAllActions() {
        return allActions;
    }

    public void setAllActions(Map<String, Actions> actions) {
        this.allActions = actions;
    }


    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        Actions actions = new Actions(new XmlReader());
        ActionsMap actionsMap = new ActionsMap();
        Map<String, Actions> allActions = (actionsMap.ActionsMap());


        System.out.println(allActions.get("Show/AddNew").getToDoMap(StatusCheck.ALL));
        Task task = new Task("Fu", "A", LocalDate.now(), Status.NEW, 3);
        allActions.get("Show/AddNew").setToDoMap(task);
        System.out.println(allActions.get("Show/AddNew").getToDoMap(StatusCheck.ALL));
       allActions.get("Delete").setToDoMap(task);
        System.out.println(allActions.get("Show/AddNew").getToDoMap(StatusCheck.ALL));
    }}