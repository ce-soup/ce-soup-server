package kr.soupio.soup.member.entities

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import kr.soupio.soup.core.entities.Core
import kr.soupio.soup.file.entities.File
import kr.soupio.soup.review.entities.Review
import javax.persistence.*

@Entity
class Member(
    @Column(nullable = false)
    val name: String,

    val sex: SexEnum,

    @OneToOne
    @JoinColumn(name = "id")
    val profileImage: File,

    val bio: String,

    @OneToMany(mappedBy = "recipient")
    val reviews: List<Review>,

    val reviewCount: Int,

    @ElementCollection
    @CollectionTable()
    val deviceToken: List<String>
) : Core(){

    override fun toString() = kotlinToString(properties = toStringProperties)

    override fun equals(other: Any?) = kotlinEquals(other = other, properties = equalsAndHashCodeProperties)

    override fun hashCode() = kotlinHashCode(properties = equalsAndHashCodeProperties)

    companion object {
        private val equalsAndHashCodeProperties = arrayOf(Member::id)
        private val toStringProperties = arrayOf(
            Member::id,
            Member::name,
            Member::sex,
            Member::profileImage,
            Member::bio,
            Member::reviews,
            Member::reviewCount,
            Member::deviceToken,
            Member::createdAt,
            Member::updatedAt
        )
    }
}