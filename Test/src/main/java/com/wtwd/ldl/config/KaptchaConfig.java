package com.wtwd.ldl.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

/**
 * @Author lixiangyi
 * @Date 2019/5/8 11:41
 * @Description
 */
public class KaptchaConfig {

    @Value("${kaptcha.textproducer.char.string}")
    private String charString;
    @Value("${kaptcha.border.color}")
    private String borderColor;
    @Value("${kaptcha.textproducer.font.color}")
    private String fontColor;
    @Value("${kaptcha.textproducer.char.space}")
    private String charSpace;
    @Value("${kaptcha.image.width}")
    private String imageWidth;
    @Value("${kaptcha.image.height}")
    private String imageHeight;
    @Value("${kaptcha.session.key}")
    private String sessionKey;
    @Value("${kaptcha.textproducer.char.length}")
    private String charLength;
    @Value("${kaptcha.textproducer.font.names}")
    private String fontNames;
    @Value("${kaptcha.noise.color}")
    private String noiseColor;
    @Value("${kaptcha.background.clear.from}")
    private String bgBegin;
    @Value("${kaptcha.background.clear.to}")
    private String bgEnd;
    @Value("${kaptcha.producer.impl}")
    private String producerImpl;
    @Value("${kaptcha.textproducer.impl}")
    private String textProducerImpl;
    @Value("${kaptcha.obscurificator.impl}")
    private String obscurificatorImpl;

        public DefaultKaptcha getKaptchaBean(){
            DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
            Properties properties=new Properties();
            properties.setProperty("kaptcha.textproducer.char.string", charString);//验证码字符范围
            properties.setProperty("kaptcha.border.color", borderColor);//图片边框颜色
            properties.setProperty("kaptcha.textproducer.font.color", fontColor);//字体颜色
            properties.setProperty("kaptcha.textproducer.char.space", charSpace);//文字间隔
            properties.setProperty("kaptcha.image.width", imageWidth);//图片宽度
            properties.setProperty("kaptcha.image.height", imageHeight);//图片高度
            properties.setProperty("kaptcha.session.key", sessionKey);//session的key
            properties.setProperty("kaptcha.textproducer.char.length", charLength);//长度
            properties.setProperty("kaptcha.textproducer.font.names", fontNames);//字体
            properties.setProperty("kaptcha.noise.color", noiseColor);//干扰颜色
            properties.setProperty("kaptcha.background.clear.from", bgBegin);//背景颜色渐变起始
            properties.setProperty("kaptcha.background.clear.to", bgEnd);//背景颜色渐变结束
            properties.setProperty("kaptcha.producer.impl", producerImpl);//图片实现类
            properties.setProperty("kaptcha.textproducer.impl", textProducerImpl);//文本实现类
            properties.setProperty("kaptcha.obscurificator.imp", obscurificatorImpl);//图片样式
            Config config=new Config(properties);
            defaultKaptcha.setConfig(config);
            return defaultKaptcha;
        }

    public String getCharString() {
        return charString;
    }

    public void setCharString(String charString) {
        this.charString = charString;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getCharSpace() {
        return charSpace;
    }

    public void setCharSpace(String charSpace) {
        this.charSpace = charSpace;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(String imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(String imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getCharLength() {
        return charLength;
    }

    public void setCharLength(String charLength) {
        this.charLength = charLength;
    }

    public String getFontNames() {
        return fontNames;
    }

    public void setFontNames(String fontNames) {
        this.fontNames = fontNames;
    }

    public String getNoiseColor() {
        return noiseColor;
    }

    public void setNoiseColor(String noiseColor) {
        this.noiseColor = noiseColor;
    }

    public String getBgBegin() {
        return bgBegin;
    }

    public void setBgBegin(String bgBegin) {
        this.bgBegin = bgBegin;
    }

    public String getBgEnd() {
        return bgEnd;
    }

    public void setBgEnd(String bgEnd) {
        this.bgEnd = bgEnd;
    }

    public String getProducerImpl() {
        return producerImpl;
    }

    public void setProducerImpl(String producerImpl) {
        this.producerImpl = producerImpl;
    }

    public String getTextProducerImpl() {
        return textProducerImpl;
    }

    public void setTextProducerImpl(String textProducerImpl) {
        this.textProducerImpl = textProducerImpl;
    }

    public String getObscurificatorImpl() {
        return obscurificatorImpl;
    }

    public void setObscurificatorImpl(String obscurificatorImpl) {
        this.obscurificatorImpl = obscurificatorImpl;
    }
}
