package kr.soupio.soup.minio.service

import io.minio.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.InputStream


@Service
class MinioService(private val minioClient: MinioClient) {

    @Value("\${minio.bucketName}")
    private val bucketName: String = "bucket"

    fun upload(key: String, inputStream: InputStream, size: Long, mime: String): Boolean {
        return try {
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucketName)
                    .`object`(key)
                    .stream(inputStream, size, -1)
                    .contentType(mime)
                    .build()
            )
            stat(key)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun delete(key: String): Boolean {
        return try {
            minioClient.removeObject(
                RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .`object`(key)
                    .build()
            )
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun stat(key: String): Boolean {
        val statObject: StatObjectResponse = minioClient.statObject(
            StatObjectArgs.builder()
                .bucket(bucketName)
                .`object`(key)
                .build()
        )
        return statObject.size() > 0
    }
}
