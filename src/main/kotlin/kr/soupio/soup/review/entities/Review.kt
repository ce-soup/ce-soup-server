package kr.soupio.soup.review.entities

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import kr.soupio.soup.core.entities.Core
import kr.soupio.soup.member.entities.Member
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
class Review(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id")
    val recipient : Member,

    @OneToOne
    @JoinColumn(name = "writer_id")
    val writer: Member,

    val content: String,

) : Core(){

//    fun addMember(member: Member){
//        this.recipient = member
//        member.setReviews()
//    }

    override fun toString() = kotlinToString(properties = toStringProperties)

    override fun equals(other: Any?) = kotlinEquals(other = other, properties = equalsAndHashCodeProperties)

    override fun hashCode() = kotlinHashCode(properties = equalsAndHashCodeProperties)

    companion object {
        private val equalsAndHashCodeProperties = arrayOf(Review::id)
        private val toStringProperties = arrayOf(
            Review::id,
            Review::writer,
            Review::content,
            Review::createdAt,
            Review::updatedAt,
        )
    }
}