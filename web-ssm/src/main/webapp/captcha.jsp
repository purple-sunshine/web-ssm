<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="org.patchca.color.SingleColorFactory"%>
<%@ page import="org.patchca.filter.predefined.*"%>
<%@ page import="org.patchca.font.RandomFontFactory"%>
<%@ page import="org.patchca.service.ConfigurableCaptchaService"%>
<%@ page import="org.patchca.utils.encoder.EncoderHelper"%>
<%@ page import="org.patchca.word.AdaptiveRandomWordFactory,java.awt.*,java.io.OutputStream"%>
<%@ page import="java.util.Random"%>
<%@ page contentType="image/png"%>
<%
	String codeKey = request.getParameter("key");
	codeKey = StringUtils.isEmpty(codeKey)? "verify-code-seesion-key" : codeKey;
	ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
	// 验证码字符组成
	AdaptiveRandomWordFactory wordFactory = new AdaptiveRandomWordFactory();
	wordFactory.setCharacters("absdekmntwxAEGHLRT2345678#@%=*&");
	wordFactory.setMinLength(5);
	cs.setWordFactory(wordFactory);
	cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
	cs.setHeight(40);
	cs.setWidth(140);
	RandomFontFactory fontFactory = new RandomFontFactory();
	fontFactory.setMinSize(30);
	fontFactory.setMaxSize(30);
	cs.setFontFactory(fontFactory);
	int filter = new Random().nextInt(3);
	switch (filter) {//update by walden 删除比较模糊的大理石纹和扩散纹方便用户使用
	case 0: // 干扰线
		cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
		break;
	case 1: // 振动纹
		cs.setFilterFactory(new DoubleRippleFilterFactory());
		break;
	case 2: // 摆动纹
		cs.setFilterFactory(new WobbleRippleFilterFactory());
		break;
	}
	OutputStream os = response.getOutputStream();
	session.setAttribute(codeKey, EncoderHelper.getChallangeAndWriteImage(cs, "png", os));
	os.flush();
	os.close();
	response.flushBuffer();
	out.clear();
%>