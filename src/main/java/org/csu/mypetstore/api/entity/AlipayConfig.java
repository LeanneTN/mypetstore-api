package org.csu.mypetstore.api.entity;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2021000119638764";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCgLU5mfzgZReOSBoHtRSNgGmCnb/QSJBBdRW1J/TNoOSG29UtItx4yQHfZ7/XzmV8Ha0W/o/WpkH0ydBzHIdK1yMUNsSKcKdUp8ElMVABb8XbGmahKvPnPVM3YdKAnW3plyXuJKsrv1NimlSnoXvkfLFdi05AeQgkA8o5M3B6qJGXgBeMiBCgbRKGO4wbF6W6CzLo07dnFM4f5XYz1983adhXSSBmFjmyLL9aoc9dk/Ppy2a5AF8+ygvgVgqGLZh+VB9eS4LLZmjrhRuABmPATqUUGEYTmkjQybaCo0H+TprkGhuf8BGLKaDQO6f4Jp2aBfxXo8hId29tZIHErx8dtAgMBAAECggEAaf6RKvJ0xQSimzcDM2fVCPtrimFjOb3ztkUDFTsgkY985/1aXDwRF1/h764DK7gKUnaCKVbucc7Tileb04pmTTUWcQEOzhhAnwgK+R99PVqEJtf3rb1v8etEbECA2oqGbGTwAsz2jc5jr5h1qvduE1rcWOF3NQ6ttprL38Cp6hwv7AbyWk+E6g9hdHHCAgb2+6Sp2LEqe3Lp6eCHhb+LJF3Suk7I+wiELPGpl/otpDs3RSefM7h2rBNgpK1O9xZk0SK2KZekdDunejSC2hNHlmGTraKVI8orW6XoOrLC8CkIHNJY0msNiUnxyf1MlzWqDwvovUk4b/gUKpmCMVB/YQKBgQDLwhPKA8BNnSbvR0bjJ7auRG91awgwuq+lsSFv9isUkZQdZCT06+bA9t18OFBHabVBcBcjHBLgHmedUF4Ih/y5g6FcsPNu/jf7HdzrYM/1iWavT1ASnde14deuJHmKvFY1CXUlFiQwurA80CkEk0TkWblYqNRy7GTCgYPR+OyD4wKBgQDJPrnFDXE9dt2LuPmmvGpr+yS1nzBllAvPOQOsMRtYmDaBnU4MyeinoKjsTXRUQL3C7PL4KR8VbNoQQCxGD/0jiFABceC3D2BirKDk6TCsF3PHolFqUBl3JlAgsr4zlY48RLF8aXcrkO0G8opAITof5vI5rZGd6UHN4d+CeE6IbwKBgGyq7PLoXT8HeSRT1zJ+k6cNSIROuOeEKPktJrAXkZI+scebCTq2NTLkdesx/V6YudpUdYJ8DnUIwincGsph/B7UXNKabLndSu3aV2ARGdfRl7oXipTjKZAPCoANY+Th3PgcOyW0UUjLyAIm4jlWcX93eqoWIHfKK1Kw0CTDpSARAoGAGDmNwTOyCvURDC2zF6gco2zy6U4FVGTy2voBACxVH2ALR6T47T8s22jSjMdyFmZIsXs2cDr6++hDg6tF6RUpoqpY/yOHKmIWdypSn9uWOVEFZeWbKVIFDru074q0rJ73JTgbqV9EsiDa93ggvkGBrq9tPxYurjSS8EadUiZLRc8CgYEAtWfoOOPs9jd1UGhvNeXeZsiJmCYLknh15RAqDWb2tkfj2KaPrY0JmRsfwm1zuzoOGoO66V9Ci2mnHw3IsjrJHz+LJiqr1ypvARmpyuvDFqjyDahqJRJUGeIBjiYIbJu+JXJG/u4nHklMnrwyiGyXMl5HefrPKMnk96MjFweB51w=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoIxqe32vnHKhSVR/U/b/Xbds+KsT2xW2hvkLqZEGoEiSX7NkDyXf0ZXOble4Jsq36SBmfNfhhizfxD/oQum7GY2AIsHlRF7iTmwjKM/6ur8FUJwT2GJ6HFdmLb5GSxrpa25zD9+E5YDck22OLuzI5t/nUy6hV2YVEhu+fduUJFQOcrf8Bz2oNz8oRoWQlwF99FJaHIIX79SlJwM0QSyD94mw8Oto6C3knBlFWM1E3m1oaQomd4kimaYtLe4ueZRaUqCaEWqmGienpQ0fu7xO0YMF18a8f0+PvC2V2o5S7OGQ/nAkMijTuqlAlFUTGR705SpdtQCU9vyS84i/0SEizwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost/pay/fail.html";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://localhost:8888/view/catalog/catalog-main.html";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "https://openapi.alipaydev.com/gateway.do";


    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
