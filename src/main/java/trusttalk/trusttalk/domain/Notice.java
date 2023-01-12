package trusttalk.trusttalk.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "trusttalk.notice")
@Data
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="notice_id")
	private Long id; // 글 번호

	private String title; // 글 제목

	private String content; // 글 내용

	private String writer; // 작성자

	private Long hits; // 조회수
	
	private String date = LocalDateTime.now().toString(); // 작성 일자
}
