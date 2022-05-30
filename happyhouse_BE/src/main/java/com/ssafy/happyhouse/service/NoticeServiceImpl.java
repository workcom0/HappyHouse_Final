package com.ssafy.happyhouse.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.NoticeDto;
import com.ssafy.happyhouse.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public List<NoticeDto> selectNotice() {
		return noticeMapper.selectNotice();
	}

	@Override
	public NoticeDto selectNoticeByNo(int noticeNo) {
		return noticeMapper.selectNoticeByNo(noticeNo);
	}

	@Override
	public boolean insertNotice(NoticeDto notice) {
		return noticeMapper.insertNotice(notice)==1;
	}

	@Override
	public boolean updateNotice(NoticeDto notice) {
		return noticeMapper.updateNotice(notice)==1;
	}

	@Override
	public boolean deleteNotice(int noticeNo) {
		return noticeMapper.deleteNotice(noticeNo)==1;
	}

	@Override
	public List<NoticeDto> searchNotice(HashMap<String, String> map) {
		return noticeMapper.searchNotice(map);
	}

}
