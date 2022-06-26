package kr.soupio.soup.member.entities

import kr.soupio.soup.core.entities.Core
import kr.soupio.soup.file.entities.File
import kr.soupio.soup.review.entities.Review
import org.hibernate.annotations.ColumnDefault
import javax.persistence.*

@Entity
class Member(
    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var sex: SexEnum,

    @Column(nullable = true)
    var bio: String? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = true)
    var profileImage: File? = null,
) : Core() {
    @OneToMany(mappedBy = "recipient", cascade = [CascadeType.ALL])
    var reviews: MutableList<Review> = ArrayList()

    @ElementCollection
    @CollectionTable()
    var deviceToken: List<String> = ArrayList()

    @Column(nullable = false)
    @ColumnDefault("0")
    var reviewCount: Int = 0

    fun changeProfileImage(newProfile: File) {
        this.profileImage = newProfile
    }
}