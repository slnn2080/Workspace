package com.sam.reggie.utils;

import java.util.Random;

/*
  阿里云的API如下:
    但是我们没有申请短信服务 所以我们自己在这里模拟生成随机的6位数字 当做验证码

  API模版如下
  import com.aliyuncs.DefaultAcsClient;
  import com.aliyuncs.IAcsClient;
  import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
  import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
  import com.aliyuncs.exceptions.ClientException;
  import com.aliyuncs.profile.DefaultProfile;

  短信发送工具类
  public class SMSUtils {

 * 发送短信
 * @param signName 签名: 官网控台中审核通过的
 * @param templateCode 模板: 官网控台中审核通过的
 * @param phoneNumbers 手机号
 * @param param 参数: 官网模版中的变量的值

  public static void sendMessage(String signName, String templateCode,String phoneNumbers,String param){

    // 设置自己的 AccessKey
    DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "自己的AccessKey ID", "自己的AccessKey Secret");

    IAcsClient client = new DefaultAcsClient(profile);

    SendSmsRequest request = new SendSmsRequest();
    request.setSysRegionId("cn-hangzhou");
    request.setPhoneNumbers(phoneNumbers);
    request.setSignName(signName);
    request.setTemplateCode(templateCode);
    request.setTemplateParam("{\"code\":\""+param+"\"}");

    try {
      SendSmsResponse response = client.getAcsResponse(request);
      System.out.println("短信发送成功");
    }catch (ClientException e) {
      e.printStackTrace();
    }
  }
}
*/
public class SMSUtils {
  public static String generateCode() {
    String code = "";
    for(int i = 0; i < 6; i++) {
      code += new Random().nextInt(10);
    }

    return code;
  }
}
