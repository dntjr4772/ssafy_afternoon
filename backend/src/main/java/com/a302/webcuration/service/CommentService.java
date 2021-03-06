package com.a302.webcuration.service;

import com.a302.webcuration.common.BaseMessage;
import com.a302.webcuration.common.BaseStatus;
import com.a302.webcuration.controller.CommentController;
import com.a302.webcuration.domain.Account.Account;
import com.a302.webcuration.domain.Account.AccountRepository;
import com.a302.webcuration.domain.Comment.Comment;
import com.a302.webcuration.domain.Comment.CommentDto;
import com.a302.webcuration.domain.Comment.CommentRepository;
import com.a302.webcuration.domain.Pin.Pin;
import com.a302.webcuration.domain.Pin.PinDto;
import com.a302.webcuration.domain.Pin.PinRepository;
import com.a302.webcuration.domain.Post.Posts;
import com.a302.webcuration.domain.Post.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostsRepository postsRepository;
    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;
    private final PinRepository pinRepository;
    private final ModelMapper modelMapper;
    private final PostsService postsService;


    public static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    //TODO 일단 이런식으로 validation체크 해놓고 나중에 리팩토링
    public Boolean createCommentValidation(CommentDto.CreateCommentRequest request)
    {
        if (!request.getCommentLink().isEmpty())
        {
            if(request.getPinId()==null)
            {
                return false;
            }
        }
        return true;
    }

    @Transactional
    public BaseMessage createComment(long accountId, CommentDto.CreateCommentRequest request)
    {
        //사용가능 여부 판별
        Boolean isValid = createCommentValidation(request);
        Map<String, Object> resultMap = new HashMap<>();
        if(!isValid)
        {
            resultMap.put("error","유효하지 않은 요청입니다.");
            return new BaseMessage(HttpStatus.BAD_REQUEST,resultMap);
        }
        Comment comment = request.toEntity();
        Account myAccount = accountRepository.findAccountByAccountId(accountId);
        comment.addCommentWriter(myAccount.getAccountNickname(),myAccount.getAccountPhoto());

        try {
            //핀이 존재하는 경우
            Pin pin = pinRepository.findPinByPinId(request.getPinId());
            if(pin!=null)
            {
                comment.saveWithCascadePin(pin);
                resultMap.put("message","핀을 가지고 있습니다.");
            }
            else resultMap.put("message","핀을가지고 있지 않습니다.");

            Posts post = postsRepository.findPostsByPostsId(request.getPostsId());
            comment.saveWithCascadePosts(post);
            commentRepository.save(comment);
            resultMap.put("message","게시물에 댓글이 등록되었습니다.");
            return new BaseMessage(HttpStatus.CREATED,resultMap);
        }catch (Exception e)
        {
            resultMap.put("error","게시물의 아이디가 존재하지않습니다.");
            return new BaseMessage(HttpStatus.BAD_REQUEST,resultMap);
        }
    }
    public BaseMessage retrieveComment(Long postid) {
        logger.info(postid+"번 게시물 댓글 조회");
        try {
            List<CommentDto.CreateCommentResponse> commentResponses = new ArrayList<>();
            Posts post = postsRepository.findPostsByPostsId(postid);
            List<Comment> commentList = post.getComments();
            String accountNickname = post.getPostWriter().getAccountNickname();
            for (Comment comment : commentList) {
                CommentDto.CreateCommentResponse commentResponse = modelMapper.map(comment,CommentDto.CreateCommentResponse.class);
                commentResponse.setAccountNickname(accountNickname);
                commentResponses.add(commentResponse);
            }
            return new BaseMessage(HttpStatus.OK,commentResponses);
        }catch (Exception e)
        {
            //Todo 나중에 다시 처리해야됌
            return new BaseMessage(HttpStatus.BAD_REQUEST,null);
        }
    }
}
