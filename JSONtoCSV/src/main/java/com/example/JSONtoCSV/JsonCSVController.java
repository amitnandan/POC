package com.example.JSONtoCSV;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JsonCSVController {



    private JsonCsvService jsonCsvService;

    public JsonCSVController(JsonCsvService jsonCsvService) {
        this.jsonCsvService = jsonCsvService;
    }

    @PostMapping("/convertToJson")
    public ResponseEntity<String> jsonTOCsv (@RequestBody List<JsonEntity> jsonData) throws JsonProcessingException {


        jsonCsvService.convertJsonToCsv(jsonData);


        return new ResponseEntity<>("String", HttpStatus.OK);
    }
}
