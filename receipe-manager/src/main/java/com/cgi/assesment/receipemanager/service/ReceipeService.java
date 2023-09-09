package com.cgi.assesment.receipemanager.service;

import com.cgi.assesment.receipemanager.model.Receipe;

import java.io.IOException;
import java.util.List;

public interface ReceipeService {
    List<Receipe> loadDataFromJsonFile(String jsonFilePath) throws IOException;

    List<Receipe> getAllReceipes();

    List<String> getAllIngredients();

    List<Receipe> getReceipeByIngredients(List<String> ingredients);
}
