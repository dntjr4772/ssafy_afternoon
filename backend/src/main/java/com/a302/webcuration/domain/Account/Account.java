package com.a302.webcuration.domain.Account;

import com.a302.webcuration.domain.Post.Posts;
import com.a302.webcuration.domain.Tag.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    //기본 정보
    private String accountName;

    //unique key
    @Column(unique = true)
    private String accountNickname;
    @Column(unique = true)
    private String accountEmail;

    //시간 관련
    @CreationTimestamp
    private LocalDate accountCreateDate;
    @UpdateTimestamp
    private LocalDateTime accountUpdateDate;
    //유저 소개글
    @Builder.Default
    private String accountBio ="";
    //프로필 사진
    @Builder.Default
    private String accountPhoto ="";

    //인증
    @Builder.Default
    private String accountAuthKey ="";

    //임시 고객
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Role accountRole = Role.TEMPORARY;
    //팔로잉
    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Account> following=new HashSet<>();

    @Builder.Default
    @ManyToMany(mappedBy = "following")
    private Set<Account> follower=new HashSet<>();
    //관심 태그
    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "ACCOUNT_TAGS",
            joinColumns = @JoinColumn(name = "ACCOUNT_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID")
    )
    private Set<Tag> tags=new HashSet<>();
    //좋아요한 게시글
    @Builder.Default
    @ManyToMany
    private List<Posts> likePosts=new ArrayList<>();
    //내가 쓴 게시글
    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "postWriter")
    private List<Posts> myPosts=new ArrayList<>();

    public void updateAccount(AccountDto.UpdateRequest request)
    {
        this.accountNickname=request.getAccountNickname();
        this.accountBio =request.getAccountBio();
        this.accountPhoto=request.getAccountPhoto();
    }

    public void changeAuthKey(String accountAuthKey){
        this.accountAuthKey =accountAuthKey;
    }

    public void changeRole(Role accountRole){
        this.accountRole=accountRole;
    }

    public void followAccount(Account account){
        this.getFollowing().add(account);
        account.getFollower().add(this);
    }
    // TODO: 2021-02-06 관심태그  
    public void tagging(Tag tag){
            this.getTags().add(tag);
            tag.getAccounts().add(this);
    }

    //관심태그 삭제
    public void deleteTag(){
        for (Tag tag : tags){
            tag.getAccounts().remove(this);
        }
        tags.clear();
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                ", accountNickname='" + accountNickname + '\'' +
                ", accountEmail='" + accountEmail + '\'' +
                ", accountCreateDate=" + accountCreateDate +
                ", accountBio='" + accountBio + '\'' +
                ", myPosts=" + myPosts +
                '}';
    }
}
