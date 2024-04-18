
package com.alec.spring.rest.rest_api.test;

import com.alec.spring.rest.rest_api.model.Status;
import com.alec.spring.rest.rest_api.model.StatusCheck;
import com.alec.spring.rest.rest_api.model.Task;
import com.alec.spring.rest.rest_api.repository.IReadSaveFile;

import com.alec.spring.rest.rest_api.service.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


@RunWith(JUnit4.class)
public class ActionTest {
    @Test
    public void showAllTest() {
        Actions actions = new Actions(new XmlReader());
        Map<Long, Task> map = new TreeMap<>();
        Task task = new Task("A", "S", LocalDate.now(), Status.NEW, 5);
        Actions action = new Actions(new XmlReader()) {

            @Override
            public Map<Long, Task> getUpdatedTaskMap(IReadSaveFile readSaveFile) {
                return map;
            }};
        Assert.assertTrue(map.isEmpty());
        map.put(1L, new Task("A", "S", LocalDate.now(), Status.NEW, 5));
        Task task1 = (Task) (map.get(1L));
        Assert.assertEquals("A", task.getCaption());
        Assert.assertEquals("S", task.getDescription());
        Assert.assertEquals(Status.NEW, task.getStatus());
        Assert.assertEquals(5, task.getPriority());
    }
    @Test
    public void deleteTest() throws ParserConfigurationException, TransformerException {
        Map<Long, Task> map = new TreeMap<>();
        Map<String, Actions> actions = new HashMap<>();

        map.put(1L, new Task("A", "S", LocalDate.now(), Status.NEW, 5));
        Actions action = new Actions(new XmlReader()) {
            @Override
            public Map<Long, Task> getUpdatedTaskMap(IReadSaveFile readSaveFile) {
                return map;
            }};
        Assert.assertNotNull(action.getToDoMap(StatusCheck.NEW));
        Actions actions1 = new ActionsDeleteTask(new XmlReader()){
            @Override
            public Map<Long, Task> getUpdatedTaskMap(IReadSaveFile readSaveFile) {
                return map;
            }
        };
        actions1.setToDoMap(1L);
        Assert.assertTrue(map.isEmpty());
    }
    @Test
    public void showAllByStatusTest() {
        Map<Long, Task> map = new TreeMap<>();
        map.put(1L, new Task("A", "S", LocalDate.now(), Status.NEW, 5));
        map.put(2L, new Task("B", "S", LocalDate.now(), Status.NEW, 5));
        map.put(3L, new Task("C", "S", LocalDate.now(), Status.NEW, 5));
        Actions action = new Actions(new XmlReader()) {
            @Override
            public Map<Long, Task> getUpdatedTaskMap(IReadSaveFile readSaveFile) {
                return map;
            }
        };
        action.getToDoMap(StatusCheck.NEW);
        Assert.assertTrue(map.size() == 3);
    }
    @Test
    public void updateTaskTest() throws ParserConfigurationException, TransformerException {
        Map<Long, Task> map = new TreeMap<>();
       Task task = new Task("A", "S", LocalDate.now(), Status.NEW, 5);
       map.put(1L,task);
        Actions actions = new ActionsUpdateTask(new XmlReader()) {
            @Override
            public Map<Long, Task> getUpdatedTaskMap(IReadSaveFile readSaveFile) {
                return map;
            }
        };
        Task task1 = (Task) (actions.getToDoMap(StatusCheck.NEW).get(1L));
        Assert.assertEquals("S", task.getDescription());
        actions.setToDoMap(new Task("A", "W", LocalDate.now(), Status.NEW, 2));
       Task task2 = (Task) (actions.getToDoMap(StatusCheck.NEW).get(1L));
       Assert.assertEquals("W", task2.getDescription());
    }
    @Test
    public void newTaskTest() throws ParserConfigurationException, TransformerException {
        Map<Long, Task> map1 = new TreeMap<>();
        Actions action = new Actions(new XmlReader()) {
            @Override
            public Map<Long, Task> getUpdatedTaskMap(IReadSaveFile readSaveFile) {
                return map1;
            }
        };
        Assert.assertFalse(map1.containsKey(1L));
       action.setToDoMap(new Task("A", "S", LocalDate.now(), Status.NEW, 2));
        Assert.assertTrue(map1.containsKey(1L));
    }}


