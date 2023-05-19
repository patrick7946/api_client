package net.ibebu.user.back.data.dao

import jakarta.persistence.*
import net.ibebu.user.common.data.enums.MedicalCategoryTypeEnums
import net.ibebu.user.core.base.BaseDateHistoryEntity
import net.ibebu.user.core.base.BaseEntity
import net.ibebu.user.core.enums.YesOrNo
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
@Table(name = "MDC_MEDICAL_CATEGORIES")
data class MedicalCategories(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "MEDICAL_CATEGORY_UUID")
    val medicalCategoryUuid: UUID? = null,
    @Column(name = "CATEGORY_TYPE")
    @Enumerated(EnumType.STRING)
    val categoryType: MedicalCategoryTypeEnums,
    @Column(name = "CATEGORY_FULL_NAME")
    val categoryFullName: String,
    @Column(name = "CATEGORY_NAME")
    val categoryName: String,
    @Column(name = "CATEGORY_DEPTH", columnDefinition = "tinyint")
    val categoryDepth: Short,
    @Column(name = "PARENT_CATEGORY_UUID")
    val parentCategoryUuid: UUID? = null,
    @Column(name = "DEL_YN", columnDefinition = "bit")
    var delYn: YesOrNo = YesOrNo.N,
) : BaseEntity<MedicalCategories>, BaseDateHistoryEntity() {
    @OneToMany(mappedBy = "medicalCategories")
    val childMedicalCategories: List<MedicalCategories> = listOf()

    @ManyToOne
    @JoinColumn(name = "PARENT_CATEGORY_UUID", insertable = false, updatable = false)
    val medicalCategories: MedicalCategories? = null

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "MEDICAL_CATEGORY_UUID")
    val medicalCategoryImages: List<MedicalCategoryImages> = listOf()
}