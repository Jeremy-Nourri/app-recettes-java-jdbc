package org.example.DAO;

import org.example.entity.Recette;
import org.example.utils.DataBaseManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class RecetteDAO extends BaseDAO<Recette>{
    @Override
    public Recette save(Recette element) throws SQLException {
        try{
            connection = DataBaseManager.getConnection();
            request = "INSERT INTO etape (nom, tempsPrep, tempsCuisson, difficulte, categorie) VALUE (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, element.getNom());
            preparedStatement.setInt(2, element.getTempsPrep());
            preparedStatement.setInt(3, element.getTempsCuisson());
            preparedStatement.setString(4, String.valueOf(element.getDifficulte()));
            preparedStatement.setString(5, String.valueOf(element.getCategorie()));
            int nbrow = preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            Recette recette = null;
            if(resultSet.next()){
                recette = Recette.builder()
                        .nom(element.getNom())
                        .tempsPrep(element.getTempsPrep())
                        .tempsCuisson(element.getTempsCuisson())
                        .difficulte(element.getDifficulte())
                        .categorie(element.getCategorie())
                        .id(resultSet.getInt(1))
                        .build();
            }
            if(nbrow != 1){
                connection.rollback();
            }
            connection.commit();
            return recette;
        }catch(SQLException e){
            connection.rollback();
            return null;
        }finally {
            close();
        }
    }

    @Override
    public Recette update(Recette element) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Recette element) throws SQLException {

        return false;
    }

    @Override
    public Recette get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Recette> get() throws SQLException {
        return null;
    }
}
