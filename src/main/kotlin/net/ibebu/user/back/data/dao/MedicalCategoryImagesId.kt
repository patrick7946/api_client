package net.ibebu.user.back.data.dao

import jakarta.persistence.*
import net.ibebu.user.common.data.enums.LoginTypeEnum
import net.ibebu.user.core.base.BaseDateEntity
import net.ibebu.user.core.base.BaseEntity
import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import java.util.*

data class MedicalCategoryImagesId(
    @Id
    @Column(name = "MEDICAL_CATEGORY_UUID")
    val medicalCategoryUuid: UUID? = null,
    @Id
    @Column(name = "IMAGE_SEQUENCE", columnDefinition = "tinyint")
    var imageSequence: Short? = null,
) : Serializable