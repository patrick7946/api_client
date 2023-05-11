package net.ibebu.user.back.data.dtd

import net.ibebu.user.back.data.dao.User
import net.ibebu.user.common.data.enums.LoginTypeEnum
import net.ibebu.user.common.data.enums.LoginTypeEnum.Companion.toLoginTypeEnum
import net.ibebu.user.core.base.BaseDto
import net.ibebu.user.core.enums.YesOrNo
import net.ibebu.user.core.enums.YesOrNo.Companion.toYesOrNoEnum
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
            fun of(tokenData: HashMap<String, String>): UdUserTokenPrincipal {
                return UdUserTokenPrincipal(
                    userUuid = UUID.fromString(tokenData["userUuid"]),
                    userEmail = tokenData["userEmail"]!!
                )
            }
        }
    }

    data class UdUserTokenCredentials(
        override val loginType: LoginTypeEnum,
        override val paidMemberYn: YesOrNo
    ) : UdUserTokenCredentialsIf {
        companion object {
            fun of(tokenData: HashMap<String, String>): UdUserTokenCredentials {
                return UdUserTokenCredentials(
                    loginType = tokenData["loginType"]!!.toLoginTypeEnum(),
                    paidMemberYn = tokenData["paidMemberYn"]!!.toYesOrNoEnum()
                )
            }
        }
    }
}