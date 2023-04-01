package cz.osu.javamessboardapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;
import java.io.Serializable;

public interface PagingAndSortingRepository<T, ID extends Serializable> extends CrudRepository<T, ID>
{
    Iterable<T> findAll(Sort sort);

    Page<T> findAll(Pageable pageable);
}
