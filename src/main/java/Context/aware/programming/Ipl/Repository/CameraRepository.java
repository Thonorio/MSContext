package Context.aware.programming.Ipl.Repository;

import Context.aware.programming.Ipl.Entity.Camara;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CameraRepository implements CrudRepository<Camara, Long> {


    @Override
    public <S extends Camara> S save(S s) {
        return null;
    }

    @Override
    public <S extends Camara> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Camara> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Camara> findAll() {
        return null;
    }

    @Override
    public Iterable<Camara> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Camara camara) {

    }

    @Override
    public void deleteAll(Iterable<? extends Camara> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
