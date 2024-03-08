package com.vachado.posts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
        name = "post",
        description = "Schema to hold Post information"
)
public class PostDto {

    @Schema(
            description = "Title of the post", example = "Island children"
    )
    @NotEmpty(message = "Title can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String title;

    @Schema(
            description = "Content of the post", example = "We should help people in ..."
    )
    @NotEmpty(message = "Content can not be a null or empty")
    @Size(min = 5, max = 256, message = "The length of the content should be between 16 and 256")
    private String content;

//    @NotNull(message = "Post number can not be null")
    @PositiveOrZero(message = "Post number must be positive or zero")
    @Schema(
            description = "Post number of the post", example = "1234567890"
    )
    private Long postNumber;

    /*
        Not for use
    @NotNull(message = "Account number can not be null")
    @PositiveOrZero(message = "Account number must be positive or zero")
    @Schema(
            description = "Account number of the account", example = "1234567890"
    )
    private Long like;*/

    @NotNull(message = "Account number can not be null")
    @PositiveOrZero(message = "Account number must be positive or zero")
    @Schema(
            description = "Account number of the account", example = "1234567890"
    )
    private Long accountNumber;


}