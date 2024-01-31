package com.learn.epam.springfoundation.service;

import com.learn.epam.springfoundation.entity.MemberEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PopulateDBServiceTest {

    @Test
    void newEntTest() {
        var line = "37;Smith;Darren;3 Funktown, Denzington, Boston;66796;(822) 577-3541;;2012-09-26 18:08:45";
        var result = new MemberEntity(line.split(";"));
        assertNotNull(result);
    }

    @Test
    void newEntTest2() {
        var line = "37;Smith;Darren;3 Funktown, Denzington, Boston;66796;(822) 577-3541;2;2012-09-26 18:08:45";
        var result = new MemberEntity(line.split(";"));
        assertNotNull(result);
    }

    @Test
    void newEntTest3() {
        var line = "0;GUEST;GUEST;GUEST;0;(000) 000-0000;;2012-07-01 00:00:00";
        var s=line.split(";");
        var result = new MemberEntity(line.split(";"));
        assertNotNull(result);
    }

}