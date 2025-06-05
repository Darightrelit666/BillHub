// 文件：src/main/java/com/heiyuk6/bilihub/interfaces/captcha/CaptchaController.java
package com.heiyuk6.bilihub.interfaces;

import com.heiyuk6.bilihub.application.captcha.service.CaptchaAppService;
import com.heiyuk6.bilihub.common.result.ApiResponse;
import cloud.tianai.captcha.application.vo.ImageCaptchaVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CaptchaController {

    private final CaptchaAppService captchaAppService;

    public CaptchaController(CaptchaAppService captchaAppService) {
        this.captchaAppService = captchaAppService;
    }

    @GetMapping("/api/captcha")
    public ApiResponse<ImageCaptchaVO> getCaptcha() {
        ImageCaptchaVO vo = captchaAppService.generateCaptcha();
        return ApiResponse.success(vo);
    }
}
