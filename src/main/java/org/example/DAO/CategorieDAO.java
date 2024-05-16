package org.example.DAO;

import org.example.entity.Categorie;
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
        return null;
    }

    @Override
    public boolean delete(Categorie element) throws SQLException {
        return false;
    }

    @Override
    public Categorie get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Categorie> get() throws SQLException {
        return null;
    }
}
