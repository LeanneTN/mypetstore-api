package org.csu.mypetstore.api.controller.front;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import cn.hutool.core.util.RandomUtil;
import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.entity.AlipayConfig;
import org.csu.mypetstore.api.entity.Order;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

@RestController
public class PayController {
    @PostMapping("/pay")
    @ResponseBody
    public void payController(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("totalPrice") BigDecimal totalPrice,
            @RequestParam("orderId") int orderId,
            @RequestParam("billToFirstName") String billToFirstName,
            @RequestParam("billAddress1") String billAddress1) throws IOException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        String newOrderId = DigestUtils.md5DigestAsHex(RandomUtil.randomNumbers(5).getBytes(StandardCharsets.UTF_8));

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = newOrderId;
        //付款金额，必填
        String total_amount = totalPrice.toString();
        //订单名称，必填
        String subject = billToFirstName;
        //商品描述，可空
        String body = billAddress1;

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }
}