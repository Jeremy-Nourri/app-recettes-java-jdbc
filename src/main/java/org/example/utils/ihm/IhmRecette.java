package org.example.utils.ihm;

import org.example.DAO.RecetteDAO;
import org.example.entity.Categorie;
import org.example.entity.Difficulte;
import org.example.entity.Recette;

import java.sql.SQLException;
import java.util.Scanner;

public class  IhmRecette {

    Scanner scanner;
    private final RecetteDAO recetteDAO;

    public IhmRecette(Scanner scanner) {
        this.scanner = scanner;
        recetteDAO = new RecetteDAO();
    }

    public void start() throws SQLException {
        int entry;
        while (true) {
            System.out.println("--- Menu recette ---");
            System.out.println("1/ Créer une recette");
            System.out.println("2/ Modifier une recette");
            System.out.println("3/ Supprimer une recette");

            entry = scanner.nextInt();
            scanner.nextLine();

            switch (entry) {
                case 1:
                    createRecette();
                    break;
                case 2:
                    updateRecette();
                    break;
                case 3:
                    deleteRecette();
                    break;
                default:
                    return;
            }
        }
    }

    private void updateRecette() throws SQLException {
        System.out.println("-- Modification d'une recette --");
        System.out.println("Tapez l'id de la recette :");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            Recette recette = recetteDAO.get(id);

            if (recette != null) {

                String nom;
                int tempsPrep;
                int tempsCuisson;

                do {
                    System.out.println("Tapez le nom de la recette :");
                    nom = scanner.nextLine();
                    if (nom.isEmpty()) {
                        System.out.println("Le nom ne peut pas être vide");
                    }
                } while (nom.isEmpty());

                do {
                    System.out.println("Tapez le temps de préparation en minutes :");
                    tempsPrep = scanner.nextInt();
                    scanner.nextLine();
                    if (tempsPrep < 0) {
                        System.out.println("Le temps de préparation doit être positif");
                    }
                } while (tempsPrep < 0);

                do {
                    System.out.println("Tapez le temps de cuisson en minutes :");
                    tempsCuisson = scanner.nextInt();
                    scanner.nextLine();
                    if (tempsCuisson < 0) {
                        System.out.println("Le temps de cuisson doit être positif");
                    }
                } while (tempsCuisson < 0);

                IhmDIfficulte ihmDIfficulte = new IhmDIfficulte(scanner);
                ihmDIfficulte.start();

                Difficulte difficulte = ihmDIfficulte.getDifficulteChoisie();

                IhmCategorie ihmCategorie = new IhmCategorie(scanner);

                Categorie categorie = ihmCategorie.createCategorie();

                try {
                    Recette newRecette = recetteDAO.update(Recette.builder()
                            .id(id)
                            .nom(nom)
                            .tempsPrep(tempsPrep)
                            .tempsCuisson(tempsCuisson)
                            .categorie(categorie)
                            .difficulte(difficulte)
                            .build());
                    System.out.println("Recette modifiée : " + newRecette);

                } catch (SQLException e) {
                    throw new RuntimeException("Erreur lors de la mise à jour de la recette", e);
                }

            } else {
                System.out.println("Aucune recette trouvée avec cet id");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de la recette", e);
        }
    }

    private void createRecette() throws SQLException {

        String nom;
        int tempsPrep;
        int tempsCuisson;
        Categorie categorie;
        Difficulte difficulte;

        System.out.println("-- Création d'une recette --");

        do {
            System.out.println("Tapez le nom de la recette :");
            nom = scanner.nextLine();
            if (nom.isEmpty()) {
                System.out.println("Le nom ne peut pas être vide");
            }
        } while (nom.isEmpty());

        do {
            System.out.println("Tapez le temps de préparation en minutes :");
            tempsPrep = scanner.nextInt();
            scanner.nextLine();
            if (tempsPrep < 0) {
                System.out.println("Le temps de préparation doit être positif");
            }
        } while (tempsPrep < 0);

        do {
            System.out.println("Tapez le temps de cuisson en minutes :");
            tempsCuisson = scanner.nextInt();
            scanner.nextLine();
            if (tempsCuisson < 0) {
                System.out.println("Le temps de cuisson doit être positif");
            }
        } while (tempsCuisson < 0);

        IhmDIfficulte ihmDIfficulte = new IhmDIfficulte(scanner);
        ihmDIfficulte.start();

        difficulte = ihmDIfficulte.getDifficulteChoisie();

        IhmCategorie ihmCategorie = new IhmCategorie(scanner);

        categorie = ihmCategorie.createCategorie();

        try {
            Recette recette = recetteDAO.save(Recette.builder()
                    .nom(nom)
                    .tempsPrep(tempsPrep)
                    .tempsCuisson(tempsCuisson)
                    .categorie(categorie)
                    .difficulte(difficulte)
                    .build());
            System.out.println("Recette créée : " + recette);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteRecette() throws SQLException {
        System.out.println("-- Suppression d'une recette' --");
        System.out.println("Tapez l'id de la recette :");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            Recette recette = recetteDAO.get(id);
            if (recetteDAO.delete(recette)) {
                System.out.println("L'la recette a été supprimé avec succès");
            } else {
                System.out.println("Erreur lors de la suppression");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
