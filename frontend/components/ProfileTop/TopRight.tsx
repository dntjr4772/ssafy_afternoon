import React from "react";
import styled from "@emotion/styled";
import Button from "../Button";
import color from "../../styles/theme";
import Image from "next/image";

const TitleBox = styled.div`
  display: flex;
  width: 700px;
  height: 60px;
  align-items: center;
`;

const ProfileTitle = styled.div`
  font-size: 45px;
  font-weight: bold;
`;

const EditBox = styled.div`
  width: 30px;
  height: 30px;
  position: absolute;
`;

const TextBox = styled.div`
  width: 700px;
  height: 50px;
  margin-bottom: 7px;
`;

const ProfileText = styled.div`
  width: 100%;
  font-size: 17px;
  font-weight: 100px;
`;

const FollowBox = styled.div`
  width: 700px;
  height: 90px;
`;

const ProfileFollower = styled.div`
  width: 100%;
  font-size: 17px;
  font-weight: 100px;
  color: ${color.gray.dark};
  margin-bottom: 4px;
`;

const ProfileFollowing = styled.div`
  width: 100%;
  font-size: 17px;
  font-weight: 100px;
  color: ${color.gray.dark};
  margin-bottom: 4px;
`;

const ProfilePost = styled.div`
  width: 100%;
  font-size: 17px;
  font-weight: 100px;
  color: ${color.gray.dark};
  margin-bottom: 4px;
`;

const TagTitle = styled.div`
  width: 700px;
  height: 40px;
  font-size: 17px;
  font-weight: "600";
  margin: 2px 0px;
`;

const TagList = styled.div`
  display: flex;
  width: 700px;
  height: 40px;
  margin: 3px 0px;
`;

const TopRight = ({
  profileName,
  profileText,
  profileFollowing,
  profileFollower,
  profileMyposts,
  profileTags,
}) => {
  return (
    <>
      <TitleBox>
        <ProfileTitle>{ profileName }</ProfileTitle>
        <EditBox>
          {/* <Image
            src="/assets/icons/edit_white.png"
            layout="fill"
            objectFit="cover"
          ></Image> */}
        </EditBox>
      </TitleBox>
      <TextBox>
        <ProfileText>
          { profileText }
        </ProfileText>
      </TextBox>
      <FollowBox>
        <ProfileFollowing>팔로잉 : { profileFollowing }명</ProfileFollowing>
        <ProfileFollower>팔로워 : { profileFollower }명</ProfileFollower>
        <ProfilePost>나의 게시글 : { profileMyposts }개</ProfilePost>
      </FollowBox>
      <TagTitle>
        <TagTitle>나의 관심태그</TagTitle>
        <TagList>
          { profileTags }
          <Button
            btnText="클라이밍"
            btnWidth="100px"
            btnMarginRight="5px"
            btnBgColor="${color.gray.light}"
            btnTextColor="${color.gray.semidark}"
            btnFontSize="20px"
            btnFontWeight={500}
            btnHoverTextColor="${color.gray.dark}"
          />
          <Button
            btnText="엔지니어"
            btnWidth="100px"
            btnMarginRight="5px"
            btnBgColor="${color.gray.light}"
            btnTextColor="${color.gray.semidark}"
            btnFontSize="20px"
            btnFontWeight={500}
            btnHoverTextColor="${color.gray.dark}"
          />
        </TagList>
      </TagTitle>
    </>
  );
};

export default TopRight;
