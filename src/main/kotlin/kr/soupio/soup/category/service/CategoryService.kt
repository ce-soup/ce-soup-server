package kr.soupio.soup.category.service

import kr.soupio.soup.category.entities.Category
import kr.soupio.soup.category.repository.CategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CategoryService(
    private val categoryRepository: CategoryRepository,
) {
    fun findByName(name: String): Category? {
        return categoryRepository.findByName(name)
    }
}