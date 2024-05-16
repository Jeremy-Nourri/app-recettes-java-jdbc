package org.example.DAO;

import org.example.entity.Etape;
import org.example.entity.Ingredient;
import org.example.utils.DataBaseManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EtapeDAO extends BaseDAO<Etape> {
    @Override
    public Etape save(Etape element) throws SQLException {
        try{
            connection = DataBaseManager.getConnection();
            request = "INSERT INTO etape (nom) VALUE (?)";
            preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, element.getDescription());
            int nbrow = preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            Etape etape = null;
            if(resultSet.next()){
                etape = Etape.builder()
                        .description(element.getDescription())
                        .build();
            }
            if(nbrow != 1){
                connection.rollback();
            }
            connection.commit();
            return etape;
        }catch(SQLException e){
            connection.rollback();
            return null;
        }finally {
            close();
        }
    }

    @Override
    public Etape update(Etape element) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Etape element) throws SQLException {
        return false;
    }

    @Override
    public Etape get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Etape> get() throws SQLException {
        return null;
    }
}
