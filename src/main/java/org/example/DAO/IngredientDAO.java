package org.example.DAO;

import org.example.entity.Ingredient;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class IngredientDAO extends BaseDAO<Ingredient> {

    protected IngredientDAO() {

    }

    @Override
    public Ingredient save(Ingredient element) throws SQLException {
        try{
            request = "INSERT INTO ingredient (nom) VALUE (?)";
            preparedStatement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, element.getNom());
            int nbrow = preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            Ingredient ingredient = null;
            if(resultSet.next()){
                ingredient = Ingredient.builder().nom(element.getNom()).build();
            }
            if(nbrow != 1){
                _connection.rollback();
            }
            _connection.commit();
            return ingredient;
        }catch(SQLException e){
            _connection.rollback();
            return null;
        }
    }

    @Override
    public Ingredient update(Ingredient element) throws SQLException {
        try{
            request = "UPDATE Ingredient SET nom = ? WHERE id = ?";
            statement = _connection.prepareStatement(request);
            statement.setString(1,element.getTitre());
            statement.setString(2,element.getRealisateur());
            statement.setDate(3,Date.valueOf(element.getDateSortie()));
            statement.setString(4, element.getGenre());
            statement.setInt(5,element.getId());
            if(statement.executeUpdate() == 1) {
                connection.commit();
                return element;
            }
            throw new SQLException();
        }catch (SQLException e){
            connection.rollback();
            return null;
        }finally {
            close();
        }
    }

    @Override
    public boolean delete(Ingredient element) throws SQLException {
        return false;
    }

    @Override
    public Ingredient get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Ingredient> get() throws SQLException {
        return null;
    }
}
