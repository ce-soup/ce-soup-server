package kr.soupio.soup.exception

class FileSizeExceededException : RuntimeException(
    "파일 사이즈가 업로드 가능한 1048576 bytes를 넘었습니다."
)