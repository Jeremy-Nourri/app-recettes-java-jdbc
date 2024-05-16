package org.example.entity;

import lombok.Builder;
import lombok.Data;
@Builder
@Data

public class Ingredient {
    private int id;
    private String nom;
}
