package code.data.repositories;

import java.util.ArrayList;
import java.util.List;

import code.core.Repository;

import code.data.entities.Pavilion;

public class PavilionRepository implements Repository<Pavilion> {

    List<Pavilion> listPavilions = new ArrayList<>();

    @Override
    public boolean insert(Pavilion objet) {
        return listPavilions.add(objet);
    }

    @Override
    public Pavilion getById(int id) {
        for (Pavilion pavilion : listPavilions) {
            if (pavilion.getId() == id) {
                return pavilion;
            }

        }
        return null;

    }

    @Override
    public List<Pavilion> selectAll() {
        return listPavilions;
    }

    @Override
    public void update(Pavilion objet) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void update1(Pavilion pavilion) {
        if (pavilion != null) {
            System.out.println("[Edit executed!]");
        } else {
            System.out.println("[Edit non executed!]");
        }

    }

    @Override
    public void affect(Pavilion pavilion) {
        if (pavilion != null) {
            System.out.println("[Edit executed!]");
        } else {
            System.out.println("[Edit non executed!]");
        }
    }

    @Override
    public List<Pavilion> selectBy(int x) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}