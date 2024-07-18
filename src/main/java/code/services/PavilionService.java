package code.services;

import java.util.List;

import code.core.Repository;
import code.core.Service;

import code.data.entities.Pavilion;

public class PavilionService implements Service<Pavilion> {
    Repository<Pavilion> pavilionRepository;

    public PavilionService(Repository<Pavilion> pavilionRepository) {
        this.pavilionRepository = pavilionRepository;
    }

    @Override
    public void save(Pavilion objet) {
        pavilionRepository.insert(objet);
    }

    @Override
    public Pavilion find(int id) {
        return pavilionRepository.getById(id);
    }

    @Override
    public List<Pavilion> show() {
        return pavilionRepository.selectAll();
    }

    @Override
    public void update(Pavilion objet) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void update1(Pavilion objet) {
        pavilionRepository.update1(objet);
    }

    @Override
    public void affect(Pavilion objet) {
        throw new UnsupportedOperationException("Unimplemented method 'affect'");
    }

    @Override
    public List<Pavilion> selectBy(int x) {
        throw new UnsupportedOperationException("Unimplemented method 'selectBy'");
    }

}