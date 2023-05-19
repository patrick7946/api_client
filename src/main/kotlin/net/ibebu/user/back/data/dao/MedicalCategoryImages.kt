package net.ibebu.user.back.data.dao

import jakarta.persistence.*
import net.ibebu.user.core.base.BaseDateHistoryEntity
import net.ibebu.user.core.base.BaseEntity
import net.ibebu.user.core.enums.YesOrNo
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
@Table(name = "MDC_MEDICAL_CATEGORY_IMAGES")
@IdClass(MedicalCategoryImagesId::class)
data class MedicalCategoryImages(
    @Id
    @Column(name = "MEDICAL_CATEGORY_UUID")
    val medicalCategoryUuid: UUID? = null,
    @Id
    @Column(name = "IMAGE_SEQUENCE", columnDefinition = "tinyint")
    var imageSequence: Short? = null,
    @Column(name = "IMAGE_URL")
    val imageUrl: String,
    @Column(name = "DEL_YN", columnDefinition = "bit")
    var delYn: YesOrNo = YesOrNo.N,
) : BaseEntity<MedicalCategoryImages>, BaseDateHistoryEntity(){
//    @ManyToOne
//    @JoinColumn(name = "MEDICAL_CATEGORY_UUID", insertable = false, updatable = false)
//    val medicalCategories: MedicalCategories? = null
}