package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // 순수한 테스트가 아닌 스프링과 JPA 묶어서 테스트할거기 때문에 적음.
@SpringBootTest // 스프링 부트 띄운 상태로 테스트 할라면 꼭 필요하다. 
@Transactional // 이 어노테이션을 test case에 쓰면 기본적으로 rollback이 된다!
/*
정말 중요! 각 JPA에서 같은 트랜젝션 안에서 같은 엔티티(ID,PK) 값이 같으면 같은 영속성 컨텍스트에서 똑같은 애가 관리하는 것이다. 딱 하나로
그렇기에 True가 나옵니다.
 */
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    //@Rollback(value = false) //일반적으로 persist 한다해도 insert 문이 안됨. 그래서 눈으로 보려면 Rollback false로 해야함
    public void 회원가입() throws Exception{
        // given
        Member member = new Member();
        member.setName("park");

        // when
        Long savdId = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findOne(savdId));
    }

    @Test(expected = IllegalStateException.class)
    /*
        try{
            memberService.join(member2); // 예외가 터져야한다! 왜냐면 validateDuplicatedMember 메서스 테스트이기 때문에
        }catch(IllegalStateException e){
            return;
        } 이걸 줄여준다 expected 쓰면 !

     */
    public void 중복_회원_예외() throws Exception{
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
        // when
        memberService.join(member1);
        memberService.join(member2);
        // then
        fail("에외가 발생해야 한다.");
    }

}