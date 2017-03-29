package org.apache.servicemix.examples.cxf.util;

public class ObjectClassMatcher {

	private ObjectClassMatcher() {
	}

	public static boolean matchesAtLeastOneName(String[] names, String pattern) {
		for (String objectClass : names) {
			if (matchesName(objectClass, pattern)) {
				return true;
			}
		}
		return false;
	}

	public static boolean matchesName(String name, String pattern) {
		return name.equals(pattern) || getShortName(name).equals(pattern);
	}

	public static String getShortName(String name) {
		int idx = name.lastIndexOf(".");
		if (idx + 1 > name.length()) {
			idx = 0;
		}
		return name.substring(idx + 1);
	}

}
