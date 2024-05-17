package org.example.DAO;

import org.example.entity.Categorie;
import org.example.entity.Ingredient;
import org.example.utils.DataBaseManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CategorieDAO extends BaseDAO<Categorie> {
    @Override
    public Categorie save(Categorie element) throws SQLException {
        try{
            connection = DataBaseManager.getConnection();
            request = "INSERT INTO categorie (nom) VALUE (?)";
            preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, element.getNom());
            int nbrow = preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            Categorie categorie = null;
            if(resultSet.next()){
                categorie = Categorie.builder().nom(element.getNom()).build();
            }
            if(nbrow != 1){
                connection.rollback();
            }
            connection.commit();
            return categorie;
        }catch(SQLException e){
            connection.rollback();
            return null;
        }finally {
            close();
        }
    }

    @Override
    public Categorie update(Categorie element) throws SQLException {
        try {
            connection = DataBaseManager.getConnection();
            request = "UPDATE Categorie SET nom = ? WHERE id = ?";
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
    public boolean delete(Categorie element) throws SQLException {
        return false;
    }

    @Override
    public Categorie get(int id) throws SQLException {
        try {
            connection = DataBaseManager.getConnection();
            request = "SELECT * FROM categorie WHERE id = ?";
            preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Categorie.builder()
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
    public List<Categorie> get() throws SQLException {
        return null;
    }
}
