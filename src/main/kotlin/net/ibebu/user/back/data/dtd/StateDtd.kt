package net.ibebu.user.back.data.dtd

import net.ibebu.user.back.data.dao.States
import net.ibebu.user.core.base.BaseDto
import java.util.*

object StateDtd {
    data class SdStatesResponse(
        val states: List<SsState>
    ) {
        data class SsState(
            val stateUuid: UUID,
            val stateName: String
        ) {
            companion object Of : BaseDto<States, SsState> {
                override fun of(entity: States): SsState {
                    return SsState(
                        stateUuid = entity.stateUuid!!,
                        stateName = entity.stateNameKo
                    )
                }

            }
        }

        companion object Of : BaseDto<List<States>, SdStatesResponse> {
            override fun of(entity: List<States>): SdStatesResponse {
                return SdStatesResponse(entity.map { it.toDto(SsState.Of) })
            }
        }
    }
}