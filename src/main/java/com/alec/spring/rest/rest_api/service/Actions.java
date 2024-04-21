package com.alec.spring.rest.rest_api.service;

import com.alec.spring.rest.rest_api.model.*;


import com.alec.spring.rest.rest_api.repository.IReadSaveFile;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.Map;
import java.util.TreeMap;

import static com.alec.spring.rest.rest_api.model.StatusCheck.ALL;

public class Actions implements IActions {
    protected IReadSaveFile readSaveFile;
    protected Map<Long, Task> taskMap = new TreeMap<>();

    public Actions(IReadSaveFile readSaveFile) {
        this.readSaveFile = readSaveFile;
        this.taskMap = getUpdatedTaskMap(readSaveFile);
    }

    public Map<Long, Task> getTaskMap() {
        return taskMap;
    }

    public void setTaskMap(Map<Long, Task> taskMap) {
        this.taskMap = taskMap;
    }

    public IReadSaveFile getReadSaveFile() {
        return readSaveFile;
    }

    public void setReadSaveFile(IReadSaveFile readSaveFile) {
        this.readSaveFile = readSaveFile;
    }

    public static Map<String, Actions> getAllActions() {
        return getAllActions();
    }

    public static void setAllActions(Map<String, Actions> allActions) {
        setAllActions(allActions);
    }


    protected Map<Long, Task> getUpdatedTaskMap(IReadSaveFile readSaveFile) {
        taskMap = readSaveFile.readFile();
        return taskMap;
    }

    public Map<Long, Task> getToDoMap(StatusCheck statusCheck) {

        if (!statusCheck.equals(ALL)) {
            Map<Long, Task> sortedByStatus = new TreeMap<>();
            for (Map.Entry<Long, Task> entry : taskMap.entrySet()) {
                Long key = entry.getKey();
                entry.getValue().getCaption();
                if (entry.getValue().getStatus().equals(CheckChain.findStatus(statusCheck))) {
                    sortedByStatus.put(key, taskMap.get(key));
                }
            }
            return sortedByStatus;
        }
        getUpdatedTaskMap(readSaveFile);
        return taskMap;

    }

    public void setToDoMap(Task task) throws ParserConfigurationException, TransformerException {
        if (captionCheck(task) == -1L) {
            taskMap.put(UniqueId.getId(taskMap), task);
            saveAll();
            System.out.println("To added new Task with name " + task.getCaption());
        } else System.out.println("not valid key");
    }

    public void setToDoMap(Long id) throws ParserConfigurationException, TransformerException {
    }

    public void saveAll() throws ParserConfigurationException, TransformerException {
        readSaveFile.saveFile(taskMap);
    }

    public Long captionCheck(Task task) {
        for (Map.Entry<Long, Task> entry : taskMap.entrySet()) {
            Long key = entry.getKey();
            if (entry.getValue().getCaption().equals(task.getCaption())) {
                return key;
            }
        }
        return -1L;
    }
}




