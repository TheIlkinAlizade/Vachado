package com.vachado.posts.service;

import com.vachado.posts.dto.PostDto;

import java.util.List;

public interface IPostService {

    /**
     *
     * @param postDto - PostDto object
     */
    void createPost(PostDto postDto);

    /**
     *
     * @param postNumber - Input Post Number
     * @return Account details based on a given mobileNumber
     */
    PostDto fetchPost(Long postNumber);

    /**
     *
     * @param postDto - PostDto Object
     * @return - boolean indicating if the update of Account Details is successful or not
     */
    boolean updatePost(PostDto postDto);

    /**
     *
     * @param postNumber - Input Post Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    boolean deletePost(Long postNumber);


    /**
     *
     * @param accountNumber - Input Account Number
     * @return List<PostDto> indicating all posts of given account
     */
    List<PostDto> fetchAllPostsOfAccount(Long accountNumber);

    /**
     *
     * @param postNumber - Input Post Number
     * @return boolean indicating if the liking post is successful or not
     */
    boolean likePost(Long postNumber, Long accountNumber);

    /**
     *
     * @return latest posts
     */
    List<PostDto> fetchLatestPosts();
}

