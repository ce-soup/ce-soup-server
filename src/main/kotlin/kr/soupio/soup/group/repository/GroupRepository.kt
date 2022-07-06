package kr.soupio.soup.group.repository

import kr.soupio.soup.group.entities.Group
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GroupRepository : JpaRepository<Group, String> {
    fun findByName(name: String): Group?

    fun findByNameContaining(keyword: String, pageable: Pageable): Page<Group>
}