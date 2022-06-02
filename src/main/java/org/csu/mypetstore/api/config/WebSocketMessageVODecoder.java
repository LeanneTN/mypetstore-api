package org.csu.mypetstore.api.config;

import javax.websocket.DecodeException;
import javax.websocket.EndpointConfig;

import com.alibaba.fastjson.JSON;
import org.csu.mypetstore.api.vo.MessageVO;

public class WebSocketMessageVODecoder implements javax.websocket.Decoder.Text<MessageVO> {

    @Override
    public void destroy() {

    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public MessageVO decode(String message) throws DecodeException {
        return JSON.parseObject(message, MessageVO.class);
    }

    @Override
    public boolean willDecode(String arg0) {
        return true;
    }

}
