package org.example.DAO;

import org.example.entity.Ingredient;

import org.example.utils.DataBaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class IngredientDAO extends BaseDAO<Ingredient> {

    public IngredientDAO() {

    }

    @Override
    public Ingredient save(Ingredient element) throws SQLException {

        try {
            connection = DataBaseManager.getConnection();
            request = "INSERT INTO ingredient (nom) VALUE (?)";
            preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, element.getNom());
            int nbrow = preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            Ingredient ingredient = null;

            if (nbrow == 1) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    ingredient = Ingredient.builder()
                            .id(generatedId)
                            .nom(element.getNom())
                            .build();
                }
            }

            if (ingredient == null) {
                connection.rollback();
                return null;
            }

            connection.commit();
            return ingredient;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
            return null;

        } finally {
            close();
        }
    }

    @Override
    public Ingredient update(Ingredient element) throws SQLException {

        try {
            connection = DataBaseManager.getConnection();
            request = "UPDATE Ingredient SET nom = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(1, element.getNom());
            preparedStatement.setInt(2, element.getId());

            if (preparedStatement.executeUpdate() == 1) {
                connection.commit();
                return element;
            }
            throw new SQLException();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
            return null;

        } finally {
            close();
        }
    }

    @Override
    public Ingredient get(int id) throws SQLException {

        try {
            connection = DataBaseManager.getConnection();
            request = "SELECT * FROM ingredient WHERE id = ?";
            preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Ingredient.builder()
                        .id(resultSet.getInt("id"))
                        .nom(resultSet.getString("nom"))
                        .build();
            }
            return null;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;

        } finally {
            close();
        }
    }

    @Override
    public boolean delete(Ingredient element) throws SQLException {
        try {
            connection = DataBaseManager.getConnection();
            request = "DELETE FROM ingredient WHERE id = ?";
            preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1, element.getId());

            if (preparedStatement.executeUpdate() == 1) {
                connection.commit();
                return true;
            }
            throw new SQLException();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
            return false;

        } finally {
            close();
        }
    }
    @Override
    public List<Ingredient> get() throws SQLException {
        return null;
    }
}
