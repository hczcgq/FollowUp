/**
 * @COPYRIGHT Shanghai RaxTone-Tech Co.,Ltd.
 * @Title: CollectionUtils.java  
 * @Description: TODO 
 * @author Mojiajing 	 
 * @date 2012-2-7
 * @version V1.0 
 * 
 * Modification History: 
 * 2012-2-7  |  Mojiajing   |  Created 
 */
package com.shbestwin.followupmanager.common.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Description: TODO .<br>
 * <p>
 * 
 * @author jiajing.mo
 * @version V1.0
 */
public class CollectionUtils {

	public static boolean isEmpty(Collection<?> collection) {
		return (collection == null || collection.isEmpty());
	}

	public static boolean isNotEmpty(Collection<?> collection) {
		return !CollectionUtils.isEmpty(collection);
	}

	public static boolean isSetEqual(Set<?> set1, Set<?> set2) {

		if (set1 == null && set2 == null) {
			return true; // Both are null
		}

		if (set1 == null || set2 == null || set1.size() != set2.size() || set1.size() == 0 || set2.size() == 0) {
			return false;
		}

		Iterator<?> ite2 = set2.iterator();
		boolean isFullEqual = true;
		while (ite2.hasNext()) {
			if (!set1.contains(ite2.next())) {
				isFullEqual = false;
			}
		}
		return isFullEqual;
	}
}
