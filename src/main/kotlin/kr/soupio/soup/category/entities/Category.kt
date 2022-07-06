package kr.soupio.soup.category.entities

import kr.soupio.soup.core.entities.Core
import javax.persistence.*

@Entity
class Category(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    var parent: Category? = null,

    @Column(nullable = false)
    var name: String
) : Core()