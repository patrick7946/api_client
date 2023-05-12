package net.ibebu.user.back.repository

import net.ibebu.user.back.data.dao.States
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface StatesRepository : JpaRepository<States, UUID>