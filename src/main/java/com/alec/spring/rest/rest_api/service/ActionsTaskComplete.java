package com.alec.spring.rest.rest_api.service;

import com.alec.spring.rest.rest_api.model.Status;
import com.alec.spring.rest.rest_api.model.Task;
import com.alec.spring.rest.rest_api.model.UniqueId;
import com.alec.spring.rest.rest_api.repository.IReadSaveFile;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.Map;
import java.util.TreeMap;

public class ActionsTaskComplete extends Actions {
    public ActionsTaskComplete(IReadSaveFile readSaveFile) {
        super(readSaveFile);
    }

    @Override
    public void setToDoMap(Task task) throws ParserConfigurationException, TransformerException {
        for (Map.Entry<Long, Task> entry : taskMap.entrySet()) {
            if (entry.getValue().getCaption().equals(task.getCaption())) {
                entry.getValue().setStatus(Status.DONE);
                saveAll();
            }
        }
    }
}
