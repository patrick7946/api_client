package net.ibebu.user.common.utils

import net.ibebu.user.back.data.dtd.UserDtd
import org.springframework.security.core.context.SecurityContextHolder
import java.util.UUID

object JwtUtil {
    fun getUserUuid(): UUID {
        return (SecurityContextHolder.getContext().authentication.principal as UserDtd.UdUserTokenPrincipal).userUuid
    }
}