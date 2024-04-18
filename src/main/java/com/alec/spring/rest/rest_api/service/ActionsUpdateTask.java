package com.alec.spring.rest.rest_api.service;

import com.alec.spring.rest.rest_api.model.Task;
import com.alec.spring.rest.rest_api.model.UniqueId;
import com.alec.spring.rest.rest_api.repository.IReadSaveFile;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.Map;
import java.util.TreeMap;

public class ActionsUpdateTask extends Actions {
    public ActionsUpdateTask(IReadSaveFile readSaveFile) {
        super(readSaveFile);
    }

    @Override
    public void setToDoMap(Task newTask) throws ParserConfigurationException, TransformerException {
        long key = (captionCheck(newTask));
        if (key != -1L) {
            taskMap.get(key).setDescription(newTask.getDescription());
            taskMap.get(key).setDeadline(newTask.getDeadline());
            taskMap.get(key).setPriority(newTask.getPriority());
            taskMap.get(key).setStatus(newTask.getStatus());
            saveAll();
        }
    }

    public void changeCaption(Task newTask, String newCaption) throws ParserConfigurationException, TransformerException {
        long key = (captionCheck(newTask));
        if (key != -1L) {
            taskMap.get(key).setCaption(newCaption);
        }

    }
}

