package com.vachado.posts.mapper;

import com.vachado.posts.dto.PostDto;
import com.vachado.posts.entity.Post;

import java.util.List;

public class PostMapper {

    public static PostDto mapToPostDto(Post post, PostDto postDto) {
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setPostNumber(post.getPostNumber());
//        postDto.setLike(post.getLike()); Not used yet
        postDto.setAccountNumber(post.getAccountNumber());
        return postDto;
    }

    public static Post mapToPost(PostDto postDto, Post post) {
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setPostNumber(postDto.getPostNumber());
//        post.setLike(postDto.getLike()); Not used yet
        post.setAccountNumber(postDto.getAccountNumber());
        return post;
    }

    public static List<PostDto> mapToPostDtoList(List<Post> posts, List<PostDto> postDtos) {
        for (Post post : posts) {
            postDtos.add(PostMapper.mapToPostDto(post, new PostDto()));
        }
        return postDtos;
    }

    public static List<Post> mapToPostList(List<PostDto> postDtos, List<Post> posts) {
        for (PostDto postDto : postDtos) {
            posts.add(PostMapper.mapToPost(postDto, new Post()));
        }
        return posts;
    }

}
