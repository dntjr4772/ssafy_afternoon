import axios from "axios";
import secrets from "../../secrets";

const API_ROOT_URI = secrets.API_ROOT_URI;
const VIA_API_DEV = secrets.VIA_API_DEV;

export const GET_MY_INFO = async (req) => {
  console.log(req);
  await axios.get(API_ROOT_URI + "/accounts/" + req.accountId).then((res) => {
    console.log(res.data);
  });
};

export const ADD_TAGS = async (req) => {
  const authToken = window.localStorage.getItem("authToken");
  if (!VIA_API_DEV) {
    try {
      console.log(req);
      return { status: 200, data: {} };
    } catch (error) {
      console.log(error);
    }
    return { status: false };
  } else {
    // API 요청 시 실행
    let status;
    let data;

    try {
      await axios
        .put(API_ROOT_URI + "/api/accounts/mytag", req, {
          headers: { Authorization: `Bearer ${authToken}` },
        })
        .then((res) => {
          console.log(res);
          status = res.status;
          data = res.data;
        });
      return { status, data };
    } catch (error) {
      console.log(error);
    }
    return { status: false };
  }
};
