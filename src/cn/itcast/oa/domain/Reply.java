package cn.itcast.oa.domain;

/*8
 * 回复
 */
public class Reply extends Article {

	public Topic topic;

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
}
