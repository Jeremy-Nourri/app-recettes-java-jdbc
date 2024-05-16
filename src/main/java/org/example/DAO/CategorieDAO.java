package org.example.DAO;

import org.example.entity.Categorie;

import java.sql.SQLException;
import java.util.List;

public class CategorieDAO extends BaseDAO<Categorie> {
    @Override
    public Categorie save(Categorie element) throws SQLException {
        return null;
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
