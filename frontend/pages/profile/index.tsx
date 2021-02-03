import React from "react";
import styled from "@emotion/styled";
import ProfileTop from "../../components/ProfileTop";
import Button from "../../components/Button";
import color from "../../styles/theme";
import Image from "next/image";

const Container1 = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  height: 800px;
`;

const Container2 = styled.div`
  display: flex;
  width: 100%;
  height: 800px;
`;

const index = () => {
  const profileData = {
    profileImg: "https://t1.daumcdn.net/cfile/tistory/995AB93359C8BC6D10",
    profileName: "Alex",
    profileBox:
      "보이는 가치를 사랑의 끓는다. 굳세게 산야에 용기가 품었기 이상의 속잎나고, 그리하였는가? 타오르고 못하다 가치를 귀는 없는 속에서 따뜻한 보이는 내는 쓸쓸하랴? 인간은 가슴에 새 그들에게 자신과 대한 길지 것이다. 날카로우나 얼마나 용감하고 별과 가는 따뜻한 우리 말이다. 우리의 우리 발휘하기 구하지 같은 원대하고, 많이 스며들어 아름다우냐? 피고 심장의 그들의 그들은 풍부하게 그들을 원질이 운다.",
    profileFollowing: "159",
    profileFollwer: "143,534",
    profileMyposts: "376",
    profileTags: "컴포넌트받아오기.",
	};
	
  return (
    <>
      <Container1>
        <ProfileTop profileData={profileData}></ProfileTop>
      </Container1>
      <Container2>{/* <ProfileBottom></ProfileBottom> */}</Container2>
    </>
  );
};

export default index;
