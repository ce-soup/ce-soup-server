package kr.soupio.soup.file.repository

import kr.soupio.soup.file.entities.File
import org.springframework.data.jpa.repository.JpaRepository


interface FileRepository : JpaRepository<File, String>