package com.vachado.posts.controller;


import com.vachado.posts.constants.PostsConstants;
import com.vachado.posts.dto.ErrorResponseDto;
import com.vachado.posts.dto.PostDto;
import com.vachado.posts.dto.ResponseDto;
import com.vachado.posts.service.IPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Posts in Donategram",
        description = "CRUD REST APIs in Donategram to CREATE, UPDATE, FETCH AND DELETE post details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class PostsController {

    private IPostService iPostService;

    @Operation(
            summary = "Create Post REST API",
            description = "REST API to create new Post inside Donategram"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createPost (@Valid @RequestBody PostDto postDto) {
        iPostService.createPost(postDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(PostsConstants.STATUS_201, PostsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Post REST API",
            description = "REST API to fetch Post details based on a post number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetch")
    public ResponseEntity<PostDto> fetchPostDetails(    @RequestParam
                                                            @Positive(message = "PostNumber must be a positive number")
                                                            @Max(value = 9999999999L, message = "PostNumber must be at most 10 digits")
                                                            Long postNumber) {
        PostDto postDto = iPostService.fetchPost(postNumber);
        return ResponseEntity.status(HttpStatus.OK).body(postDto);
    }

    @Operation(
            summary = "Update Post Details REST API",
            description = "REST API to update Post details based on a post number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updatePostDetails(@Valid @RequestBody PostDto postDto) {
        boolean isUpdated = iPostService.updatePost(postDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(PostsConstants.STATUS_200, PostsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(PostsConstants.STATUS_417, PostsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Post Details REST API",
            description = "REST API to delete Post based on a post number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deletePostDetails(@RequestParam
                                                             @Positive(message = "PostNumber must be a positive number")
                                                             @Max(value = 9999999999L, message = "PostNumber must be at most 10 digits")
                                                             Long postNumber) {
        boolean isDeleted = iPostService.deletePost(postNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(PostsConstants.STATUS_200, PostsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(PostsConstants.STATUS_417, PostsConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Fetch All Posts",
            description = "Fetching all Posts of Account based on a Account Number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetchAll")
    public ResponseEntity<List<PostDto>> fetchAllPostsOfAccount(@RequestParam
                                                        @Positive(message = "Account Number must be a positive number")
                                                        @Max(value = 9999999999L, message = "Account Number must be at most 10 digits")
                                                        Long accountNumber) {

        List<PostDto> posts = iPostService.fetchAllPostsOfAccount(accountNumber);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @Operation(
            summary = "Like Post Details",
            description = "Like Post details based on a postNumber and accountNumber "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/like")
    public ResponseEntity<ResponseDto> like(@RequestParam
                                                @Positive(message = "Account Number must be a positive number")
                                                @Max(value = 9999999999L, message = "Account Number must be at most 10 digits")
                                                Long postNumber,
                                            @RequestParam
                                            @Positive(message = "Account Number must be a positive number")
                                            @Max(value = 9999999999L, message = "Account Number must be at most 10 digits")
                                                Long accountNumber) {

        boolean isLiked = iPostService.likePost(postNumber, accountNumber);

        if(isLiked) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(PostsConstants.STATUS_200, PostsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(PostsConstants.STATUS_417, PostsConstants.MESSAGE_417_UPDATE));
        }

    }

    @Operation(
            summary = "Fetch Latest Posts",
            description = "Fetching latest posts based on date"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/latest")
    public ResponseEntity<List<PostDto>> fetchAllPostsOfAccount() {

        List<PostDto> posts = iPostService.fetchLatestPosts();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

}
