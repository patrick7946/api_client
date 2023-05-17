package net.ibebu.user.back.data.dao

import jakarta.persistence.*
import net.ibebu.user.common.data.enums.LoginTypeEnum
import net.ibebu.user.core.base.BaseDateEntity
import net.ibebu.user.core.base.BaseEntity
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
@Table(name = "USR_USER")
@DynamicUpdate
data class User(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "USER_UUID")
    val userUuid: UUID? = null,
    @Column(name = "USER_EMAIL")
    val userEmail: String,
    @Column(name = "USER_PWD")
    val userPwd: String,
    @Column(name = "USER_NAME")
    val userName: String,
    @Column(name = "LOGIN_TYPE")
    @Enumerated(EnumType.STRING)
    val loginType: LoginTypeEnum,
    @Column(name = "STATE_UUID")
    var stateUuid: UUID? = null
) : BaseEntity<User>, BaseDateEntity() {
    @OneToMany(mappedBy = "user")
    val subscriptionList: List<Subscriptions> = listOf()
}