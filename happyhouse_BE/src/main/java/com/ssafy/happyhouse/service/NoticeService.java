package com.ssafy.happyhouse.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.happyhouse.dto.NoticeDto;

public interface NoticeService {
	public List<NoticeDto> selectNotice();
	public NoticeDto selectNoticeByNo(int noticeNo);
	public boolean insertNotice(NoticeDto notice);
	public boolean updateNotice(NoticeDto notice);
	public boolean deleteNotice(int noticeNo);
	public List<NoticeDto> searchNotice(HashMap<String, String> map);
}
