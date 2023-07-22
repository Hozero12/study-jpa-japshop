package jpastudy.japshop.service;

import jpastudy.japshop.domain.Member;
import jpastudy.japshop.repository.MemberRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)  // junit 과 스프링 컨테이너를 같이 사용
@SpringBootTest  //springboot 사용 가능 - @Autowired 등 사용가능
@Transactional  //테스트에서는 기본적으로 Rollback 한다
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    //@Rollback(false)    - 실제 db에 쿼리를 날리도록 설정
   public void 회원가입(){

        Member member = new Member();
        member.setName("kim");

        Long savedId = memberService.join(member);

        assertEquals(member, memberService.findOne(savedId));

    }


    @Test(expected = IllegalArgumentException.class)
    public void 중복회원_예외(){

        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        memberService.join(member1);
        memberService.join(member2);

        fail("예외발생실패");
    }
}