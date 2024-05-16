package org.example.utils.ihm;

import org.example.DAO.RecetteDAO;
import org.example.entity.Difficulte;
import org.example.entity.Recette;

import java.sql.SQLException;
import java.util.Scanner;

public class IhmRecette {

    Scanner scanner;
    private RecetteDAO recetteDAO;

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

    private void updateRecette() {
        System.out.println("-- Modification d'une recette --");
        System.out.println("Tapez l'id de la recette :");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            Recette recette = recetteDAO.get(id);

            if (recette != null) {

                System.out.println("Nom actuel de la recette : " + recette.getNom());
                System.out.println("Entrez le nouveau nom de la recette :");
                String newNom = scanner.nextLine();

                System.out.println("Temps de préparation actuel de la recette : " + recette.getTempsPrep());
                System.out.println("Entrez le nouveau temps de préparation en minutes :");
                int newTempsPrep = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Temps de cuisson actuel de la recette : " + recette.getTempsCuisson());
                System.out.println("Entrez le nouveau temps de cuisson en minutes :");
                int newTempsCuisson = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Difficulté actuelle de la recette : " + recette.getDifficulte());

                IhmDIfficulte ihmDIfficulte = new IhmDIfficulte(scanner);
                ihmDIfficulte.start();
                Difficulte newDifficulte = ihmDIfficulte.getDifficulteChoisie();

                recette.setDifficulte(newDifficulte);
                recette.setNom(newNom);
                recette.setTempsPrep(newTempsPrep);
                recette.setTempsCuisson(newTempsCuisson);

                try {
                    if (recetteDAO.update(recette) != null) {
                        System.out.println("Rcette mise à jour avec succès");
                    } else {
                        System.out.println("La mise à jour de la recette a échouée");
                    }

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
        System.out.println("-- Création d'une recette' --");
        System.out.println("Tapez le nom de la recette :");
        String nom = scanner.nextLine();

        try {
            Recette recette = recetteDAO.save(Recette.builder().nom(nom).build());

            if (recette != null) {
                System.out.println("Ingrédient a été créé avec succès " + recette);
            } else {
                System.out.println("Erreur lors de la creation de la recette");
            }

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
