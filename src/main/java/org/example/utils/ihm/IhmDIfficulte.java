package org.example.utils.ihm;

import lombok.Getter;
import org.example.entity.Difficulte;

import java.sql.SQLException;
import java.util.Scanner;

public class IhmDIfficulte {
    Scanner scanner;

    @Getter
    private Difficulte difficulteChoisie;
    public IhmDIfficulte(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() throws SQLException {
        int entry;

        do {
            System.out.println("Choisissez la difficulté de la recette :");
            for (int i = 0; i < Difficulte.values().length; i++) {
                System.out.println(i + ". " + Difficulte.values()[i].name());
            }

            System.out.println("Entrez le numéro correspondant à la difficulté :");
            entry = scanner.nextInt();

            if (entry < 0 || entry >= Difficulte.values().length) {
                System.out.println("Choix invalide.");
            } else {
                difficulteChoisie = Difficulte.values()[entry];
                System.out.println("Vous avez choisi la difficulté : " + difficulteChoisie);
            }
        } while (entry < 0 || entry >= Difficulte.values().length);
    }

    }
