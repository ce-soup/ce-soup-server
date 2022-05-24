package kr.soupio.soup.member.service

import Member
import MemberServiceGrpc
import io.grpc.stub.StreamObserver
import kr.soupio.soup.member.dto.request.CreateMemberRequest
import kr.soupio.soup.member.entities.SexEnum
import kr.soupio.soup.member.service.MemberService
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class MemberGrpcService(
    private val memberService: MemberService
) : MemberServiceGrpc.MemberServiceImplBase() {

    override fun createMember(
        request: Member.CreateMemberReq,
        responseObserver: StreamObserver<Member.MemberRes>
    ) {
        val id: String? = memberService.join(
            CreateMemberRequest(request.name, SexEnum.valueOf(request.sex.toString()))
        )
        val response: Member.MemberRes = Member.MemberRes.newBuilder()
            .setMemberId(id)
            .build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}