package kr.soupio.soup.group.exception

class NoGroupManagerAuthorityException : RuntimeException(
    "그룹 매니저 권한이 없습니다."
)