package net.ibebu.user.mobile.data

import io.swagger.v3.oas.annotations.media.Schema
import net.ibebu.user.back.data.dtd.DeployDtd
import net.ibebu.user.back.data.dtd.UserDtd
import net.ibebu.user.common.data.enums.DeviceTypeEnums
import net.ibebu.user.common.data.enums.FailMessageEnums
import net.ibebu.user.common.data.enums.PopupTypeEnum
import net.ibebu.user.core.base.BaseValidation
import net.ibebu.user.core.base.BaseResponsePopup
import net.ibebu.user.core.enums.YesOrNo
import net.ibebu.user.core.token.TokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.password.PasswordEncoder

object SplashDto {
    data class SdVersion(
        @Schema(title = "디바이스 타입", example = "A", required = true)
        val deviceType: DeviceTypeEnums
    ) : BaseValidation<SdVersion, SdVersionResponse>

    data class SdVersionResponse(
        @Schema(title = "최신 버전", example = "1.0.0", required = true)
        val version: String,
        @Schema(title = "버전 업데이트 제목", example = "업데이트 제목", required = true)
        val title: String,
        @Schema(title = "버전 업데이트 내용", example = "업데이트 내용", required = true)
        val content: String
    ) : BaseResponsePopup() {
        companion object {
            fun of(response: DeployDtd.DdVersionResponse): SdVersionResponse {
                return SdVersionResponse(
                    version = response.version,
                    title = response.title,
                    content = response.content
                )
            }
        }
    }

    data class SdLogin(
        @Schema(title = "로그인 이메일", example = "qk54r71@nate.com", required = true)
        val email: String,
        @Schema(title = "로그인 패스워드", example = "ibebu794613", required = true)
        val password: String
    ) : BaseValidation<SdLogin, SdLoginResponse>

    data class SdLoginResponse(
        @Schema(title = "사용자 정보를 담고 있는 TOKEN", example = "", required = true)
        val loginToken: String,
        @Schema(title = "유료 회원 여부(Y : 유료, N : 무료)", example = "", required = true)
        val paidMemberYn: YesOrNo
    ) : BaseResponsePopup() {
        companion object {
            fun validation(
                passwordEncoder: PasswordEncoder,
                password: String,
                response: UserDtd.UdLoginResponse,
                tokenProvider: TokenProvider
            ): SdLoginResponse {
                if (response.userUuid == null) {
                    return SdLoginResponse("", YesOrNo.N).apply {
                        this.popupData = PopupMessage.of(PopupTypeEnum.TOAST, FailMessageEnums.EMPTY_USER)
                    }
                }

                if (!passwordEncoder.matches(password, response.userPwd)) {
                    return SdLoginResponse("", YesOrNo.N).apply {
                        this.popupData = PopupMessage.of(PopupTypeEnum.TOAST, FailMessageEnums.INVALID_PASSWORD)
                    }
                }

                return SdLoginResponse(
                    loginToken = createToken(response,tokenProvider),
                    paidMemberYn = response.paidMemberYn!!
                )
            }

            private fun createToken(
                response: UserDtd.UdLoginResponse,
                tokenProvider: TokenProvider
            ): String {
                return tokenProvider.createToken(response)
            }
        }
    }
}