package com.codesqaude.cocomarco.domain.issue;

import com.codesqaude.cocomarco.domain.issue.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Query("select distinct i from Issue i left join fetch i.writer " +
            "left join fetch i.assignments a " +
            "left join fetch a.user u " +
            "left join fetch i.comments " +
            "left join fetch i.milestone " +
            "left join fetch i.issueLabels il " +
            "left join fetch il.label l " +
            "where i.id = :id")
    Optional<Issue> findByIdFetch(Long id);
}
