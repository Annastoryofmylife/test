package com.cgi.assesment.receipemanager.controller;

import com.cgi.assesment.receipemanager.constants.Constants;
import com.cgi.assesment.receipemanager.exception.ReceipeException;
import com.cgi.assesment.receipemanager.model.Receipe;
import com.cgi.assesment.receipemanager.service.ReceipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping
@Slf4j
public class ReceipeController {

    private final ReceipeService receipeService;

    public ReceipeController(ReceipeService receipeService) {
        this.receipeService = receipeService;
    }

    @PostMapping("/load")
    public ResponseEntity<List<Receipe>> loadItemsFromJsonFile(
            @RequestParam(required = true) String jsonFilePath) throws IOException {
        log.info(Constants.RECEIPE_SERVICE_CALL);
        List<Receipe> receipeList = receipeService.loadDataFromJsonFile(jsonFilePath);
        return ResponseEntity.status(HttpStatus.CREATED).body(receipeList);
    }

    @GetMapping("/getAllReceipes")
    public ResponseEntity<List<Receipe>> getAllReceipes() {
        log.info(Constants.RECEIPE_SERVICE_CALL);
        List<Receipe> receipeList = receipeService.getAllReceipes();
        return ResponseEntity.status(HttpStatus.OK).body(receipeList);

    }

    @GetMapping("/getAllIngredients")
    public ResponseEntity<List<String>> getAllIngredients() {
        log.info(Constants.RECEIPE_SERVICE_CALL);
        List<String> allIngredients = receipeService.getAllIngredients();
        return ResponseEntity.status(HttpStatus.OK).body(allIngredients);

    }

    @GetMapping("/getRecipesByIngredients")
    public ResponseEntity<List<Receipe>> getRecipesByIngredients(
            @RequestBody(required = true) List<String> ingredients) {

        log.info(Constants.RECEIPE_SERVICE_CALL);
        List<Receipe> receipeList = receipeService.getReceipeByIngredients(ingredients);
        return ResponseEntity.status(HttpStatus.OK).body(receipeList);
    }

}
