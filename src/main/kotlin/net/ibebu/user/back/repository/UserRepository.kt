package net.ibebu.user.back.repository

import net.ibebu.user.back.data.dao.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {
    fun findAllByUserEmail(email: String): List<User>

    fun findTopByUserEmailOrderByRegDt(userEmail: String): User?
}