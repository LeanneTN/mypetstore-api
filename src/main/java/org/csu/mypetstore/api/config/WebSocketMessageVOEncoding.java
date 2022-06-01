package org.csu.mypetstore.api.config;

import com.alibaba.fastjson.JSON;
import org.csu.mypetstore.api.vo.MessageVO;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class WebSocketMessageVOEncoding implements Encoder.Text<MessageVO> {
    @Override
    public String encode(MessageVO messageVO) throws EncodeException {
        assert messageVO != null;
        return JSON.toJSONString(messageVO);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
