package kr.soupio.soup.member.entities

import kr.soupio.soup.core.entities.Core
import kr.soupio.soup.file.entities.File
import kr.soupio.soup.review.entities.Review
import javax.persistence.*

@Entity
class Member(
    @Column(nullable = false)
    var name: String,

    var sex: SexEnum,

    @OneToOne
    @JoinColumn(name = "id")
    var profileImage: File,

    var bio: String,

    @OneToMany(mappedBy = "recipient")
    var reviews: MutableList<Review>,

    var reviewCount: Int,

    @ElementCollection
    @CollectionTable()
    var deviceToken: List<String>
) : Core()