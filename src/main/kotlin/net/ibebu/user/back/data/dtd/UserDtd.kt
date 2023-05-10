package net.ibebu.user.back.data.dtd

import io.jsonwebtoken.Claims
import net.ibebu.user.back.data.dao.User
import net.ibebu.user.common.data.enums.LoginTypeEnum
import net.ibebu.user.core.base.BaseDto
import net.ibebu.user.core.enums.YesOrNo
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

object UserDtd {
    data class UdLoginResponse(
        val userUuid: UUID?,
        val userEmail: String?,
        val userPwd: String?,
        val loginType: LoginTypeEnum?,
        val paidMemberYn: YesOrNo?
    ) {
        companion object Of : BaseDto<User?, UdLoginResponse> {
            override fun of(entity: User?): UdLoginResponse {
                return UdLoginResponse(
                    userUuid = entity?.userUuid,
                    userEmail = entity?.userEmail,
                    userPwd = entity?.userPwd,
                    loginType = entity?.loginType,
                    paidMemberYn = YesOrNo.N
                )
            }
        }
    }

    data class UdUserSave(
        val userEmail: String,
        val userPwd: String,
        val loginType: LoginTypeEnum,
    ) {
        fun toEntity(passwordEncoder: PasswordEncoder): User {
            return User(
                userEmail = this.userEmail,
                userPwd = passwordEncoder.encode(this.userPwd),
                loginType = this.loginType
            )
        }
    }

    interface UdUserTokenPrincipalIf {
        val userUuid: UUID
        val userEmail: String
    }

    interface UdUserTokenCredentialsIf {
        val loginType: LoginTypeEnum
        val paidMemberYn: YesOrNo
    }

    data class UdUserToken(
        override val userUuid: UUID,
        override val userEmail: String,
        override val loginType: LoginTypeEnum,
        override val paidMemberYn: YesOrNo
    ) : UdUserTokenPrincipalIf, UdUserTokenCredentialsIf

    data class UdUserTokenPrincipal(
        override val userUuid: UUID,
        override val userEmail: String
    ) : UdUserTokenPrincipalIf {
        companion object {
            fun of(tokenData: Claims): UdUserTokenPrincipal {
                return UdUserTokenPrincipal(
                    userUuid = tokenData.get("userUuid", UUID::class.java),
                    userEmail = tokenData.get("userEmail", String::class.java)
                )
            }
        }
    }

    data class UdUserTokenCredentials(
        override val loginType: LoginTypeEnum,
        override val paidMemberYn: YesOrNo
    ) : UdUserTokenCredentialsIf {
        companion object {
            fun of(tokenData: Claims): UdUserTokenCredentials {
                return UdUserTokenCredentials(
                    loginType = tokenData.get("loginType", LoginTypeEnum::class.java),
                    paidMemberYn = tokenData.get("paidMemberYn", YesOrNo::class.java)
                )
            }
        }
    }
}