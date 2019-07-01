package com.hero.hotel.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id ="2016100100637064";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCT6cq9ljwjbZx7rcySehz+Kro4FPGidpN/f4yPrhU8vwQozqrh/PsgguiMHj6s3YYHK01fdrUwa7dnR2cTRXYS1CO9WcLPyGiLIXAGoKYCJfCBUXab7nDhBFxZHZQGRXgSI1OqwkxfBV6qvDHbcJnYOLrQLlZAuC9Tr0WYNZKq1qi9Hk82m74hl8FiC1DQeJ6o/D2FcOSI+CTD+ehC0rxP9KivwY79O314rXAU9kDvY13QOmWT23OaTp2Ww3hqHkoAUvxsqEmuZhthjS8Jgf8toCSFFkthLJq8/dFRRZyOUlRrIM/8LTXkOBsBH3gwNraAXqxs01CHTpMYb1qv2goLAgMBAAECggEASO5L0vMSr/1J3QmzBr5DMWdr7l8VTZ/d1k3Txrx1KECfEmfkhg0w3bTuaLg4+w83GK3XNqzXZgqWocvpHO1w7JPBE4oArmcIdSGFlR78+64tSfYa34PQEIMP7bjR1c3HCFp21Il9EpUZOmIUyzhYAu7l3eQbD0pBu6d+TzCLIsRzgna53fGbNTNEpcnGsUFyg4RZexS05JBMr+ZQ+U7sTEY/z+gpB6Zec6OqvjBcXxja/rENhxvpM2vg8mYI7KLx4S6DDRDYinngNn/sgoTOB8h1gu1LRcKUX5KH+GOYXmlg47JXuD1QrrmF6cwoxGeMqzMLVVerxUppN9aCfUW2oQKBgQD3KFK7qjG1/Wvwj6vTy7aYZFQPNHa4FJTC6R33z388U1sQ+Xoc0yGM7Zp2WFoswkijlDeIO+74RCvC/LwV+mzCg5Y8jC++UgYhK2KFSDrZzGBRhpMxZL41kTIzWXfYkrDj4guhYbU00pxRVhFT/oEWwtNik0ke+hsuLqIxGwoYcwKBgQCZNIGUbjPt8aOiGo6rZzb1ey/dk76dQfIVf6JegnFSjQzYUyPeGU+WoCFsNu+/0wCxtrHpx7YZ1aTzTRR+SSEB2oCJ2bCzU3ua0xb56LBNQYJEEMpSMa+UGfKAzog/5yVJlMQoY0IcEUo/LTOb9V76RSTRVU8uArfZs4k7/o6aCQKBgEgPta+Ju9y3+tS0zwB9kMt11Rxpy4N4XLdHiLlMAmjwVMjqNDRbSpjwXezLbd9G9i7bdCWAc/hmx+ueDKjLzxIXk6IrrW/sMmVRZPQ/TwlZkWuztQ7dtKehoeVe3JaEjpaivYlcF51DGtiMI5k9xx1/yGohT1x8x5f7eMagf50JAoGBAI0gUuU6Btf/KW7f6+B0eanzygngWUxfPPMU2asZme4jRFvZakbjJe9onqEikgMwCYmq84SYlWSC6ym8LU6ParpcXt+dCiqGkmvyW03mTNE9sROmK9k/L6/LnxmMJvJGqJB5W6CgnntgafZ8WvglNVJxcxLp9MA7fMQroBUFzrKRAoGAJb+ZvilLZfgyXMwB4DIZIlm0OpwxkgzWmAeId+oSwDUsEd4x3MIEQPmMW6Qz9tj4vcF727r9pVNExV0K5xTIolSSa23TF8oI/UVzv4tM+cehtEivHiU47572/lGYGsnxyrUbotsVi2jmrFg6JOnntk334Keha5DmbJq3iOOHKMk=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqQlN0DJQTIDM7qmNmiKzyI+4pXEZqn4vUCI4nVLarU2Uk+tVwRKws4i/mnftyVMHMh1KLEXcc5BM4Gp+NAX1Y3onDT7Kx2LJ+XCZ99R4X6y+F5p5Z1L+UpkZOY3YlugBSM+g3C5GyyGCaPe+doO5QLKHQiBiCxeRNMcdebopXZ01V8IWRAN+Qcj4lnXII1mYAnLwvHib2iF1WhG+TzstENpfLyntRMvsT8mSGvnRjmRU8ccRdkV1mJ/4VrOElSDao1Qd9jzx2hGVcf1UQfMdMwEcaeoe6HxBK2HaUF2sq9JV+Pw/piL7ubPewYLKmuvCIYdbPbQzzc2DORjx6tAweQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://k24808384h.qicp.vip/pay/save";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://k24808384h.qicp.vip/shuaixinjie/bookroom.html";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "F:\\";


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

