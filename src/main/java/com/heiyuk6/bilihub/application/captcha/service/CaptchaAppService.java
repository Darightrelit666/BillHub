package com.heiyuk6.bilihub.application.captcha.service;

import cloud.tianai.captcha.common.constant.CaptchaTypeConstant;
import cloud.tianai.captcha.application.ImageCaptchaApplication;
import cloud.tianai.captcha.application.vo.CaptchaResponse;
import cloud.tianai.captcha.application.vo.ImageCaptchaVO;
import com.heiyuk6.bilihub.domain.captcha.exception.CaptchaException;
import org.springframework.stereotype.Service;

/**
 * 应用层 Captcha 服务：封装 Tianai-Captcha 1.5.x 滑块验证码的生成与校验（偏移量方式）。
 */
@Service
public class CaptchaAppService {

    private final ImageCaptchaApplication imageCaptchaApplication;

    public CaptchaAppService(ImageCaptchaApplication imageCaptchaApplication) {
        this.imageCaptchaApplication = imageCaptchaApplication;
    }

    /**
     * 生成滑块验证码（SLIDER）。
     *
     * @return ImageCaptchaVO 其中包含：
     *   - getId(): 唯一的 captchaId（前端必须保存并在校验时带回）
     *   - getCaptcha(): Base64 编码的图片数据（包含背景 + 滑块）
     * @throws CaptchaException 如果生成过程中出现任何异常
     */
    public ImageCaptchaVO generateCaptcha() {
        try {
            // 一定要调用 “generateCaptcha(String type)” 这种签名，传入 CaptchaTypeConstant.SLIDER
            CaptchaResponse<ImageCaptchaVO> response =
                    imageCaptchaApplication.generateCaptcha(CaptchaTypeConstant.SLIDER);

            // 在源码里 CaptchaResponse<T> 定义的是 private T captcha;  所以要使用 getCaptcha() 拿到 VO
            return response.getCaptcha();
        } catch (Exception ex) {
            // 不管是什么原因导致的异常，一律抛成业务层的 CaptchaException
            throw new CaptchaException("验证码生成失败");
        }
    }

    /**
     * 校验滑块偏移量是否正确。
     *
     * @param captchaId   前端在调用 generateCaptcha() 时拿到的唯一 ID（String）。
     * @param offsetData  前端拖动滑块后得到的水平偏移量（String），例如 "152.5"。
     *                    我们会把它转成 float，然后调用 matching(id, float) 进行校验。
     * @return true 表示校验通过；否则抛出 CaptchaException
     * @throws CaptchaException 如果传入的 offsetData 不是有效浮点数，或者校验失败、或出现其它异常
     */
    public boolean validateCaptcha(String captchaId, String offsetData) {
        try {
            // 1. 首先尝试把前端传回的 offsetData（"152.5"）转成 float
            float offset;
            try {
                offset = Float.parseFloat(offsetData);
            } catch (NumberFormatException nfe) {
                // 如果不能成功转成 float，直接算作校验失败
                throw new CaptchaException("无效的滑块偏移量");
            }

            // 2. 调用 matching(String id, Float percentage) 这一重载签名
            //    如果匹配通过，matching 会返回 true；否则返回 false
            boolean passed = imageCaptchaApplication.matching(captchaId, offset);

            if (passed) {
                return true;
            } else {
                // 如果 matching 返回 false，则认为校验失败
                throw new CaptchaException("验证码校验失败");
            }
        } catch (CaptchaException ex) {
            // 如果前面抛出的是 CaptchaException，就继续往上抛
            throw ex;
        } catch (Exception ex) {
            // 任何其它异常，都转换成 “验证码校验异常”
            throw new CaptchaException("验证码校验异常");
        }
    }
}
