package kr.soupio.soup.file.entities

import kr.soupio.soup.core.entities.Core
import kr.soupio.soup.member.entities.Member
import javax.persistence.*

@Entity
class File(
    @Column(name = "`key`", nullable = false)
    var key: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var type: FileTypeEnum,

    @Column(nullable = false)
    var mime: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploader_id")
    var uploader: Member,
) : Core()
