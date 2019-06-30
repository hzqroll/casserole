package com.roll.casserole.annotation.dbannotation;

/**
 * MEMBER是表名
 * @author haozq
 * Date: 2018/8/19 下午2:24
 */
@DBTable(name = "MEMBER")
public class Member {

	@SQLString(30)
	String firstName;

	@SQLString(50)
	String laseName;

	@SQLInteger
	Integer age;

	@SQLString(value = 30,
			constraints = @Constraints(primaryKey = true))
	String handle;

	public String getFirstName() {
		return firstName;
	}

	public String getLaseName() {
		return laseName;
	}

	public Integer getAge() {
		return age;
	}

	public String getHandle() {
		return handle;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Member{");
		sb.append("firstName='").append(firstName).append('\'');
		sb.append(", laseName='").append(laseName).append('\'');
		sb.append(", age=").append(age);
		sb.append(", handle='").append(handle).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
