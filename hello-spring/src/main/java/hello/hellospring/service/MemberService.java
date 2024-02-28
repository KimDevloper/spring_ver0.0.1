package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository){ //외부에서 넣을 수 있도록 변경하여 test에서 그대로 사용수 있게 한다. di 관련
        this.memberRepository = memberRepository;
    }


    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검사, 컨트롤 + 쉬프트 + 알트 + T 기능으로 사용한 것
        //같은 이름이 있는 중복 회원 X

        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) { //컨트롤 + 쉬프트 + 알트 + T 기능으로 사용한 것
        memberRepository.findByName(member.getName())
                .ifPresent(member1 -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
