package guru.springframework.wx.handler;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.*;

import java.io.InputStream;

import javax.servlet.http.HttpServlet;

import guru.springframework.wx.api.TulingRobot;

public class WxMpDemoServer {

  private static WxMpConfigStorage wxMpConfigStorage;
  private static WxMpService wxMpService;
  private static WxMpMessageRouter wxMpMessageRouter;

  /*public static void main(String[] args) throws Exception {
    initWeixin();

    Server server = new Server(8080);

    ServletHandler servletHandler = new ServletHandler();
    server.setHandler(servletHandler);

    ServletHolder endpointServletHolder = new ServletHolder(new WxMpEndpointServlet(wxMpConfigStorage, wxMpService, wxMpMessageRouter));
    servletHandler.addServletWithMapping(endpointServletHolder, "/*");

    ServletHolder oauthServletHolder = new ServletHolder(new WxMpOAuth2Servlet(wxMpService));
    servletHandler.addServletWithMapping(oauthServletHolder, "/oauth2/*");

    server.start();
    server.join();
  }*/

  public static void initWeixin() {
    InputStream is1 = ClassLoader.getSystemResourceAsStream("config.xml");
    WxMpDemoInMemoryConfigStorage config = WxMpDemoInMemoryConfigStorage.fromXml(is1);

    wxMpConfigStorage = config;
    wxMpService = new WxMpServiceImpl();
    wxMpService.setWxMpConfigStorage(config);

    WxMpMessageHandler logHandler = new DemoLogHandler();
    WxMpMessageHandler textHandler = new DemoTextHandler();
    WxMpMessageHandler imageHandler = new DemoImageHandler();
    WxMpMessageHandler oauth2handler = new DemoOAuth2Handler();
    DemoGuessNumberHandler guessNumberHandler = new DemoGuessNumberHandler();
    WxMpMessageHandler subscribe = new DemoSubscribeHandler();
    TulingRobot robot = new TulingRobot();
    wxMpMessageRouter = new WxMpMessageRouter(wxMpService);
    wxMpMessageRouter
          .rule().handler(logHandler).next()
          .rule().msgType(WxConsts.XML_MSG_TEXT).matcher(guessNumberHandler).handler(guessNumberHandler).end()
          .rule().async(false).content("哈哈").handler(textHandler).end()
          .rule().async(false).content("图片").handler(imageHandler).end()
          .rule().async(false).content("外链").handler(subscribe).end()
          .rule().async(false).content("oauth").handler(oauth2handler).end()
          .rule().async(false).event(WxConsts.EVT_SUBSCRIBE).handler(subscribe).end()
          .rule().async(false).handler(robot).end()
      ;
  }
  public static HttpServlet getJettyServlet(){
	initWeixin();
	return new WxMpEndpointServlet(wxMpConfigStorage, wxMpService, wxMpMessageRouter);
  }
}
