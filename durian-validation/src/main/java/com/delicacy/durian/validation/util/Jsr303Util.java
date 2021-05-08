package com.delicacy.durian.validation.util;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

/**
 * JSR303的校验帮助类
 * @author yangzhilong
 *
 */
public class Jsr303Util {

	private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * 如果返回null则表示没有错误
	 * @param obj
	 * @return
	 */
	public static String check(Object obj) {
//		if (null == obj) {
//			return "入参不能为空";
//		}
		Set<ConstraintViolation<Object>> validResult = validator.validate(obj);
		if (null != validResult && validResult.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Iterator<ConstraintViolation<Object>> iterator = validResult.iterator(); iterator.hasNext();) {
				ConstraintViolation<Object> constraintViolation = iterator.next();
				String string = constraintViolation.getPropertyPath().toString();
				String message = constraintViolation.getMessage();
				if(isNotBlank(message) &&
						message.contains(string)) {
					sb.append(message).append("、");
				} else if(isNotBlank(message) &&
						!message.contains(string)){
					int i = message.indexOf("不");
					//第一个是不
					if (i==0)sb.append(string).append(message).append("、");
					else sb.append(message).append("、");
				}else {
					sb.append(string).append("、");
				}
			}
			if (sb.lastIndexOf("、") == sb.length() - 1) {
				sb.delete(sb.length() - 1, sb.length());
			}
			return sb.toString();
		}
		return null;
	}


	private static boolean isNotBlank(String string){
		return null!=string && !"".equals(string);
	}

}
