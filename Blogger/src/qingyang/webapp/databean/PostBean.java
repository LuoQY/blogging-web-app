package qingyang.webapp.databean;
import java.util.Date;

import org.genericdao.PrimaryKey;


@PrimaryKey("id")
public class PostBean {
	private int id;
	private String email;
	private String content;
	private Date time;
	private int position;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}

	
	
}
