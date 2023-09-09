package com.cgi.assesment.receipemanager.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ReceipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long receipeId;
    private String title;
    private String href;
    @ElementCollection
    private List<String> ingredients;
    private String thumbnail;

}
