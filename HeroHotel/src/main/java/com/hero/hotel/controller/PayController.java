package com.hero.hotel.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.hero.hotel.pojo.Order;
import com.hero.hotel.pojo.User;
import com.hero.hotel.service.OrderService;
import com.hero.hotel.utils.AlipayConfig;

import jdk.nashorn.internal.objects.annotations.Constructor;

@Controller
@RequestMapping("/pay")
public class PayController {
	@Resource
	private OrderService orderService;
	@RequestMapping("/pay")
	@ResponseBody
	public void pay(String ordernumber,HttpServletRequest req,HttpServletResponse resp) {
		HttpSession session =req.getSession();
		Object obj =(User) session.getAttribute("user");
		User user =(User) obj;
		if(user==null) {
			return;
		}
		Order order =orderService.findIdByOrderNumber(ordernumber);
		System.out.println(order);
		//获取总金额
		BigDecimal  total_amount= order.getTotal();
		payMoney(order.getOrdernumber(), total_amount.doubleValue()+"",order.getOrdernumber()+total_amount.doubleValue() ,"", resp);
		
	}
	@RequestMapping("/save")
	@ResponseBody
	public void saveResult(HttpServletRequest req,String out_trade_no,String trade_no,String trade_status) throws AlipayApiException, UnsupportedEncodingException {
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = req.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
		
			params.put(name, valueStr);
		}
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——
		
		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		*/
		if(signVerified) {//验证成功
			if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				Order order =orderService.findIdByOrderNumber(out_trade_no);
				order.setPaynumber(trade_no);
				order.setFlag(1);
				orderService.InsertPay(order);
				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
			}
		}

	}
	public void payMoney(String out_trade_no,String total_amount,String subject,String body,HttpServletResponse resp){
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		
//		//商户订单号，商户网站订单系统中唯一订单号，必填
//		String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
//		//付款金额，必填
//		String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
//		//订单名称，必填
//		String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
//		//商品描述，可空
//		String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ body +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		
		//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
		//		+ "\"total_amount\":\""+ total_amount +"\"," 
		//		+ "\"subject\":\""+ subject +"\"," 
		//		+ "\"body\":\""+ body +"\"," 
		//		+ "\"timeout_express\":\"10m\"," 
		//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
		
		//请求
		String result;
		try {
			result = alipayClient.pageExecute(alipayRequest).getBody();
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out;
			try {
				out = resp.getWriter();
				out.println(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}		
	}
	@RequestMapping("/refund")
	@ResponseBody
	public void refund(HttpServletResponse resp,String ordernumber) {
		Order order =orderService.findIdByOrderNumber(ordernumber);
		
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
		
//		//商户订单号，商户网站订单系统中唯一订单号
		String out_trade_no = order.getOrdernumber();
//		//支付宝交易号
		String trade_no = order.getPaynumber();
//		//请二选一设置
//		//需要退款的金额，该金额不能大于订单金额，必填
		String refund_amount = order.getTotal().toString();
//		//退款的原因说明
		String refund_reason = "";
//		//标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
		String out_request_no = "";
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"trade_no\":\""+ trade_no +"\"," 
				+ "\"refund_amount\":\""+ refund_amount +"\"," 
				+ "\"refund_reason\":\""+ refund_reason +"\"," 
				+ "\"out_request_no\":\""+ out_request_no +"\"}");
		
		//请求
		String result;
		try {
			result = alipayClient.execute(alipayRequest).getBody();
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out;
			try {
				out = resp.getWriter();
				resp.sendRedirect("/shuaixinjie/user_center.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
