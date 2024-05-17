package org.example.utils.ihm;

import org.example.DAO.EtapeDAO;
import org.example.entity.Etape;

import java.sql.SQLException;
import java.util.Scanner;

public class IhmEtape {
    Scanner scanner;
    private final EtapeDAO etapeDAO;

    public IhmEtape(Scanner scanner) {
        this.scanner = scanner;
        etapeDAO = new EtapeDAO();
    }

    public void start() throws SQLException {
        int entry;
        while (true) {
            System.out.println("--- Menu étape ---");
            System.out.println("1/ Créer une étape");
            System.out.println("2/ Modifier une étape");
            System.out.println("3/ Supprimer une étape");

            entry = scanner.nextInt();
            scanner.nextLine();

            switch (entry) {
                case 1:
                    createIngredient();
                    break;
                case 2:
                    updateEtape();
                    break;
                case 3:
                    deleteIngredient();
                    break;
                default:
                    return;
            }
        }
    }

    private void updateEtape() {
        System.out.println("-- Modification d'une étape --");
        System.out.println("Tapez l'id de l'étape :");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            Etape etape = etapeDAO.get(id);
            if (etape != null) {
                System.out.println("Description actuelle de l'étape : " + etape.getDescription());
                System.out.println("Entrez la nouvelle description :");
                String newDescription = scanner.nextLine();
                etape.setDescription(newDescription);

                try {
                    if (etapeDAO.update(etape) != null) {
                        System.out.println("L'étape a été mise à jour avec succès");
                    } else {
                        System.out.println("La mise à jour de l'étape a échouée");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException("Erreur lors de la mise à jour de l'étape", e);
                }
            } else {
                System.out.println("Aucune étape n'a été trouvée avec cet id");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de l'étape", e);
        }
    }

    private void createIngredient() throws SQLException {
        System.out.println("-- Création d'une étape' --");
        System.out.println("Tapez la description de l'étape :");
        String description = scanner.nextLine();

        try {
            Etape etape = etapeDAO.save(Etape.builder().description(description).build());

            if (etape != null) {
                System.out.println("Ingrédient a été créé avec succès " + etape);
            } else {
                System.out.println("Erreur lors de la creation de l'ingrédient");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteIngredient() throws SQLException {
        System.out.println("-- Suppression d'une étape' --");
        System.out.println("Tapez l'id de l'étape :");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
           Etape etape = etapeDAO.get(id);
            if (etapeDAO.delete(etape)) {
                System.out.println("L'étape a été supprimée avec succès");
            } else {
                System.out.println("Erreur lors de la suppression de l'étape");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
