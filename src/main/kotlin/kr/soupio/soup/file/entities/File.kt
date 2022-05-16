package kr.soupio.soup.file.entities

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import kr.soupio.soup.core.entities.Core
import kr.soupio.soup.member.entities.Member
import javax.persistence.*

@Entity
class File(
    @Column(name= "`key`")
    val key: String,

    @Enumerated(EnumType.STRING)
    val type: FileTypeEnum,

    val mime: String,

    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploader_id")
    val uploader: Member,
) : Core(){
    override fun toString() = kotlinToString(properties = toStringProperties)

    override fun equals(other: Any?) = kotlinEquals(other = other, properties = equalsAndHashCodeProperties)

    override fun hashCode() = kotlinHashCode(properties = equalsAndHashCodeProperties)

    companion object {
        private val equalsAndHashCodeProperties = arrayOf(File::id)
        private val toStringProperties = arrayOf(
            File::id,
            File::key,
            File::type,
            File::mime,
            File::name,
            File::uploader,
            File::createdAt,
            File::updatedAt,
        )
    }
}