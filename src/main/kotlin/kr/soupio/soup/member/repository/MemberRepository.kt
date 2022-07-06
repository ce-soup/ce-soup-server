package kr.soupio.soup.member.repository

import kr.soupio.soup.member.entities.Member
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<Member, String> {
    fun findByNameContaining(keyword: String, pageable: Pageable): Page<Member>
}