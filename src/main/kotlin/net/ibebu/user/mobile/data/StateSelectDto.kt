package net.ibebu.user.mobile.data

import io.swagger.v3.oas.annotations.media.Schema
import net.ibebu.user.back.data.dtd.StateDtd
import net.ibebu.user.back.data.dtd.UserDtd
import net.ibebu.user.common.utils.JwtUtil.getUserUuid
import net.ibebu.user.core.base.BaseResponsePopup
import net.ibebu.user.core.base.BaseValidation
import java.util.*

object StateSelectDto {
    data class SsdStateListResponse(
        @Schema(title = "주 리스트", required = true)
        val stateList: List<SslrState>,
    ) : BaseResponsePopup() {
        data class SslrState(
            @Schema(title = "주 범용식별자", required = true)
            val stateUuid: UUID,
            @Schema(title = "주 이름", required = true)
            val stateName: String
        ) {
            companion object {
                fun of(state: StateDtd.SdStatesResponse.SsState): SslrState {
                    return SslrState(
                        stateUuid = state.stateUuid,
                        stateName = state.stateName
                    )
                }
            }
        }

        companion object {
            fun of(data: StateDtd.SdStatesResponse): SsdStateListResponse {
                return SsdStateListResponse(
                    stateList = data.states.map { SslrState.of(it) }
                )
            }
        }
    }

    data class SsdStateSelectRequest(
        val stateUuid: UUID
    ) : BaseValidation<SsdStateSelectRequest, SsdStateSelectResponse> {
        fun toRequestDtd(): UserDtd.UdStateUpdateRequest {
            return UserDtd.UdStateUpdateRequest(
                stateUuid = stateUuid,
                userUuid = getUserUuid()
            )
        }
    }

    data class SsdStateSelectResponse(
        @Schema(title = "메시지", required = true)
        val message: String
    ) : BaseResponsePopup() {
        companion object {
            fun of(): SsdStateSelectResponse {
                return SsdStateSelectResponse(
                    "설정이 완료 되었습니다."
                )
            }
        }
    }
}