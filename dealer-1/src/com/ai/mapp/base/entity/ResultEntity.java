package com.ai.mapp.base.entity;

//@Entity
//@NamedNativeQuery(name="getChannelInfo",query="call PRC_CHANGE_HQ_CODE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",resultClass=ResultEntity.class,resultSetMapping="channelInfoResults")
//@SqlResultSetMapping(name="channelInfoResults", 
//    entities={ 
//        @EntityResult(entityClass=ResultEntity.class, fields={
//            @FieldResult(name="ret1", column="OUT_CHANNEL_ID"),
//            @FieldResult(name="ret2", column="OUT_CHNL_KIND_ID")
//        })
//    }
//)
public class ResultEntity {

	private Object ret1;

	private Object ret2;

	private Object ret3;

	private Object ret4;

	private Object ret5;
	
	public Object getRet1() {
		return ret1;
	}
	public void setRet1(Object ret1) {
		this.ret1 = ret1;
	}
	public Object getRet2() {
		return ret2;
	}
	public void setRet2(Object ret2) {
		this.ret2 = ret2;
	}
	public Object getRet3() {
		return ret3;
	}
	public void setRet3(Object ret3) {
		this.ret3 = ret3;
	}
	public Object getRet4() {
		return ret4;
	}
	public void setRet4(Object ret4) {
		this.ret4 = ret4;
	}
	public Object getRet5() {
		return ret5;
	}
	public void setRet5(Object ret5) {
		this.ret5 = ret5;
	}
	
	
}
