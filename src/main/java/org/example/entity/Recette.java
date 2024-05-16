package org.example.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data

public class Recette {
    private int id;
    private String nom;
    private int tempsPrep;
    private int tempsCuisson;
    private Difficulte difficulte;
    private List<Etape> listeEtapes;
    private List<Commentaire> listeCommentaires;
    private List<Categorie> listeCategories;
}
