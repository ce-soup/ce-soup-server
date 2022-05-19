package kr.soupio.soup.review.entities

import kr.soupio.soup.core.entities.Core
import kr.soupio.soup.member.entities.Member
import javax.persistence.*

@Entity
class Review(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id")
    var recipient: Member,

    @OneToOne
    @JoinColumn(name = "writer_id")
    var writer: Member,

    var content: String,

    ) : Core() {
    fun addMember(member: Member) {
        this.recipient = member
        member.reviews.add(this)
    }
}