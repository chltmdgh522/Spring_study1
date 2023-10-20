package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryV0Test {

    MemberRepositoryV0 repository=new MemberRepositoryV0();
    @Test
    void crud() throws SQLException {
        Member member = new Member("최승호", 1000000000);
        repository.save(member);
    }
}