package kr.soupio.soup.category.repository

import kr.soupio.soup.category.entities.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, String> {
    fun findByName(name: String): Category?
}