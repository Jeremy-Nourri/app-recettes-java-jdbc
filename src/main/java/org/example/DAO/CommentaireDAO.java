package org.example.DAO;

import org.example.entity.Commentaire;
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
        return null;
    }

    @Override
    public boolean delete(Commentaire element) throws SQLException {
        return false;
    }

    @Override
    public Commentaire get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Commentaire> get() throws SQLException {
        return null;
    }
}
