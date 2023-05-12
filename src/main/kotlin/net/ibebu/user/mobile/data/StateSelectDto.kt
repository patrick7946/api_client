package net.ibebu.user.mobile.data

import io.swagger.v3.oas.annotations.media.Schema
import net.ibebu.user.back.data.dtd.StateDtd
import net.ibebu.user.core.base.BaseResponsePopup
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
                fun of(state: StateDtd.SdStates.SsState): SslrState {
                    return SslrState(
                        stateUuid = state.stateUuid,
                        stateName = state.stateName
                    )
                }
            }
        }

        companion object {
            fun of(data: StateDtd.SdStates): SsdStateListResponse {
                return SsdStateListResponse(
                    stateList = data.states.map { SslrState.of(it) }
                )
            }
        }
    }
}