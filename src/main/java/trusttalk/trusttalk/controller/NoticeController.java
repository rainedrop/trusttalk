package trusttalk.trusttalk.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import trusttalk.trusttalk.domain.Notice;
import trusttalk.trusttalk.service.NoticeService;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/notice")
public class NoticeController {

	private final NoticeService noticeService;

	@GetMapping("list")
	public String noticeList(Model model,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Notice> list = noticeService.noticeList(pageable);

		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, list.getTotalPages());

		model.addAttribute("notice", noticeService.noticeList(pageable));
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		return "noticeList";
	}

	@GetMapping("write")
	public String noticeWrite() {
		return "noticeWrite";
	}

	@PostMapping("write")
	public String noticeInsert(Notice notice) {
		notice.setHits(0L);
		notice.setWriter("관리자");
		noticeService.noticeWrite(notice);
		return "redirect:/notice/list";
	}

	@GetMapping("detail/{id}")
	public String noticeDetail(Model model, @PathVariable("id") Long id) {
		noticeService.noticeHits(id);
		model.addAttribute("notice", noticeService.noticeDetail(id));
		return "noticeDetail";
	}

	@GetMapping("delete/{id}")
	public String noticeDelete(@PathVariable("id") Long id) {
		noticeService.noticeDelete(id);
		return "redirect:/notice/list";
	}

	@GetMapping("update/{id}")
	public String noticeUpdate(Model model, @PathVariable("id") Long id) {
		model.addAttribute("notice", noticeService.noticeDetail(id));

		return "noticeUpdate";
	}

	@PostMapping("update/{id}")
	public String noticeUpdateInsert(@PathVariable("id") Long id, Notice notice) {
		Notice noticeTemp = noticeService.noticeDetail(id);
		noticeTemp.setTitle(notice.getTitle());
		noticeTemp.setContent(notice.getContent());
		noticeService.noticeWrite(noticeTemp);

		return "redirect:/notice/list";
	}
}
