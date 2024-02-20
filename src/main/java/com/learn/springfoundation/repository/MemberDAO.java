package com.learn.springfoundation.repository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberDAO {

    private final MembersViewRepo membersViewRepo;
    private final MembersRepo membersRepo;

    @Autowired
    public MemberDAO(MembersViewRepo membersViewRepo, MembersRepo membersRepo) {
        this.membersViewRepo = membersViewRepo;
        this.membersRepo = membersRepo;
    }

    public List<MemberDTO> getMembers() {
        var ms = membersRepo.findAll();
        List<MemberDTO> members = new ArrayList<>();
        ms.forEach(memberEntity -> members.add(toDto(memberEntity)));
        return members;
    }

    private MemberDTO toDto(Member m) {
        Long rec = null;
        if (m.getRecommendedby() != null) {
            rec = m.getRecommendedby().getMemid();
        }
        return new MemberDTO(m.getMemid(), m.getSurname(), m.getFirstname(), m.getAddress(), m.getZipcode(), m.getTelephone(), rec, m.getJoindate());
    }

    void getMembersListing() {
        var views = membersViewRepo.findAll();
        views.forEach(this::showMember);
    }

    private void showMember(Member member) {
        System.out.printf("%d %s %s", member.getMemid(), member.getFirstname(), member.getSurname());
        System.out.println();
    }

    private void showMember(MemberView member) {
        System.out.printf("%d %s %s", member.getMemid(), member.getFirstname(), member.getSurname());
        System.out.println();
    }

    public MemberDTO getById(Long id) {
        var o = membersRepo.findById(id);
        if (o.isPresent()) {
            return toDto(o.get());
        } else {
            throw new EntityNotFoundException("Member with id " + id + " not found");
        }
    }
}
