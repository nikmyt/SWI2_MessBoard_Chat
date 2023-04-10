package cz.osu.java.messboardapp.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

@NoRepositoryBean
public interface CrudeRepositoryInterface<T, ID extends Serializable> extends Repository<T, ID>
{
    <S extends T> S save(S entity);



    Iterable<T> findAll();

    Long count();


    void delete(T entity);
    //boolean exists(ID primaryKey);
}
