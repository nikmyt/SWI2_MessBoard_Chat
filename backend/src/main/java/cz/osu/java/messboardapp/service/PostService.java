package cz.osu.java.messboardapp.service;

import cz.osu.java.messboardapp.model.BoardPost;
import cz.osu.java.messboardapp.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService implements PostRepository
{

    public final PostRepository postRepo;
    @Override
    public <S extends BoardPost> S save(S entity) {
        return postRepo.save(entity);
    }



    @Override
    public Iterable<BoardPost> findAll() {
        return postRepo.findAll();
    }

    @Override
    public Long count() {
        return postRepo.count();
    }


    @Override
    public void delete(BoardPost entity) {

    }

    @Override
    public boolean existsByPostId(Integer primaryKey) {
        return false;
    }

    @Override
    public BoardPost findByPostId(Integer id)
    {
        return postRepo.findByPostId(id);
    }


}
