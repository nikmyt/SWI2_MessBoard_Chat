package cz.osu.java.messboardapp;

import cz.osu.java.messboardapp.Form.CommentForm;
import cz.osu.java.messboardapp.model.BoardComment;
import cz.osu.java.messboardapp.model.BoardPost;
import cz.osu.java.messboardapp.model.BoardUser;
import cz.osu.java.messboardapp.repository.CommentRepository;
import cz.osu.java.messboardapp.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CommentServiceTesting {
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commentService=new CommentService(commentRepository);
    }

    @Test
    void testSave() {
        // Arrange
        CommentForm commentForm = new CommentForm();
        commentForm.assets("hint", new Date(), 1, 1, "user");
        BoardUser boardUser = new BoardUser();
        boardUser.setUserId(1);
        boardUser.setUsername("user");
        BoardPost post = new BoardPost();
        post.setPostId(1);
        BoardComment boardComment = new BoardComment();
        boardComment.setUser(boardUser);
        boardComment.setText("hint");
        boardComment.setCreatedAt(new Date());
        boardComment.setPost(post);
        boardComment.setId(1);


        commentService.save(commentForm, boardUser, post);
        verify(commentRepository, times(1)).save(any(BoardComment.class));
        //
        assertEquals(boardUser, boardComment.getUser());
        assertEquals(post, boardComment.getPost());

    }

    @Test
    void testDeleteComment() {

        BoardComment boardComment = new BoardComment();


        commentService.deleteComment(boardComment);


        verify(commentRepository, times(1)).delete(boardComment);
    }

    @Test
    void testUpdate() {

        BoardComment boardComment = new BoardComment();


        commentService.update(boardComment);


        verify(commentRepository, times(1)).save(boardComment);
    }

    @Test
    void testFindCommentsByPostId() {

        BoardPost boardPost = new BoardPost();
        ArrayList<BoardComment> comments = new ArrayList<>();
        when(commentRepository.findBoardCommentByPost(boardPost)).thenReturn(comments);


        Iterable<BoardComment> result = commentService.findCommentsByPostId(boardPost);


        verify(commentRepository, times(1)).findBoardCommentByPost(boardPost);
        assertEquals(comments, result);
    }

    @Test
    void testFindBoardCommentByCommentId() {

        int commentId = 1;
        Optional<BoardComment> expectedComment = Optional.of(new BoardComment());
        when(commentRepository.findBoardCommentById(commentId)).thenReturn(expectedComment);


        Optional<BoardComment> result = commentService.findBoardCommentByComment_id(commentId);


        verify(commentRepository, times(1)).findBoardCommentById(commentId);
        assertEquals(expectedComment, result);
    }

    @Test
    void testGetCommentCountByUserId() {

        BoardUser boardUser = new BoardUser();
        int expectedCount = 5;
        when(commentRepository.countAllByUser(boardUser)).thenReturn(expectedCount);


        int result = commentService.getCommentCountByUserId(boardUser);


        verify(commentRepository, times(1)).countAllByUser(boardUser);
        assertEquals(expectedCount, result);
    }

    @Test
    void testGetCommentCount() {

        long expectedCount = 10;
        when(commentRepository.count()).thenReturn(expectedCount);


        long result = commentService.getCommentCount();


        verify(commentRepository, times(1)).count();
        assertEquals(expectedCount, result);
    }
}
