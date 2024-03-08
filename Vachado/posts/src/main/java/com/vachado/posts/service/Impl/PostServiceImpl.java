package com.vachado.posts.service.Impl;

import com.vachado.posts.dto.PostDto;
import com.vachado.posts.entity.Post;
import com.vachado.posts.entity.PostLiker;
import com.vachado.posts.exception.ResourceNotFoundException;
import com.vachado.posts.mapper.PostMapper;
import com.vachado.posts.repository.PostLikerRepository;
import com.vachado.posts.repository.PostRepository;
import com.vachado.posts.service.IPostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class PostServiceImpl implements IPostService {

    private PostRepository postRepository;
    private PostLikerRepository postLikerRepository;

    /**
     * @param postDto - PostDto object
     */
    @Override
    public void createPost(PostDto postDto) {

        Post post = PostMapper.mapToPost(postDto, new Post());

        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        post.setPostNumber(randomAccNumber);
        post.setLike(0L);

        postRepository.save(post);


    }


    /**
     * @param postNumber - Input Post Number
     * @return Account details based on a given mobileNumber
     */
    @Override
    public PostDto fetchPost(Long postNumber) {
        Post post = postRepository.findByPostNumber(postNumber).orElseThrow(
                () -> new ResourceNotFoundException("Post", "postNumber", Long.toString(postNumber))
        );

        return PostMapper.mapToPostDto(post, new PostDto());
    }

    /**
     * @param postDto - PostDto Object
     * @return - boolean indicating if the update of Account Details is successful or not
     */
    @Override
    public boolean updatePost(PostDto postDto) {
        boolean isUpdated = false;
        if(postDto !=null ){
            Post post = postRepository.findById(postDto.getPostNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Post", "PostNumber", postDto.getPostNumber().toString())
            );
            PostMapper.mapToPost(postDto, post);
            post = postRepository.save(post);
            isUpdated = true;
        }
        return  isUpdated;
    }

    /**
     * @param postNumber - Input PostNumber
     * @return boolean indicating if the delete of Account details is successful or not
     */
    @Override
    public boolean deletePost(Long postNumber) {

        Post post = postRepository.findByPostNumber(postNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post Number", Long.toString(postNumber)));
        postRepository.deleteById(postNumber);
        return true ;
    }

    /**
     * @param accountNumber - Input Account Number
     * @return List<PostDto> indicating all posts of given account
     */
    @Override
    public List<PostDto> fetchAllPostsOfAccount(Long accountNumber) {

        List<Post> posts =  postRepository.findAllByAccountNumber(accountNumber).orElseThrow(
                () -> new ResourceNotFoundException("Post", "Posts", Long.toString(accountNumber)));

        if (posts.isEmpty()) {
            throw new ResourceNotFoundException("Posts", "accountNumber", Long.toString(accountNumber));
        }

        List<PostDto> postDtos = PostMapper.mapToPostDtoList(posts, new ArrayList<PostDto>());

        return postDtos;
    }

    /**
     * @param postNumber - Input Post Number
     * @return boolean indicating if the liking post is successful or not
     */
    @Override
    public boolean likePost(Long postNumber, Long accountNumber) {
        Post post = postRepository.findByPostNumber(postNumber).orElseThrow(
                () -> new ResourceNotFoundException("Post", "postNumber", Long.toString(postNumber)));

        if (postLikerRepository.existsByPostIdAndAccountNumber( post.getPostNumber(), accountNumber)) {
            return true;
        }

        post.setLike(post.getLike() + 1);
        postLikerRepository.save(new PostLiker(post.getPostNumber(), accountNumber));
        postRepository.save(post);

        return true;
    }

    /**
     *
     * @return latest posts
     */

    @Override
    public List<PostDto> fetchLatestPosts() {

        List<Post> posts = postRepository.findTop10ByOrderByCreatedAtDesc();

        List<PostDto> postDtos = PostMapper.mapToPostDtoList(posts, new ArrayList<PostDto>());

        return postDtos;

    }
}
