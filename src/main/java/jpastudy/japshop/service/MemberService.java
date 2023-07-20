package jpastudy.japshop.service;

import jpastudy.japshop.domain.Member;
import jpastudy.japshop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // final 붙은 필드만 생성자 생성
public class MemberService {

    private final MemberRepository memberRepository;

//    public MemberService(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }

    //회원가입
    @Transactional
    public Long join(Member member) {
        
        validateDuplicatemember(member);
        memberRepository.save(member);
        return member.getId(); // 리포지토리 save 함수에서 .persist를 사용하면 영속성 컨텍스트에 저장이 되면서 id 값을 보장한다.
    }

    private void validateDuplicatemember(Member member) {

        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();

    }

    @Transactional(readOnly = true)
    public Member findOne (Long id){
        return memberRepository.findOne(id);
    }
}
