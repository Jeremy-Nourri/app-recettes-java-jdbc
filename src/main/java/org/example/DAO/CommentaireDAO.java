package org.example.DAO;

import org.example.entity.Commentaire;

import java.sql.SQLException;
import java.util.List;

public class CommentaireDAO extends BaseDAO<Commentaire> {
    @Override
    public Commentaire save(Commentaire element) throws SQLException {
        return null;
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
