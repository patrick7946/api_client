package net.ibebu.user.core.base

interface BaseDto<T, U> {
    fun of(entity: T): U
}