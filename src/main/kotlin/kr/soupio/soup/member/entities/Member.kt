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
    var profileImage: File? = null,

    var bio: String? = null,

    @OneToMany(mappedBy = "recipient")
    var reviews: MutableList<Review>? = null,

    var reviewCount: Int? = null,

    @ElementCollection
    @CollectionTable()
    var deviceToken: List<String>? = null
) : Core()