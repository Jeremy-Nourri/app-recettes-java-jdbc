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
        try {
            connection = DataBaseManager.getConnection();
            request = "UPDATE Etape SET description = ? WHERE id = ?";
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
    public boolean delete(Etape element) throws SQLException {
        try {
            connection = DataBaseManager.getConnection();
            request = "DELETE FROM etape WHERE id = ?";
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
    public Etape get(int id) throws SQLException {
        try {
            connection = DataBaseManager.getConnection();
            request = "SELECT * FROM etape WHERE id = ?";
            preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Etape.builder()
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
    public List<Etape> get() throws SQLException {
        return null;
    }
}
