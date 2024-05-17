package org.example.DAO;

import org.example.entity.Commentaire;
import org.example.entity.Ingredient;
import org.example.utils.DataBaseManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CommentaireDAO extends BaseDAO<Commentaire> {
    @Override
    public Commentaire save(Commentaire element) throws SQLException {
        try{
            connection = DataBaseManager.getConnection();
            request = "INSERT INTO commentaire (description) VALUE (?)";
            preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, element.getDescription());
            int nbrow = preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            Commentaire commentaire = null;
            if(resultSet.next()){
                commentaire = Commentaire.builder()
                        .description(element.getDescription())
                        .build();
            }
            if(nbrow != 1){
                connection.rollback();
            }
            connection.commit();
            return commentaire;
        }catch(SQLException e){
            connection.rollback();
            return null;
        }finally {
            close();
        }
    }

    @Override
    public Commentaire update(Commentaire element) throws SQLException {

        try {
            connection = DataBaseManager.getConnection();
            request = "UPDATE Commentaire SET description = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(1, element.getDescription());
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
    public boolean delete(Commentaire element) throws SQLException {
        return false;
    }

    @Override
    public Commentaire get(int id) throws SQLException {
        try {
            connection = DataBaseManager.getConnection();
            request = "SELECT * FROM commentaire WHERE id = ?";
            preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Commentaire.builder()
                        .id(resultSet.getInt("id"))
                        .description(resultSet.getString("description"))
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
    public List<Commentaire> get() throws SQLException {
        return null;
    }
}
