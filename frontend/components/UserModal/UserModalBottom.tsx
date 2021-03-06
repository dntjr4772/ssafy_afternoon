import React from "react";
import Button from "../Button";
import styled from "@emotion/styled";
import color from "../../styles/theme";
import Swal from "sweetalert2";

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 300px;
  padding: 10px 0px;
  border-top: 2px solid ${color.gray.default};
  border-bottom: 2px solid ${color.gray.default};
`;

const ModalText = styled.div`
  display: flex;
  width: 300px;
  margin-top: 4px;
  justify-content: center;
  align-items: center;
  font-size: 12px;
  font-weight: bold;
  color: ${color.black.default};
`;

const SnsButton1 = styled.div`
  display: flex;
  width: 300px;
  margin-top: 15px;
  justify-content: center;
  align-items: center;
`;

const SnsButton2 = styled.div`
  display: flex;
  width: 300px;
  justify-content: center;
  align-items: center;
  margin: 7px 0px;
`;

const SnsButton3 = styled.div`
  display: flex;
  width: 300px;
  justify-content: center;
  align-items: center;
`;

type BottomProps = {
  bottomText?: string;
  snsText1?: string;
  snsText2?: string;
  snsText3?: string;
};

const UserModalBottom = ({
  bottomText,
  snsText1,
  snsText2,
  snsText3,
}: BottomProps) => {
  return (
    <Container>
      <ModalText>{bottomText}</ModalText>
      <SnsButton1>
        <Button
          btnBgColor={color.blue.default}
          btnWidth="300px"
          btnText={snsText1}
          btnTextColor={color.white.default}
          btnHeight="35px"
          btnFontWeight={700}
          btnBorderColor="transparent"
          btnHoverBorderColor="transparent"
          btnHoverBgColor={color.blue.dark}
          btnHoverTextColor={color.white.default}
          btnUseIcon={true}
          btnIconSrc={"/assets/icons/facebook_icon.png"}
          btnIconHeight={"18px"}
          btnIconWidth={"18px"}
          btnIconMargin={"0px 0px 0px 20px"}
          btnOnClick={() => {
            Swal.fire({
              icon: "info",
              title: "소셜 연동 서비스 준비 중..",
              text: "일반 로그인 및 회원가입을 이용해주세요",
            });
          }}
        />
      </SnsButton1>
      <SnsButton2>
        <Button
          btnBgColor={color.yellow.default}
          btnWidth="300px"
          btnText={snsText2}
          btnTextColor={color.black.default}
          btnHeight="35px"
          btnFontWeight={700}
          btnBorderColor="transparent"
          btnHoverBorderColor="transparent"
          btnHoverBgColor={color.yellow.dark}
          btnHoverTextColor={color.black.default}
          btnUseIcon={true}
          btnIconSrc={"/assets/icons/kakao_icon.png"}
          btnIconHeight={"18px"}
          btnIconWidth={"18px"}
          btnIconMargin={"0px 0px 0px 20px"}
          btnOnClick={() => {
            Swal.fire({
              icon: "info",
              title: "소셜 연동 서비스 준비 중..",
              text: "일반 로그인 및 회원가입을 이용해주세요",
            });
          }}
        />
      </SnsButton2>
      <SnsButton3>
        <Button
          btnBgColor={color.gray.default}
          btnWidth="300px"
          btnText={snsText3}
          btnTextColor={color.black.default}
          btnHeight="35px"
          btnFontWeight={700}
          btnBorderColor="transparent"
          btnHoverBorderColor="transparent"
          btnHoverBgColor={color.gray.semidark}
          btnHoverTextColor={color.black.default}
          btnUseIcon={true}
          btnIconSrc={"/assets/icons/google_icon.png"}
          btnIconHeight={"16px"}
          btnIconWidth={"16px"}
          btnIconMargin={"0px 1px 0px 21px"}
          btnOnClick={() => {
            Swal.fire({
              icon: "info",
              title: "소셜 연동 서비스 준비 중..",
              text: "일반 로그인 및 회원가입을 이용해주세요",
            });
          }}
        />
      </SnsButton3>
    </Container>
  );
};

export default UserModalBottom;
