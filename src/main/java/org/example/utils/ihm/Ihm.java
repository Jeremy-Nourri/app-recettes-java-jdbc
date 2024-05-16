package org.example.utils.ihm;
import java.sql.SQLException;
import java.util.Scanner;

public class Ihm {
    private Scanner scanner;
    private IhmIngredient ihmIngredient;
    private IhmCommentaire ihmCommentaire;
    private IhmEtape ihmEtape;
    private IhmRecette ihmRecette;

    public Ihm() {
        scanner = new Scanner(System.in);
        ihmIngredient = new IhmIngredient(scanner);
        ihmCommentaire = new IhmCommentaire();
        ihmEtape = new IhmEtape(scanner);
        ihmRecette = new IhmRecette(scanner);
    }

    public void start () throws SQLException {
        int entry;
        while(true){
            System.out.println("--- Application gestion de recette ---");
            System.out.println("1/ menu recette");
//            System.out.println("2/ menu meal");
//            System.out.println("3/ menu enclos");
            entry = scanner.nextInt();
            scanner.nextLine();

            switch (entry){
                case 1:
                    ihmIngredient.start();
                    break;
//                case 2:
//                    mealIhm.start();
//                    break;
//                case 3:
//                    enclosIhm.start();
//                    break;
                default:
                    return;
            }
        }
    }
}