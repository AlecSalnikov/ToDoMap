package com.alec.spring.rest.rest_api.Controller;
import com.alec.spring.rest.rest_api.exception.TaskNotFoundException;
import com.alec.spring.rest.rest_api.model.Status;
import com.alec.spring.rest.rest_api.model.StatusCheck;
import com.alec.spring.rest.rest_api.model.Task;
import com.alec.spring.rest.rest_api.repository.IReadSaveFile;
import com.alec.spring.rest.rest_api.service.Actions;
import com.alec.spring.rest.rest_api.service.ActionsMap;
import com.alec.spring.rest.rest_api.service.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.Map;
import java.util.TreeMap;

import static com.alec.spring.rest.rest_api.model.Status.*;
import static com.alec.spring.rest.rest_api.model.StatusCheck.ALL;

@RestController
@RequestMapping("/")
public class TaskController {
    ActionsMap actionsMap = new ActionsMap();
    Map<String, Actions> allActions = (actionsMap.ActionsMap());

    @GetMapping("/tasks")
    public ResponseEntity showAll() {
        return ResponseEntity.ok(allActions.get("Show/AddNew").getToDoMap(StatusCheck.ALL));
    }

    @PostMapping("/tasks")
    public ResponseEntity createEndDocument(@RequestBody Task task) throws ParserConfigurationException,
            TransformerException {
        if (task.getCaption().length() > 50 || task.getPriority() < 0 || task.getPriority() > 10) {
            return new ResponseEntity<>("This value is out of bound",
                    HttpStatus.BAD_REQUEST);
        }
        Long id = allActions.get("Show/AddNew").captionCheck(task);
        if (id==-1L) {
            allActions.get("Show/AddNew").setToDoMap(task);
            return ResponseEntity.ok("Task with name " +task.getCaption() + " is successfully added");
        } else return new ResponseEntity<>("This name is already use",
                HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/tasks/{statusCheck}")
    public ResponseEntity showStatus(@PathVariable StatusCheck statusCheck) throws TaskNotFoundException {
        if (statusCheck != StatusCheck.ALL && statusCheck != StatusCheck.IN_PROGRESS && statusCheck != StatusCheck.DONE&&
        statusCheck != StatusCheck.NEW) {
            return new ResponseEntity<>(
                    "Uncorrected value",
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(allActions.get("Show/AddNew").getToDoMap(statusCheck));
    }
    @PutMapping("/tasks")
    public ResponseEntity Update(@RequestBody Task task) throws
            ParserConfigurationException, TransformerException {
        allActions.get("Update").setToDoMap(task);
        return ResponseEntity.ok("Task with name " +task.getCaption() + " is update");
    }

    @DeleteMapping("/tasks")
    public ResponseEntity Delete(@RequestBody Task task) throws ParserConfigurationException,
            TransformerException {
        allActions.get("Delete").setToDoMap(task);
        return ResponseEntity.ok(" Task with name " + task.getCaption() + " is delete");
    }

    @PutMapping("/tasks/complete/")
    public ResponseEntity CompleteTask(@RequestBody Task task) throws ParserConfigurationException,
            TransformerException {
        allActions.get("Complete").setToDoMap(task);
        return ResponseEntity.ok("Task with name= " + task.getCaption() + " is complete");
    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity DeleteById(@PathVariable Long id) throws ParserConfigurationException,
            TransformerException {
        allActions.get("Delete").setToDoMap(id);
        return ResponseEntity.ok("with id " + id + " is delete");
}}

