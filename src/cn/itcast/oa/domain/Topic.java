package cn.itcast.oa.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;

/*
 * 主题
 */
public class Topic extends Article{

	/*
	 * 普通帖
	 */
	public static final int TYPE_NORMAL = 0;
	/*
	 * 精华帖
	 */
	public static final int TYPE_BEST = 1;
	/*
	 * 置顶帖
	 */
	public static final int TYPE_TOP = 2;
	
	private Forum forum;//所属的版块
	private Set<Reply> replies = new HashSet<Reply>();
	private int type;//数量
	private int replyCount;//回复数量
	private Reply lastReply;//最后回复
	//@Column(columnDefinition="timestamp")
	private Timestamp lastUpdateTime;//最后更新时间（主题发表时间或者最后回复时间）
	
	
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public Set<Reply> getReplies() {
		return replies;
	}
	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public Reply getLastReply() {
		return lastReply;
	}
	public void setLastReply(Reply lastReply) {
		this.lastReply = lastReply;
	}
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public static int getTypeNormal() {
		return TYPE_NORMAL;
	}
	public static int getTypeBest() {
		return TYPE_BEST;
	}
	public static int getTypeTop() {
		return TYPE_TOP;
	}
	
	
	
}
