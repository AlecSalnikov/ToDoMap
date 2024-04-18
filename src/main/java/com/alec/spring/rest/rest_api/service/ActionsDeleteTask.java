package com.alec.spring.rest.rest_api.service;

import com.alec.spring.rest.rest_api.model.Task;
import com.alec.spring.rest.rest_api.model.UniqueId;
import com.alec.spring.rest.rest_api.repository.IReadSaveFile;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.Map;
import java.util.TreeMap;

public class ActionsDeleteTask extends Actions {
    public ActionsDeleteTask(IReadSaveFile readSaveFile) {
        super(readSaveFile);
    }

    @Override
    public void setToDoMap(Task task) throws ParserConfigurationException, TransformerException {
        Long key = (captionCheck(task));
            taskMap.remove(key);
            saveAll();
           getUpdatedTaskMap(readSaveFile);


    }

    public void setToDoMap(Long id) throws ParserConfigurationException, TransformerException {
        taskMap.remove(id);
        saveAll();
    }


}
