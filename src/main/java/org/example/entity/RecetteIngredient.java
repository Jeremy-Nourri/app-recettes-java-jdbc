package org.example.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class RecetteIngredient {
    private int id;
    private int id_recette;
    private int id_ingredient;
}
