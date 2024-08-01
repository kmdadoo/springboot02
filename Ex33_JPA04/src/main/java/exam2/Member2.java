package exam2;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="JpaMember2")	// 여기가 없으면 아래 이름(Member2)으로 만들어짐.
public class Member2 
{
	@Id
	@SequenceGenerator (	// 시퀀스 설정을 여기서 해줌.
			name = "mySequence01",
			sequenceName = "JpaMember2_SEQ",
			initialValue = 1,
			allocationSize = 1
	)
	@GeneratedValue (generator = "mySequence01")
	private Long id;
	@Access(AccessType.FIELD)
	private String username;	// 필드를 통해서 데이터 접근
	@Access(AccessType.PROPERTY)	// 멤버 메서드를 통해 접근
	private String password;	// 프로퍼티(get/set)를 통해서 데이터 접근
	@Transient	// 영속 대상에서 제외한다는 의미
	private long timestamp1;			// 영속 대상에서 제외 어노테이션
	transient private long timestamp2;	// 영속 대상에서 제외 키워드

	protected Member2() {}
	
	public Member2(String username, String password) 
	{
		this.username = username;
		this.password = password;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}
