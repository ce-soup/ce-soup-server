package kr.soupio.soup.file.service

import kr.soupio.soup.exception.NotExistFileException
import kr.soupio.soup.file.entities.File
import kr.soupio.soup.file.entities.FileTypeEnum
import kr.soupio.soup.file.repository.FileRepository
import kr.soupio.soup.member.entities.Member
import kr.soupio.soup.minio.service.MinioService
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*


@Service
class FileService(
    private val fileRepository: FileRepository,
    private val minioService: MinioService
) {

    fun upload(
        type: FileTypeEnum,
        uploader: Member,
        multipartFile: MultipartFile,
    ): File? {
        if (multipartFile.isEmpty) throw NotExistFileException()

        val mime: String? = multipartFile.contentType?.split("/")?.get(1)
        val key: String = "${type}/${UUID.randomUUID()}.${mime}"

        val file: File = fileRepository.save(File(key, type, mime!!, uploader))
        if (minioService.upload(key, multipartFile.inputStream, multipartFile.size, file.mime))
            return file
        return null
    }

    fun delete(file: File) {
        minioService.delete(file.key)
        fileRepository.deleteById(file.id!!)
    }
}
