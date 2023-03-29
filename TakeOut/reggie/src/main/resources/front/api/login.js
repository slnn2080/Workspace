function loginApi(data) {
    return $axios({
      'url': '/user/login',
      'method': 'post',
      data
    })
  }

  function sendMsgApi(phoneNum) {
    return $axios({
      url: `/user/sendMsg/${phoneNum}`,
      method: "get"
    })
  }

function loginoutApi() {
  return $axios({
    'url': '/user/loginout',
    'method': 'post',
  })
}

  