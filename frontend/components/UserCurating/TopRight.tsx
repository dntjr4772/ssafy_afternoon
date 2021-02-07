import React from "react";
import styled from "@emotion/styled";
import Button from "../Button";
import color from "../../styles/theme";
import Image from "next/image";
import ProfileTagBox from "../ProfileTagBox";

const TitleBox = styled.div`
  display: flex;
  width: 100%;
  margin-top: 10px;
  align-items: center;
`;

const UserTitle = styled.div`
  font-size: 45px;
  font-weight: bold;
`;

const EditBox =styled.div`  
  display: flex;
  width: 40px;
  height: 40px;
  margin-left: 14px;
  margin-top: 16px;
`;

const FollowBox = styled.div`
  width: 50%;
  display: flex;
  margin-top: 15px;
  margin-bottom: 35px;
  justify-content: space-between;
`;

const UserFollowing = styled.div`
  font-size: 15px;
  font-weight: 100px;
  color: ${color.black.default};
  cursor: pointer;
  padding: 4px;
`;

const UserFollower = styled.div`
  font-size: 15px;
  font-weight: 100px;
  color: ${color.black.default};
  cursor: pointer;
  padding: 4px;
`;

const UserPost = styled.div`
  font-size: 15px;
  font-weight: 100px;
  color: ${color.black.default};
  padding: 4px;
`;

const TextBox = styled.div`
  width: 100%;
  margin-bottom: 50px;
`;

const UserText = styled.div`
  width: 100%;
  font-size: 15px;
  font-weight: 100px;
`;

const TagDiv = styled.div`
  width: 100%;
  margin-bottom: 30px;
  display: flex;
  flex-wrap: wrap;
`;

const TagTitle = styled.div`
  width: 100%;
  font-size: 15px;
  font-weight: "600";
  margin-bottom: 10px;
`;

const TagList = styled.div`
  display: flex;
  width: 100%;
`;

const TopRight = ({
  userName,
  userBox,
  userFollowing,
  userFollower,
  userPosts,
  userTags,
}) => {
  const editProfile = () => {};

  return (
    <>
      <TitleBox>
        <UserTitle>{userName}</UserTitle>
      
      <EditBox>
          <Button
            btnBgColor="transparent"
            btnWidth="120px"
            btnHeight="32px"
            btnText="팔로우하기"
            btnFontSize="15px"
            btnTextColor={color.black.default}
            btnBorderColor={color.black.default}
            // btnHoverBorderColor={"transparent"}
            btnHoverBgColor="transparent"
            btnUseIcon={true}
            btnIconSrc={"/assets/icons/follow.png"}
            btnIconHeight={"15px"}
            btnIconWidth={"15px"}
            btnIconMargin={"2px 0px 0px 12px"}
            btnUseOpacity={true}
            btnSetOpacity={"0.4"}
            btnOnClick={editProfile}
          />
        </EditBox>
        </TitleBox>
      <FollowBox>
        <UserFollowing>팔로잉 {userFollowing}</UserFollowing>
        <UserFollower>팔로워 {userFollower}</UserFollower>
        <UserPost>게시물 {userPosts}</UserPost>
      </FollowBox>
      <TextBox>
        <UserText>{userBox}</UserText>
      </TextBox>
      <TagDiv>
        {userTags &&
          userTags.map((t, index) => {
            return <ProfileTagBox key={index} tagId={t} tagMargin="0px 8px 8px 0px" tagUseDelete={false} />;
          })}
      </TagDiv>
    </>
  );
};

export default TopRight;
