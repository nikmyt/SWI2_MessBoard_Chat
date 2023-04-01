package cz.osu.javamessboardapp.repository;

import org.springframework.data.repository.Repository;

import java.io.Serializable;

public interface CrudeRepositoryInterface<T, ID extends Serializable> extends Repository<T, ID>
{
    <S extends T> S save(S entity);

    T findOne(ID primaryKey);

    Iterable<T> findAll();

    Long count();

    void delete(T entity);
                                                                                                                   boolean exists(ID primaryKey);
}
