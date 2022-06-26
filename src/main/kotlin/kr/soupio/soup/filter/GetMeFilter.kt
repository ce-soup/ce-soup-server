package kr.soupio.soup.filter

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kr.soupio.soup.member.dto.token.TokenInfo
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Component
class GetMeFilter : Filter {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        // ServletRequest에서 header 정보를 가져옴
        // header에서 Authorization를 키로 데이터를 가져옴
        val header: String = (request as HttpServletRequest).getHeader("Authorization") as String

        // JsonString을 TokenInfo 객체로 역직렬화
        val tokenInfo: TokenInfo = Json.decodeFromString(header)

        request.setAttribute("memberId", tokenInfo.memberId)
        chain.doFilter(request, response)
    }
}
