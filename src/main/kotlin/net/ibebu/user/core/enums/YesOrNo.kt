package net.ibebu.user.core.enums

enum class YesOrNo {
    N, Y;

    fun toBoolean(): Boolean {
        return this == Y
    }

    fun toReverseYn(): YesOrNo {
        return if (this == Y) N else Y
    }

    companion object {
        fun find(boolean: Boolean): YesOrNo {
            return when (boolean) {
                true -> Y
                false -> N
            }
        }


        fun toYesOrNoEmpty(yesOrNo: YesOrNo?): YesOrNo {
            return yesOrNo ?: N
        }

        fun String.toYesOrNoEnum():YesOrNo{
            return YesOrNo.valueOf(this)
        }
    }
}
