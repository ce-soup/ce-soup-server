package kr.soupio.soup.file.entities

import kr.soupio.soup.core.entities.Core
import kr.soupio.soup.member.entities.Member
import javax.persistence.*

@Entity
class File(
    @Column(name = "`key`")
    var key: String,

    @Enumerated(EnumType.STRING)
    var type: FileTypeEnum,

    var mime: String,

    var name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploader_id")
    var uploader: Member,
) : Core()
