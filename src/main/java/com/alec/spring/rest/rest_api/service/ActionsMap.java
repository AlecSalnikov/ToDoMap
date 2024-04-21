package com.alec.spring.rest.rest_api.service;
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
}