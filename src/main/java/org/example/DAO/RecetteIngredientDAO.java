package org.example.DAO;

import org.example.entity.RecetteIngredient;

import java.sql.SQLException;
import java.util.List;

public class RecetteIngredientDAO extends BaseDAO<RecetteIngredient> {
    @Override
    public RecetteIngredient save(RecetteIngredient element) throws SQLException {
        return null;
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
        return null;
    }
}
