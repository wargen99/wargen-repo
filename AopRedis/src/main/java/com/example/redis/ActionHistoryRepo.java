package com.example.redis;

import java.util.List;

public abstract interface ActionHistoryRepo<Key, Value> {
	public abstract void addAction(Value paramValue);

	public abstract List<RankDto> getRank();

	public abstract void deleteAction(Key paramKey);
}
