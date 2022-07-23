package kr.soupio.soup.exception

class NotExistMemberException(
    private val memberId: String,
) : RuntimeException("[${memberId}] 존재하지 않는 memberId입니다.")