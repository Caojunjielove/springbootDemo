package com.cjj.util;

import java.util.UUID;

public class RandomUtils {

	public static String getUuidStr(){
		String key = UUID.randomUUID().toString();
		key = key.replace("-", "");
		return key;
	}
}
