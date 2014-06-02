package com.qat.webdaptive.bai;

import java.util.List;

public interface IAsyncRepositoryBAI
{

	public List<String> fetchMessages(Integer id);

	public void insertMessage(String message);

	public void deleteMessages();

}
