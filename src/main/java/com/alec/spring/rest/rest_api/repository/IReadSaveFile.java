package com.alec.spring.rest.rest_api.repository;

import com.alec.spring.rest.rest_api.model.Task;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.Map;

public interface IReadSaveFile {

    Map readFile();

    Map saveFile(Map<Long, Task> taskMap) throws ParserConfigurationException, TransformerException;
}
