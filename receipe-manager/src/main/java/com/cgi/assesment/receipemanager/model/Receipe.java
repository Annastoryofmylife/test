package com.cgi.assesment.receipemanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Receipe implements Comparable<Receipe> {

    private String title;
    private String href;
    private List<String> ingredients;
    private String thumbnail;

    @Override
    public int compareTo(Receipe receipe) {
        return getTitle().compareTo(receipe.getTitle());
    }
}
