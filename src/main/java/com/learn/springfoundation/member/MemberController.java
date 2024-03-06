package com.learn.springfoundation.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberDAO memberDAO;

    @GetMapping("/members")
    public Page<MemberDTO> getMembers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return memberDAO.getAllPaginated(PageRequest.of(page - 1, size));
    }

    @GetMapping("/members/{id}")
    public MemberDTO getMember(@PathVariable Long id) {
        return memberDAO.getById(id);
    }

//    @PostMapping("/members")
//    public ResponseEntity<MemberDTO> createMember(@RequestBody NewMember member) {
//        var createdMember = memberDAO.createNewMember(member);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
//    }
}
