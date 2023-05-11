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

object SubIntroDto {
    data class SidLayoutResponse(
        @Schema(title = "가입혜택 말머리", example = "IBEBU 멤버십에 가입하고 다음의 혜택을 바로 누려보세요.", required = true)
        val regBenefitTitle: String,
        @Schema(title = "가입혜택 리스트", required = true)
        val regBenefitList: List<String>,
        @Schema(title = "월 단위 결제 금액", required = true)
        val monthlyPaymentAmount: String,
        @Schema(title = "년 단위 원본 결제 금액", required = true)
        val annualOriginPaymentAmount: String,
        @Schema(title = "년 단위 원본 결제 금액 할인", required = true)
        val annualOriginPaymentDiscount: String,
        @Schema(title = "년 단위 결제 금액", required = true)
        val annualPaymentAmount: String
    ) : BaseResponsePopup() {
        companion object {
            fun init(): SidLayoutResponse {
                return SidLayoutResponse(
                    regBenefitTitle = "IBEBU 멤버십에 가입하고 다음의 혜택을 바로 누려보세요.",
                    regBenefitList = listOf(
                        "멤버십 특별가 Monthly $15 / Annual $99",
                        "비대면 진료 $3 ~ $10",
                        "한국 성형 및 피부 상담",
                        "자격증 보유한 전문 상담가와의 심리 테라피",
                        "행정 지원 서비스",
                        "다양한 지역별 패키지"
                    ),
                    monthlyPaymentAmount = "월 / $15.00",
                    annualOriginPaymentAmount = "$216.00",
                    annualOriginPaymentDiscount = "-45%",
                    annualPaymentAmount = "년 / $99.00"
                )
            }
        }
    }
}