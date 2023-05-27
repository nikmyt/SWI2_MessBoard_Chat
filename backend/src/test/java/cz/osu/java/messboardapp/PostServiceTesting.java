package cz.osu.java.messboardapp;

import cz.osu.java.messboardapp.Form.PostForm;
import cz.osu.java.messboardapp.model.BoardPost;
import cz.osu.java.messboardapp.model.BoardUser;
import cz.osu.java.messboardapp.repository.AppUserRepository;
import cz.osu.java.messboardapp.repository.PostRepository;
import cz.osu.java.messboardapp.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostServiceTesting {
    @Mock
    private PostRepository postRepository;

    private AppUserRepository userRepository;
    private PostService postService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        postService = new PostService(postRepository);
    }

    @Test
    public void testSave() {
        // Arrange
        PostForm newPost = new PostForm();
        newPost.setText("Test post");
        newPost.setTitle("Test title");
        newPost.setTag("Test tag");
        BoardUser user = new BoardUser();

        // Act
        postService.save(newPost, user);

        // Assert
        verify(postRepository, times(1)).save(any(BoardPost.class));
    }

    @Test
    public void testUpdate_ExistingPost_ReturnsTrue() {
        // Arrange
        int postId = 1;
        BoardPost existingPost = new BoardPost();
        existingPost.setPostId(postId);
        existingPost.setText("Existing post");
        existingPost.setTitle("Existing title");
        existingPost.setTag("Existing tag");
        existingPost.setTemp(false);
        BoardPost updatedPost = new BoardPost();
        updatedPost.setText("Updated post");
        updatedPost.setTitle("Updated title");
        updatedPost.setTag("Updated tag");
        updatedPost.setTemp(true);
        when(postRepository.findById(postId)).thenReturn(Optional.of(existingPost));

        // Act
        boolean result = postService.update(postId, updatedPost);

        // Assert
        assertTrue(result);
        assertEquals("Updated post", existingPost.getText());
        assertEquals("Updated title", existingPost.getTitle());
        assertEquals("Updated tag", existingPost.getTag());
        assertTrue(existingPost.isTemp());
        verify(postRepository, times(1)).findById(postId);
    }

    @Test
    public void testUpdate_NonExistingPost_ReturnsFalse() {
        // Arrange
        int postId = 1;
        BoardPost updatedPost = new BoardPost();
        updatedPost.setText("Updated post");
        updatedPost.setTitle("Updated title");
        updatedPost.setTag("Updated tag");
        updatedPost.setTemp(true);
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // Act
        boolean result = postService.update(postId, updatedPost);

        // Assert
        assertFalse(result);
        verify(postRepository, times(1)).findById(postId);
        verify(postRepository, never()).save(any(BoardPost.class));
    }

    @Test
    public void testFindAll() {
        // Arrange
        ArrayList<BoardPost> expectedPosts = new ArrayList<>();
        expectedPosts.add(new BoardPost());
        expectedPosts.add(new BoardPost());
        expectedPosts.add(new BoardPost());
        when(postRepository.findAll()).thenReturn(expectedPosts);

        // Act
        Iterable<BoardPost> actualPosts = postService.findAll();

        // Assert
        assertEquals(expectedPosts, actualPosts);
        verify(postRepository, times(1)).findAll();
    }

    @Test
    public void testFindByPostId() {
        // Arrange
        Integer postId = 1;
        BoardPost expectedPost = new BoardPost();
        when(postRepository.findById(postId)).thenReturn(Optional.of(expectedPost));

        // Act
        Optional<BoardPost> actualPost = postService.findByPostId(postId);

        // Assert
        assertTrue(actualPost.isPresent());
        assertEquals(expectedPost, actualPost.get());
        verify(postRepository, times(1)).findById(postId);
    }

    @Test
    public void testDeletePost() {
        // Arrange
        BoardPost postToDelete = new BoardPost();

        // Act
        postService.deletePost(postToDelete);

        // Assert
        verify(postRepository, times(1)).delete(postToDelete);
    }
    @Test
    public void testFindBoardPostsByTagCont() {
        // Arrange
        String tag = "example";
        List<BoardPost> expectedPosts = Arrays.asList(new BoardPost(), new BoardPost());
        when(postRepository.findBoardPostByTagContainingIgnoreCase(tag)).thenReturn(expectedPosts);

        // Act
        Iterable<BoardPost> actualPosts = postService.findBoardPostsByTagCont(tag);

        // Assert
        assertEquals(expectedPosts, actualPosts);
        verify(postRepository, times(1)).findBoardPostByTagContainingIgnoreCase(tag);
    }
    @Test
    public void testFindBoardPostsByTitleCont() {
        // Arrange
        String title = "example";
        List<BoardPost> expectedPosts = Arrays.asList(new BoardPost(), new BoardPost());
        when(postRepository.findBoardPostByTitleContainingIgnoreCase(title)).thenReturn(expectedPosts);

        // Act
        Iterable<BoardPost> actualPosts = postService.findBoardPostsByTitleCont(title);

        // Assert
        assertEquals(expectedPosts, actualPosts);
        verify(postRepository, times(1)).findBoardPostByTitleContainingIgnoreCase(title);
    }


    //6 metod z PostService je jen třízení findAll()






}
