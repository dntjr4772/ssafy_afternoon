package com.a302.webcuration.controller;

import com.a302.webcuration.common.BaseControllerTest;
import com.a302.webcuration.domain.Comment.CommentDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CommentControllerTest extends BaseControllerTest {

    @Test
    public void createComment_실패_값안넣음() throws Exception {

        CommentDto.CreateCommentRequest request = CommentDto.CreateCommentRequest.builder().build();

        long postId = 12L;
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2MTI2MzE3ODMsImFjY291bnRJZCI6MiwiYWNjb3VudEVtYWlsIjoiamFzb245NjdAbmF2ZXIuY29tIn0.ek9mnRJnWzg12jUN3494DLVqpsS9rxM-OkBQjeKL5JI";
        mockMvc.perform(post("/api/comments/"+postId)
                .header("Authorization","Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void createComment_성공() throws Exception {

        String content = "댓글 생성 테스트";

        CommentDto.CreateCommentRequest request = CommentDto.CreateCommentRequest.builder()
                .commentContent(content)
                .build();

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2MTMzOTU1NzEsImFjY291bnRJZCI6MjgsImFjY291bnRFbWFpbCI6ImRudGpyNDc3MkBuYXRlLmNvbSJ9.tau4ZBv9cfMfDDQ0euyasCfW1mKyprVXJV6iYzxofug";
        mockMvc.perform(post("/api/comments")
                .header("Authorization","Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void createComment_실패_링크만존재() throws Exception {

        String content = "댓글 생성 테스트(링크가 존재합니다.)";
        String link = "link_test.com";

        CommentDto.CreateCommentRequest request = CommentDto.CreateCommentRequest.builder()
                .commentContent(content)
                .commentLink(link)
                .build();

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2MTI2MzE3ODMsImFjY291bnRJZCI6MiwiYWNjb3VudEVtYWlsIjoiamFzb245NjdAbmF2ZXIuY29tIn0.ek9mnRJnWzg12jUN3494DLVqpsS9rxM-OkBQjeKL5JI";
        mockMvc.perform(post("/api/comments")
                .header("Authorization","Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void createComment_실패_존재하지않는게시물() throws Exception {

        String content = "댓글 생성 테스트(링크가 존재합니다.)";
        String link = "link_test.com";

        CommentDto.CreateCommentRequest request = CommentDto.CreateCommentRequest.builder()
                .commentContent(content)
                .pinId(13L)
                .commentLink(link)
                .build();

        long postId = 13L;
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2MTI2MzE3ODMsImFjY291bnRJZCI6MiwiYWNjb3VudEVtYWlsIjoiamFzb245NjdAbmF2ZXIuY29tIn0.ek9mnRJnWzg12jUN3494DLVqpsS9rxM-OkBQjeKL5JI";
        mockMvc.perform(post("/api/comments/"+postId)
                .header("Authorization","Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
    @Test
    public void createComment_성공_링크존재() throws Exception {

        String content = "댓글 생성 테스트(링크가 존재합니다. 테스트 2)";
        String link = "link_test.com";

        CommentDto.CreateCommentRequest request = CommentDto.CreateCommentRequest.builder()
                .commentContent(content)
                .commentLink(link)
                .pinId(13L)
                .build();

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2MTMzOTU1NzEsImFjY291bnRJZCI6MjgsImFjY291bnRFbWFpbCI6ImRudGpyNDc3MkBuYXRlLmNvbSJ9.tau4ZBv9cfMfDDQ0euyasCfW1mKyprVXJV6iYzxofug";
        mockMvc.perform(post("/api/comments")
                .header("Authorization","Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void createComment_성공_모두존재() throws Exception {

        String content = "댓글을 답니다";
        String link = "link_test.com";

        CommentDto.CreateCommentRequest request = CommentDto.CreateCommentRequest.builder()
                .commentContent(content)
                .commentLink(link)
                .pinId(26L)
                .postsId(25L)
                .pinNum(1)
                .build();

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2MTM2Njc3MzksImFjY291bnRJZCI6MSwiYWNjb3VudEVtYWlsIjoiZG50anI0NzcyQG5hdGUuY29tIn0.sVdShJvwy1DXLeT08_yQKwUQ6XCo2Z9FPhI_ufuC59M";
        mockMvc.perform(post("/api/comments")
                .header("Authorization","Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void retrieveComment_성공() throws Exception {
        Long postId = 12L;
        mockMvc.perform(get("/api/comments/"+postId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}