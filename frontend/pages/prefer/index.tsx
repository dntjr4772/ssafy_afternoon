import React from "react";
import styled from "@emotion/styled";
import Button from "../../components/Button";
import color from "../../styles/theme";
import Image from "next/image";
import PreferTags from "../../components/PreferTags";

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding-top: 62px;
  /* padding-left: 80px;
  padding-right: 80px; */
  width: 100%;
  height: 1200px;
`;

const Wrapper = styled.div`
  display: flex;
  width: 1280px;
  height: 1200px;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
`;

const TopBox = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  align-items: center;
  padding: 50px;
  margin-bottom: 50px;
`;

const InnerBox1 = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: 13px;
  width: 100%;
  font-size: 40px;
  font-weight: bold;
  justify-content: center;
  align-items: center;
`;

const InnerBox2 = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  font-size: 20px;
  justify-content: center;
  align-items: center;
`;

const MiddleBox = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 700px;
  justify-content: space-between;
  margin-bottom: 30px;
`;

const BottomBox = styled.div`
  display: flex;
  width: 100%;
  height: 50px;
  justify-content: flex-end;
  align-items: center;
  padding: 1px;
`;

const index = () => {
  return (
    <Container>
      <Wrapper>
        <TopBox>
          <InnerBox1>이런..! 난재님의 피드에 표시할 내용이 없어요.</InnerBox1>
          <InnerBox2>난재님의 피드에 표시할 관심태그를 선택해주세요.</InnerBox2>
          <InnerBox2>
            또는 검색바에서 사용자를 검색 후 팔로잉 해주세요.
          </InnerBox2>
        </TopBox>
        <MiddleBox>
          <PreferTags></PreferTags>
        </MiddleBox>
        <BottomBox>
          <Button
            btnBgColor={color.gray.default}
            btnWidth="150px"
            btnText="관심태그 0개 선택"
            btnTextColor={color.white.default}
            btnHeight="40px"
            btnFontSize="18px"
            btnFontWeight={700}
            btnBorderColor="transparent"
            btnHoverBorderColor="transparent"
            btnHoverBgColor={color.gray.semidark}
            btnHoverTextColor={color.white.default}
          />
        </BottomBox>
      </Wrapper>
    </Container>
  );
};

export default index;