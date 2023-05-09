package net.ibebu.user.core.base

interface BaseEntity<T> {
    fun <U> toDto(dto : BaseDto<T, U>) = dto.of(this as T)
}
