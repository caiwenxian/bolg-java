package utils;

import java.lang.reflect.Method;

public final class ApprBeanUtil {
	public static Object poToVo(Object po, Object vo) {
		Class poCl = po.getClass();
		Class voCl = vo.getClass();
		Method voMethods[] = voCl.getMethods();
		if (voMethods.length == 0) {
			return vo;
		}
		for (int i = 0; i < voMethods.length; i++) {
			Method voMethod = voMethods[i];
			String voMethodName = voMethod.getName();
			if (!voMethodName.startsWith("set")) {
				continue;
			}
			String getName = "get" + voMethodName.substring(3);
			Method poMethod = null;
			try {
				poMethod = poCl.getMethod(getName, null);
				Object obj = poMethod.invoke(po, null);
				voMethod.invoke(vo, new Object[] { obj });
			} catch (Exception e) {
				// 有异常则忽略，因此封闭异常
			}
		}
		return vo;
	}
	
	/**
	 * <p>该方法只处理非空值的属性<p>
	 * @param vo
	 * @param po
	 * @return
	 */
	public static Object voToPo(Object vo, Object po) {
		Class voCl = vo.getClass();
		Class poCl = po.getClass();
		Method poMethods[] = poCl.getMethods();
		if (poMethods.length == 0) {
			return po;
		}
		for (int i = 0; i < poMethods.length; i++) {
			Method poMethod = poMethods[i];
			String poMethodName = poMethod.getName();
			if (!poMethodName.startsWith("set")) {
				continue;
			}
			String getName = "get" + poMethodName.substring(3);
			Method voMethod = null;
			try {
				voMethod = voCl.getMethod(getName, null);
				Object obj = voMethod.invoke(vo, null);
				if(obj != null)    //注意:这里只处理非空值的属性值
				   poMethod.invoke(po, new Object[] { obj });
			} catch (Exception e) {
				// 有异常则忽略，因此封闭异常
			}
		}
		return po;
	}
	
	public static Object voToPo(Object vo, Class poClass) {
		Object po = null;
		try{
		   po = poClass.newInstance();
		   return voToPo(vo, po);
		}catch(Exception ex){
			// 有异常则忽略，因此封闭异常
		}
	    return po;
	}
	
	public static Object poToVo(Object po, Class voClass) {
		Object vo = null;
		try{
		   vo = voClass.newInstance();
		   return poToVo(po, vo);
		}catch(Exception ex){
			// 有异常则忽略，因此封闭异常
		}
	    return vo;
	}
}
