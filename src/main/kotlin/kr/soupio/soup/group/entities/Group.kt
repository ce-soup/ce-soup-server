package kr.soupio.soup.group.entities

import kr.soupio.soup.category.entities.Category
import kr.soupio.soup.core.entities.Core
import kr.soupio.soup.file.entities.File
import kr.soupio.soup.member.entities.Member
import org.hibernate.annotations.ColumnDefault
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "`group`")
class Group(
    @Column(nullable = false)
    var type: GroupTypeEnum,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var content: String,

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    var category: Category?,

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "image_id", nullable = true)
    var image: File?,

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    var manager: Member,

    @Column(nullable = false)
    var isOnline: Boolean,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var scope: GroupScopeEnum,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var recruitment: GroupRecruitmentEnum,

    @Column(nullable = false)
    var startHour: Int,

    @Column(nullable = false)
    var startMinute: Int,

    @Column(nullable = true)
    var endHour: Int? = null,

    @Column(nullable = true)
    var endMinute: Int? = null,

    @ElementCollection
    @CollectionTable()
    var dayOfTheWeek: MutableList<DayOfTheWeek>? = ArrayList(),

    @Column(nullable = true)
    var meetingLink: String? = null,
) : Core() {
    @Column(nullable = false)
    @ColumnDefault("0")
    var personnel: Int = 0

    @Column(nullable = false)
    @ColumnDefault("0")
    var views: Int = 0

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: GroupStatusEnum = GroupStatusEnum.READY

    @Column(nullable = false)
    var startDate: LocalDate = LocalDate.now()

    @Column(nullable = true)
    var endDate: LocalDate? = null
}
