package com.cgi.assesment.receipemanager.service;

import com.cgi.assesment.receipemanager.exception.ReceipeException;
import com.cgi.assesment.receipemanager.model.Receipe;
import com.cgi.assesment.receipemanager.persistance.entity.ReceipeEntity;
import com.cgi.assesment.receipemanager.persistance.repository.ReceipeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.cgi.assesment.receipemanager.constants.Constants.RECEIPE_RETRIEVED;
import static com.cgi.assesment.receipemanager.exception.ReceipeExceptionCodes.INVALID_INPUT;
import static com.cgi.assesment.receipemanager.exception.ReceipeExceptionCodes.RECEIPE_NOT_FOUND;

@Service
@Slf4j
public class ReceipeServiceImpl implements ReceipeService {

    private final ReceipeRepository receipeRepository;

    public ReceipeServiceImpl(ReceipeRepository receipeRepository) {
        this.receipeRepository = receipeRepository;
    }

    @Override
    // Method to load data from JSON file and save it to the database
    public List<Receipe> loadDataFromJsonFile(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ReceipeEntity> receipeEntityList = objectMapper.readValue(new File(jsonFilePath),
                                                                       new TypeReference<List<ReceipeEntity>>() {
                                                                       });
        log.info("Data retrieved from Json");
        receipeRepository.saveAll(receipeEntityList);
        log.info("Data from Json loaded to the database");
        return receipeEntityList.stream().filter(
                Objects::nonNull).map(this::mapToReceipeFromDB).sorted().collect(Collectors.toList());
    }

    @Override
    public List<Receipe> getAllReceipes() {
        List<ReceipeEntity> receipeEntityList = receipeRepository.findAll();

        log.info(RECEIPE_RETRIEVED);

        return Optional.ofNullable(receipeEntityList)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new ReceipeException(RECEIPE_NOT_FOUND,
                                                        "No Receipe Available")).stream().filter(
                        Objects::nonNull).map(this::mapToReceipeFromDB).sorted().collect(Collectors.toList());
    }

    public List<String> getAllIngredients() {

        return receipeRepository.findAll().stream().flatMap(
                receipeEntityList -> receipeEntityList.getIngredients().stream()).filter(
                Objects::nonNull).distinct().collect(
                Collectors.toList());
    }

    @Override
    public List<Receipe> getReceipeByIngredients(List<String> ingredients) {

        ingredients.stream()
                .filter(this::containsNonStringValues).findFirst()
                .ifPresent(ingredient -> {
                    throw new ReceipeException(INVALID_INPUT, "Ingredients should be String : " + ingredient);
                });

        List<ReceipeEntity> receipeEntityList = receipeRepository.findByIngredientsContainingAll(ingredients);

        log.info(RECEIPE_RETRIEVED);

        return Optional.ofNullable(receipeEntityList)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new ReceipeException(RECEIPE_NOT_FOUND,
                                                        "Receipe not available for the selected Ingredients"))
                .stream()
                .filter(Objects::nonNull)
                .map(this::mapToReceipeFromDB)
                .sorted()
                .collect(Collectors.toList());
    }

    private boolean containsNonStringValues(String ingredient) {
        return ingredient.chars().noneMatch(Character::isLetter);
    }

    public Receipe mapToReceipeFromDB(ReceipeEntity receipeEntity) {

        return Receipe.builder().title(
                receipeEntity.getTitle()).href(receipeEntity.getHref()).ingredients(
                receipeEntity.getIngredients()).thumbnail(receipeEntity.getThumbnail()).build();

    }


}
