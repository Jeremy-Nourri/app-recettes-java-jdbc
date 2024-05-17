package org.example.DAO;

import org.example.entity.Ingredient;
import org.example.entity.RecetteIngredient;
import org.example.utils.DataBaseManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecetteIngredientDAO extends BaseDAO<RecetteIngredient> {
    @Override
    public RecetteIngredient save(RecetteIngredient element) throws SQLException {
        try{
            connection = DataBaseManager.getConnection();
            request = "INSERT INTO recette_ingredient (id_recette, id_ingredient) VALUE (?,?)";
            preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, element.getId_recette());
            preparedStatement.setInt(2, element.getId_ingredient());
            int nbrow = preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if(nbrow != 1){
                connection.rollback();
            }
            connection.commit();
            return element;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            connection.rollback();
            return null;
        }finally {
            close();
        }
    }

    @Override
    public RecetteIngredient update(RecetteIngredient element) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(RecetteIngredient element) throws SQLException {
        return false;
    }

    @Override
    public RecetteIngredient get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<RecetteIngredient> get() throws SQLException {
        return List.of();
    }

    public List<Ingredient> AllIngredientsByRecetteId(int id_recette) throws SQLException {
        List<Ingredient> ingredients = new ArrayList<>();
        request ="SELECT * FROM ingredient INNER JOIN recette_ingredient ON ingredient.id = recette_ingredient.id_ingredient WHERE recette_ingredient.id_recette = ?";
        preparedStatement = connection.prepareStatement(request);
        preparedStatement.setInt(1, id_recette);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Ingredient ingredient = Ingredient.builder()
                    .id(resultSet.getInt("id"))
                    .nom(resultSet.getString("nom"))
                    .build();
            ingredients.add(ingredient);
        }
        return ingredients;
    }
    }

