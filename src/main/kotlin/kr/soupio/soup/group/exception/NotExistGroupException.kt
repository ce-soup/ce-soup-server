package kr.soupio.soup.group.exception

class NotExistGroupException(
    private val groupId: String,
) : RuntimeException("[${groupId}] 존재하지 않는 groupId입니다.")
