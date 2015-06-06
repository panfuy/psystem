package ps.pojo;

import java.sql.Timestamp;

/**
 * BaseAccount entity. @author MyEclipse Persistence Tools
 */

public class BaseAccount implements java.io.Serializable {

	// Fields

	private String accountId;
	private String accountPwd;
	private Timestamp accountCreatedate;
	private Long accountStatus;
	private String accountRemark;

	// Constructors

	/** default constructor */
	public BaseAccount() {
	}

	/** minimal constructor */
	public BaseAccount(String accountId, String accountPwd, Long accountStatus) {
		this.accountId = accountId;
		this.accountPwd = accountPwd;
		this.accountStatus = accountStatus;
	}

	/** full constructor */
	public BaseAccount(String accountId, String accountPwd,
			Timestamp accountCreatedate, Long accountStatus,
			String accountRemark) {
		this.accountId = accountId;
		this.accountPwd = accountPwd;
		this.accountCreatedate = accountCreatedate;
		this.accountStatus = accountStatus;
		this.accountRemark = accountRemark;
	}

	// Property accessors

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountPwd() {
		return this.accountPwd;
	}

	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}

	public Timestamp getAccountCreatedate() {
		return this.accountCreatedate;
	}

	public void setAccountCreatedate(Timestamp accountCreatedate) {
		this.accountCreatedate = accountCreatedate;
	}

	public Long getAccountStatus() {
		return this.accountStatus;
	}

	public void setAccountStatus(Long accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getAccountRemark() {
		return this.accountRemark;
	}

	public void setAccountRemark(String accountRemark) {
		this.accountRemark = accountRemark;
	}

}