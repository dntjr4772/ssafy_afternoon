package com.a302.webcuration.domain.Account;

import com.a302.webcuration.domain.Tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private LocalDate accountUpdateDate;

    //인증
    @Builder.Default
    private Long accountAuthNum=0L;

    //임시 고객
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Role accountRole = Role.TEMPORARY;

    @Builder.Default
    private String accountDesc="";

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Account> following=new ArrayList<>();

    @Builder.Default
    @ManyToMany(mappedBy = "following")
    private List<Account> follower=new ArrayList<>();

    //private List<Tag> tags;
//    @Builder
//    public Account(String accountNickname,String accountEmail,String accountName,Long accountAuthNum){
//        this.accountNickname=accountNickname;
//        this.accountEmail=accountEmail;
//        this.accountName=accountName;
//    }

    public void followAccount(Account account){
        this.getFollowing().add(account);
        account.getFollower().add(this);
    }
}
