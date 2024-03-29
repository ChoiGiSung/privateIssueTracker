package com.codesqaude.cocomarco.controller;

import com.codesqaude.cocomarco.common.auth.Auth;
import com.codesqaude.cocomarco.domain.label.dto.LabelRequest;
import com.codesqaude.cocomarco.domain.label.dto.LabelWrapper;
import com.codesqaude.cocomarco.service.LabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/labels")
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @GetMapping
    public LabelWrapper findAll(Pageable pageable) {
        return labelService.findAll(pageable);
    }

    @Auth
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody LabelRequest labelRequest) {
        labelService.create(labelRequest);
    }

    @Auth
    @PutMapping("/{labelId}")
    public void modify(@PathVariable Long labelId, @RequestBody LabelRequest labelRequest) {
        labelService.modify(labelId, labelRequest);
    }

    @Auth
    @DeleteMapping("/{labelId}")
    public void delete(@PathVariable Long labelId) {
        labelService.delete(labelId);
    }
}
