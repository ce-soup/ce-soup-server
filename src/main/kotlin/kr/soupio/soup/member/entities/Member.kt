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

	@OneToOne
	@JoinColumn(name = "id", nullable = true)
	var profileImage: File? = null,

	@OneToMany(mappedBy = "recipient")
	var reviews: MutableList<Review> = ArrayList(),

	@ElementCollection
	@CollectionTable()
	var deviceToken: List<String> = ArrayList(),
) : Core() {
	@Column(nullable = false)
	@ColumnDefault("0")
	var reviewCount: Int = 0
}