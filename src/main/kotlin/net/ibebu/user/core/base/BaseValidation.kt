package net.ibebu.user.core.base

interface BaseValidation<T, U> {
    fun validation(successFun: (T) -> U): U {
        return successFun(this as T)
    }
}