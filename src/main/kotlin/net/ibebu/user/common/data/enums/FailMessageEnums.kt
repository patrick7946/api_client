package net.ibebu.user.common.data.enums

import net.ibebu.user.core.base.BaseEnum


enum class FailMessageEnums(val message: String, val title: String, val content: String) :
    BaseEnum<FailMessageEnums> {
    EMPTY_USER("존재하지 않는 이메일입니다.","로그인 실패","존재하지 않는 이메일입니다."),
    INVALID_PASSWORD("잘못된 비밀번호 입니다.","로그인 실패","잘못된 비밀번호 입니다."),
    INVALID_IDENTIFICATION("잘못된 신분증 입니다.","검증 실패","잘못된 신분증 입니다.");
    override val value = this
}