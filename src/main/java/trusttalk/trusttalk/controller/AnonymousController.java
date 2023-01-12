package trusttalk.trusttalk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import trusttalk.trusttalk.domain.Anonymous;
import trusttalk.trusttalk.service.AnonymousService;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/anonymous")
@Slf4j
public class AnonymousController {

	private final AnonymousService anonymousService;
	
	@GetMapping("/list")
	public String anonymousList(Model model,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		
		Page<Anonymous> list = anonymousService.anonymousList(pageable);

		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, list.getTotalPages());

		model.addAttribute("notice", anonymousService.anonymousList(pageable));
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		return "anonymousList";
	}
	
	@GetMapping("/write")
	public String anonymousWrite() {
		return "anonymousWrite";
	}
	
	@PostMapping("/write")
	public String anonymousInsert(Anonymous anonymous, Authentication authentication) {
			anonymous.setWriter(authentication.getName());
			log.info(authentication.getName());
			log.info(authentication.getDetails().toString());
			log.info(authentication.getPrincipal().toString());
			anonymousService.anonymousWrite(anonymous);
			return "redirect:/anonymous/list";
	}
	
}
