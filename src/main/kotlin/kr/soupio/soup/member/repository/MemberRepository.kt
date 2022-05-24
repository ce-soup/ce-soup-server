package kr.soupio.soup.member.repository

import kr.soupio.soup.member.entities.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<Member, String>