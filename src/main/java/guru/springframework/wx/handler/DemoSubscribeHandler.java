package guru.springframework.wx.handler;

import java.util.Map;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

public class DemoSubscribeHandler implements WxMpMessageHandler {

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		String content = "感谢关注此公众号,这是个人的开发公众号,有些有趣的试验~~,可以自动回复的. ";
		content += "/n <a href='http://119.29.146.177/login'>外链试验</a>";
		return WxMpXmlOutMessage
		        .TEXT()
		        .content(content)
		        .fromUser(wxMessage.getToUserName())
		        .toUser(wxMessage.getFromUserName()).build();
	}

}
