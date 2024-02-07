package org.api.dto.requestDto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CreatePostDTO {
    String title;
    String body;
    String select1;
    String uniquePost;
    String token;
}
