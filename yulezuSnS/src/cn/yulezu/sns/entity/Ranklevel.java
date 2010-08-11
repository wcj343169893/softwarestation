package cn.yulezu.sns.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Ranklevel {
	@Id
	private Integer level;
	private Integer rankvalue;
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getRankvalue() {
		return rankvalue;
	}
	public void setRankvalue(Integer rankvalue) {
		this.rankvalue = rankvalue;
	}
	
	
}